package ProjetDeSyntheseNotification.notification.service;

import ProjetDeSyntheseNotification.notification.dto.EMAILNotificationDTO;
import ProjetDeSyntheseNotification.notification.model.EmailNotification;
import ProjetDeSyntheseNotification.notification.repository.EMAILNotificationRepository;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EmailService {

    private final EMAILNotificationRepository emailNotificationRepository;
    private final JavaMailSender mailSender;

    @Autowired
    public EmailService(EMAILNotificationRepository emailNotificationRepository, JavaMailSender mailSender) {
        this.emailNotificationRepository = emailNotificationRepository;
        this.mailSender = mailSender;
    }

    /**
     * Crée une notification email et envoie l'email automatiquement
     */
    public EMAILNotificationDTO createEmailNotification(EMAILNotificationDTO emailNotificationDTO) {
        EmailNotification emailNotification = mapToEntity(emailNotificationDTO);
        EmailNotification savedNotification = emailNotificationRepository.save(emailNotification);

        // Envoyer l'email après sauvegarde
        sendEmail(emailNotificationDTO.getEmail(), emailNotificationDTO.getSubject(), emailNotificationDTO.getMessage());

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
        return emailNotificationRepository.findById(id).map(notification -> {
            notification.setSubject(updatedEmailNotificationDTO.getSubject());
            notification.setEmail(updatedEmailNotificationDTO.getEmail());
            notification.setMessage(updatedEmailNotificationDTO.getMessage());
            EmailNotification updatedNotification = emailNotificationRepository.save(notification);

            // Envoyer un email après mise à jour
            //sendEmail(updatedEmailNotificationDTO.getEmail(), updatedEmailNotificationDTO.getSubject(), updatedEmailNotificationDTO.getMessage());

            return mapToDTO(updatedNotification);
        }).orElseThrow(() -> new RuntimeException("EmailNotification not found with id " + id));
    }

    public void deleteEmailNotification(Long id) {
        if (!emailNotificationRepository.existsById(id)) {
            throw new RuntimeException("EmailNotification not found with id " + id);
        }
        emailNotificationRepository.deleteById(id);
    }

    /**
     * Méthode pour envoyer un email
     */
    private void sendEmail(String to, String subject, String message) {
        try {
            MimeMessage mail = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mail, false, "UTF-8");

            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(message, true); // HTML activé
            helper.setFrom("tonemail@gmail.com");

            mailSender.send(mail);
        } catch (MessagingException e) {
            throw new RuntimeException("Échec de l'envoi de l'email", e);
        }
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
