package ProjetDeSyntheseNotification.notification.service;

import ProjetDeSyntheseNotification.notification.dto.PushNotificationDTO;
import ProjetDeSyntheseNotification.notification.model.PushNotification;
import ProjetDeSyntheseNotification.notification.repository.PushNotificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PushService {

    private final PushNotificationRepository pushNotificationRepository;
    private PushNotificationDTO dto;

    @Autowired
    public PushService(PushNotificationRepository pushNotificationRepository) {
        this.pushNotificationRepository = pushNotificationRepository;
    }

    public PushNotification createPushNotification(PushNotificationDTO pushDTO) {
        return pushNotificationRepository.save(pushDTO);
    }



    public List<PushNotification> getAllPushNotifications() {
        return pushNotificationRepository.findAll();
    }

    public Optional<PushNotification> getPushNotificationById(Long id) {
        return pushNotificationRepository.findById(id);
    }

    public PushNotification updatePushNotification(Long id, PushNotificationDTO pushDTO) {
        return pushNotificationRepository.findById(id).map(notification -> {
            notification.setSenderName(pushDTO.getReceiverName());
            notification.setSenderName(pushDTO.getReceiverName());
            notification.setSubject(pushDTO.getSubject());
            notification.setMessage(pushDTO.getMessage());
            return pushNotificationRepository.save(notification);
        }).orElseThrow(() -> new RuntimeException("PushNotification not found with id " + id));
    }

    public void deletePushNotification(Long id) {
        pushNotificationRepository.deleteById(id);
    }
}
