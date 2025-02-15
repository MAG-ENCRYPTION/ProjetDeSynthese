package inc.notification.service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import org.thymeleaf.TemplateEngine;
import org.springframework.http.HttpStatus;
//import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import inc.notification.Validator.EmailValidator;
import inc.notification.dto.EmailNotificationDTO;
import inc.notification.model.EmailNotification;
import inc.notification.repository.EmailNotificationRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class EmailService {

    private final EmailNotificationRepository emailNotificationRepository;
    private final JavaMailSender mailSender;
    private final EmailValidator emailValidator;
    private final TemplateEngine templateEngine;

    @Autowired
    public EmailService(EmailNotificationRepository emailNotificationRepository, JavaMailSender mailSender,
                        EmailValidator emailValidator, TemplateEngine templateEngine) {
        this.emailNotificationRepository = emailNotificationRepository;
        this.mailSender = mailSender;
        this.emailValidator = emailValidator;
        this.templateEngine = templateEngine;
    }

    public EmailNotificationDTO createEmailNotification(EmailNotificationDTO emailNotificationDTO) {
        String validationError = emailValidator.validateEmail(emailNotificationDTO.getEmail(), emailNotificationDTO.getMessage());
        if (validationError != null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, validationError);
        }

        //EmailNotification emailNotification = mapToEntity(emailNotificationDTO);
        EmailNotification emailNotification = new EmailNotification();
        emailNotification.setEmail_id(UUID.randomUUID());
        emailNotification.setMessage(emailNotificationDTO.getMessage());
        emailNotification.setSubject(emailNotificationDTO.getSubject());
        emailNotification.setEmail(emailNotificationDTO.getEmail());
        emailNotification.setType(emailNotificationDTO.getType());
        emailNotification.setPriority(emailNotificationDTO.getPriority());
        EmailNotification savedNotification = emailNotificationRepository.save(emailNotification);

        sendStyledEmail(emailNotificationDTO.getEmail(), emailNotificationDTO.getSubject(),
                emailNotificationDTO.getMessage(), emailNotificationDTO.getType());

        return mapToDTO(savedNotification);
    }

    public List<EmailNotification> getAllEmailNotifications() {
        return emailNotificationRepository.findAll();
    }

    public Optional<EmailNotificationDTO> getEmailNotificationById(UUID id) {
        return emailNotificationRepository.findById(id).map(this::mapToDTO);
    }

    public EmailNotificationDTO updateEmailNotification(UUID id, EmailNotificationDTO updatedEmailNotificationDTO) {
        String validationError = emailValidator.validateEmail(updatedEmailNotificationDTO.getEmail(), updatedEmailNotificationDTO.getMessage());
        if (validationError != null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, validationError);
        }

        return emailNotificationRepository.findById(id).map(notification -> {
            notification.setSubject(updatedEmailNotificationDTO.getSubject());
            notification.setEmail(updatedEmailNotificationDTO.getEmail());
            notification.setMessage(updatedEmailNotificationDTO.getMessage());
            EmailNotification updatedNotification = emailNotificationRepository.save(notification);

            sendStyledEmail(updatedEmailNotificationDTO.getEmail(), updatedEmailNotificationDTO.getSubject(),
                    updatedEmailNotificationDTO.getMessage(), updatedEmailNotificationDTO.getType());

            return mapToDTO(updatedNotification);
        }).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "EmailNotification not found with id " + id));
    }

    public void deleteEmailNotification(UUID id) {
        if (!emailNotificationRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "EmailNotification not found with id " + id);
        }
        emailNotificationRepository.deleteById(id);
    }

    private void sendStyledEmail(String to, String subject, String message, String type) {
        try {
            MimeMessage mail = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mail, true, "UTF-8");
            String resetLink = "https://driver.yowyob.com/";
            String pickupTime = "pickupTime";
            String destination = "destination";
            String departure = "departure";
            String rating = "4";
            String comment = "Super chauffeur, très ponctuel et courtois !";

            Context context = new Context();
            context.setVariable("username", to);
            context.setVariable("message", message);
            context.setVariable("resetLink", resetLink);
            context.setVariable("pickupTime", pickupTime);
            context.setVariable("destination", destination);
            context.setVariable("departure", departure);
            context.setVariable("rating", rating);
            context.setVariable("comment", comment);
            String templateName = getTemplateNameByType(type);
            String htmlContent = templateEngine.process(templateName, context);
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(htmlContent, true);
            helper.setFrom("tonemail@gmail.com");

            mailSender.send(mail);
        } catch (MessagingException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Échec de l'envoi de l'email");
        }
    }

    private String getTemplateNameByType(String type) {
        return switch (type.toUpperCase()) {
            case "OTP" -> "otp-template";
            case "ALERTE" -> "alert-template";
            case "RESERVATION" -> "reservation-template";
            case "FEEDBACK" -> "feedback-template";
            case "RESET_PASSWORD" -> "reset-password-template";
            case "RESERVATION_CONFIRMATION" -> "reservation-confirmation-template";
            case "CONFIRMATION_MAIL" -> "confirmation-template";
            case "RAPPEL" -> "rappel-template";
            case "ORGANISATION_ACTIVATED" -> "activation-organisation";
            case "PAYMENT_FAILED" -> "echec-paiement";
            case "PAYMENT_SUCCESS" -> "succes-paiement";
            case "PRODUCT_AVAILABLE" -> "produit-disponible";
            case "ORGANISATION_CREATED" -> "activation-organisation";
            case "ORGANISATION_FAILED" -> "activation-organisation";
            case "EMISSION_STOPPED" -> "arret-emission";
            case "DEVICE_CREATED" -> "creation-appareil";
            case "DEVICE_DELETED" -> "suppression-appareil";
            case "DEVICE_ERROR" -> "erreur-appareil";
            default -> "email-template";
        };
    }

    private EmailNotification mapToEntity(EmailNotificationDTO dto) {
        EmailNotification entity = new EmailNotification();
        entity.setSubject(dto.getSubject());
        entity.setEmail(dto.getEmail());
        entity.setMessage(dto.getMessage());
        entity.setType(dto.getType());
        entity.setPriority(dto.getPriority());
        return entity;
    }

    private EmailNotificationDTO mapToDTO(EmailNotification entity) {
        EmailNotificationDTO dto = new EmailNotificationDTO();
        dto.setSubject(entity.getSubject());
        dto.setEmail(entity.getEmail());
        dto.setMessage(entity.getMessage());
        dto.setType(entity.getType());
        dto.setPriority(entity.getPriority());
        return dto;
    }
}
