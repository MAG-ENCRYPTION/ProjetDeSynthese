package inc.yowyob.service.notification.application.service.impl;

import inc.yowyob.service.notification.application.dto.email.EmailNotificationRequest;
import inc.yowyob.service.notification.application.enums.Encryption;
import inc.yowyob.service.notification.application.service.*;
import inc.yowyob.service.notification.infrastructure.persistence.entities.DesignTemplate;
import inc.yowyob.service.notification.infrastructure.persistence.entities.EmailNotification;
import inc.yowyob.service.notification.infrastructure.persistence.entities.Template;
import inc.yowyob.service.notification.infrastructure.persistence.entities.SmtpSetting;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import java.io.UnsupportedEncodingException;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class EmailServiceImpl implements EmailService {

    private final TemplateService emailTemplateService;

    private final SmtpSettingService smtpSettingService;

    private final EmailNotificationService emailNotificationService;


    @Override
    public Mono<Void> sendEmail(UUID organizationId, EmailNotificationRequest request) throws MessagingException {
        return emailNotificationService.createEmailNotification(organizationId, request.getTemplateId(), request).flatMap(emailNotification -> {
            return emailTemplateService.getTemplateByIdOrDefault(organizationId, request.getTemplateId()).flatMap(emailTemplate -> {
                return sendBySmtpSetting(emailNotification, emailTemplate);
            });
        }).then();
    }

    private Mono<Object> sendBySmtpSetting(EmailNotification emailNotification, Template emailTemplate) {
        return smtpSettingService.getSmtpSettingByIdOrDefault(emailTemplate.getOrganizationId(), emailTemplate.getSettingId()).flatMap(smtpSetting -> {
            return this.sendQuickly(emailNotification, smtpSetting);
        });
    }

    private Mono<Object> sendQuickly(EmailNotification emailNotification, SmtpSetting smtpSetting) {
        return Mono.fromRunnable(() -> {
            try {
                this.send(emailNotification, smtpSetting);
            } catch (MessagingException | UnsupportedEncodingException e) {
                throw new RuntimeException(e);
            }
        }).subscribeOn(Schedulers.boundedElastic());
    }

    private void send(EmailNotification emailNotification, SmtpSetting smtpSetting) throws MessagingException, UnsupportedEncodingException {

        JavaMailSender mailSender = this.getJavaMailSender(smtpSetting);
        MimeMessage mail = mailSender.createMimeMessage();

        MimeMessageHelper helper = new MimeMessageHelper(mail, true, "UTF-8");

        String body = emailNotification.getBody();
        String fromEmail = this.getFromEmail(emailNotification, smtpSetting);
        String fromName = this.getFromName(emailNotification, smtpSetting);

        helper.setTo(this.toEmailArray(emailNotification.getRecipients()));
        helper.setSubject(emailNotification.getSubject());
        helper.setFrom(fromEmail, fromName);
        helper.setText(body, true);
        helper.setCc(this.toEmailArray(emailNotification.getCc()));
        helper.setBcc(this.toEmailArray(emailNotification.getBcc()));

        mailSender.send(mail);
    }

    private String getFromEmail(EmailNotification emailNotification, SmtpSetting smtpSetting) {
        if (emailNotification.getSenderEmail() != null && !emailNotification.getSenderEmail().isEmpty()) {
            return emailNotification.getSenderEmail();
        }
        return smtpSetting.getSenderEmail();
    }

    private String getFromName(EmailNotification emailNotification, SmtpSetting smtpSetting) {
        if (emailNotification.getSenderName() != null && !emailNotification.getSenderName().isEmpty()) {
            return emailNotification.getSenderName();
        }
        return smtpSetting.getSenderName();
    }

    private InternetAddress[] toInternetAddresses(Set<String> recipients) {
        return recipients.stream().map(this::createInternetAddress).toArray(InternetAddress[]::new);
    }

    private String[] toEmailArray(Set<String> addresses) {
        if (addresses == null) {
            return new String[0];
        }
        return addresses.toArray(String[]::new);
    }

    private InternetAddress createInternetAddress(String email) {
        try {
            return new InternetAddress(email);
        } catch (Exception e) {
            // Handle the exception (e.g., log it)
            System.err.println("Invalid email address: " + email);
            return null; // Or throw a custom exception
        }
    }

    public JavaMailSender getJavaMailSender(SmtpSetting smtpSetting) {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost(smtpSetting.getHost()); // SMTP server address
        mailSender.setPort(smtpSetting.getPort()); // SMTP server port

        mailSender.setUsername(smtpSetting.getUsername()); // Your SMTP username
        mailSender.setPassword(smtpSetting.getPassword()); // Your SMTP password
        mailSender.setProtocol("smtp");

        Properties props = mailSender.getJavaMailProperties();
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", smtpSetting.getEncryption() == Encryption.TLS ? "true" : "false");
        props.put("mail.debug", "true"); // Enable debug output

        return mailSender;
    }
}
