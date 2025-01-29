package ProjetDeSyntheseNotification.notification.service;

import ProjetDeSyntheseNotification.notification.dto.EMAILNotificationDTO;
import ProjetDeSyntheseNotification.notification.model.EmailNotification;
import ProjetDeSyntheseNotification.notification.repository.EMAILNotificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EmailService {

    private final EMAILNotificationRepository emailNotificationRepository;

    @Autowired
    public EmailService(EMAILNotificationRepository emailNotificationRepository) {
        this.emailNotificationRepository = emailNotificationRepository;
    }

    public EMAILNotificationDTO createEmailNotification(EMAILNotificationDTO emailNotificationDTO) {
        EmailNotification emailNotification = mapToEntity(emailNotificationDTO);
        EmailNotification savedNotification = emailNotificationRepository.save(emailNotification);
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
            return mapToDTO(updatedNotification);
        }).orElseThrow(() -> new RuntimeException("EmailNotification not found with id " + id));
    }

    public void deleteEmailNotification(Long id) {
        if (!emailNotificationRepository.existsById(id)) {
            throw new RuntimeException("EmailNotification not found with id " + id);
        }
        emailNotificationRepository.deleteById(id);
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
        dto.setMessage((String)entity.getMessage());
        dto.setType(entity.getType());
        dto.setPriority(entity.getPriority());
        return dto;
    }
}
