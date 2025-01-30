package ProjetDeSyntheseNotification.notification.service;

import ProjetDeSyntheseNotification.notification.dto.EMAILNotificationDTO;
import ProjetDeSyntheseNotification.notification.model.EmailNotification;
import ProjetDeSyntheseNotification.notification.repository.EMAILNotificationRepository;
import ProjetDeSyntheseNotification.notification.Validator.EmailValidator;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EmailService {

    private final EMAILNotificationRepository emailNotificationRepository;
    private final JavaMailSender mailSender;
    private final EmailValidator emailValidator;
    private final TemplateEngine templateEngine;

    @Autowired
    public EmailService(EMAILNotificationRepository emailNotificationRepository, JavaMailSender mailSender,
                        EmailValidator emailValidator, TemplateEngine templateEngine) {
        this.emailNotificationRepository = emailNotificationRepository;
        this.mailSender = mailSender;
        this.emailValidator = emailValidator;
        this.templateEngine = templateEngine;
    }

    public EMAILNotificationDTO createEmailNotification(EMAILNotificationDTO emailNotificationDTO) {
        String validationError = emailValidator.validateEmail(emailNotificationDTO.getEmail(), emailNotificationDTO.getMessage());
        if (validationError != null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, validationError);
        }

        EmailNotification emailNotification = mapToEntity(emailNotificationDTO);
        EmailNotification savedNotification = emailNotificationRepository.save(emailNotification);

        sendStyledEmail(emailNotificationDTO.getEmail(), emailNotificationDTO.getSubject(),
                emailNotificationDTO.getMessage(), emailNotificationDTO.getType());

        return mapToDTO(savedNotification);
    }

    public List<EMAILNotificationDTO> getAllEmailNotifications() {
        return emailNotificationRepository.findAll()
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    public Optional<EMAILNotificationDTO> getEmailNotificationById(Long id) {
        return emailNotificationRepository.findById(id).map(this::mapToDTO);
    }

    public EMAILNotificationDTO updateEmailNotification(Long id, EMAILNotificationDTO updatedEmailNotificationDTO) {
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

    public void deleteEmailNotification(Long id) {
        if (!emailNotificationRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "EmailNotification not found with id " + id);
        }
        emailNotificationRepository.deleteById(id);
    }

    private void sendStyledEmail(String to, String subject, String message, String type) {
        try {
            MimeMessage mail = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mail, true, "UTF-8");

            Context context = new Context();
            context.setVariable("username", to);
            context.setVariable("message", message);
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
            case "RAPPEL" -> "rappel-template";
            default -> "email-template";
        };
    }

    private EmailNotification mapToEntity(EMAILNotificationDTO dto) {
        EmailNotification entity = new EmailNotification();
        entity.setSubject(dto.getSubject());
        entity.setEmail(dto.getEmail());
        entity.setMessage(dto.getMessage());
        entity.setType(dto.getType());
        entity.setPriority(dto.getPriority());
        return entity;
    }

    private EMAILNotificationDTO mapToDTO(EmailNotification entity) {
        EMAILNotificationDTO dto = new EMAILNotificationDTO();
        dto.setSubject(entity.getSubject());
        dto.setEmail(entity.getEmail());
        dto.setMessage(entity.getMessage());
        dto.setType(entity.getType());
        dto.setPriority(entity.getPriority());
        return dto;
    }
}
