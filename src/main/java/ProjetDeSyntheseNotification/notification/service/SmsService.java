package ProjetDeSyntheseNotification.notification.service;

import ProjetDeSyntheseNotification.notification.dto.SmsNotificationDTO;
import ProjetDeSyntheseNotification.notification.model.SmsNotification;
import ProjetDeSyntheseNotification.notification.repository.SmsNotificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SmsService {

    private final SmsNotificationRepository smsNotificationRepository;

    @Autowired
    public SmsService(SmsNotificationRepository smsNotificationRepository) {
        this.smsNotificationRepository = smsNotificationRepository;
    }

    public SmsNotification createSmsNotification(SmsNotificationDTO smsDTO) {
        // Convertir le DTO en entité avant de l'enregistrer
        SmsNotification smsNotification = new SmsNotification();
        smsNotification.setNumber(smsDTO.getNumber());
        smsNotification.setMessage(smsDTO.getMessage());
        smsNotification.setType(smsDTO.getType());
        smsNotification.setPriority(smsDTO.getPriority());

        return smsNotificationRepository.save(smsNotification);
    }

    public List<SmsNotification> getAllSmsNotifications() {
        return smsNotificationRepository.findAll();
    }

    public Optional<SmsNotification> getSmsNotificationById(Long id) {
        return smsNotificationRepository.findById(id);
    }

    public SmsNotification updateSmsNotification(Long id, SmsNotificationDTO smsDTO) {
        return smsNotificationRepository.findById(id).map(notification -> {
            notification.setNumber(smsDTO.getNumber());
            notification.setMessage(smsDTO.getMessage());
            notification.setType(smsDTO.getType());
            notification.setPriority(smsDTO.getPriority());
            return smsNotificationRepository.save(notification);
        }).orElseThrow(() -> new RuntimeException("SmsNotification not found with id " + id));
    }

    public void deleteSmsNotification(Long id) {
        smsNotificationRepository.deleteById(id);
    }
}
