package inc.notification.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import inc.notification.model.Notification;
import inc.notification.repository.NotificationRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class NotificationService {

    private final NotificationRepository notificationRepository;

    @Autowired
    public NotificationService(NotificationRepository notificationRepository) {
        this.notificationRepository = notificationRepository;
    }

    public Notification createNotification(Notification notification) {
        return notificationRepository.save(notification);
    }

    public List<Notification> getAllNotifications() {
        return notificationRepository.findAll();
    }

    public Optional<Notification> getNotificationById(UUID id) {
        return notificationRepository.findById(id);
    }

    public Notification updateNotification(UUID id, Notification updatedNotification) {
        return notificationRepository.findById(id).map(notification -> {
            notification.setMessage((String) updatedNotification.getMessage());
            return notificationRepository.save(notification);
        }).orElseThrow(() -> new RuntimeException("Notification not found with id " + id));
    }

    public void deleteNotification(UUID id) {
        notificationRepository.deleteById(id);
    }
}
