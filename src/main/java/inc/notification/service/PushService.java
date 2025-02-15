package inc.notification.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import inc.notification.dto.Message;
import inc.notification.dto.PushNotificationDTO;
import inc.notification.model.PushNotification;
import inc.notification.model.enums.PropertyType;
import inc.notification.repository.PushNotificationRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
//import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.time.LocalDateTime;
import java.util.Collections;


@Service
public class PushService {

    private final PushNotificationRepository pushNotificationRepository;
    private final SimpMessagingTemplate messagingTemplate; // Permet d'envoyer des messages WebSocket

    @Autowired
    public PushService(PushNotificationRepository pushNotificationRepository, SimpMessagingTemplate messagingTemplate) {
        this.pushNotificationRepository = pushNotificationRepository;
        this.messagingTemplate = messagingTemplate;
    }

    public List<PushNotification> createPushNotification(PushNotificationDTO pushDTO) {
        List<UUID> receiverIds = pushDTO.getReceiverIds(); // Liste des destinataires
    
        List<PushNotification> notifications = receiverIds.stream().map(receiverId -> {
            PushNotification notification = new PushNotification();
            notification.setPush_notifications_id(UUID.randomUUID());
            notification.setSenderName(pushDTO.getSenderName());
            notification.setReceiverIds(Collections.singletonList(receiverId)); // Utilisation de setReceiverIds pour une liste
            notification.setRecieverName(pushDTO.getRecieverName());
            notification.setSubject(pushDTO.getSubject());
            notification.setMessage(pushDTO.getMessage());
            notification.setGroupName(pushDTO.getGroupName());
            notification.setProperty(PropertyType.valueOf(pushDTO.getProperty().toUpperCase()));
            notification.setTimestamp(LocalDateTime.now());

    
            return notification;
        }).collect(Collectors.toList());
    
        // Enregistrement des notifications dans la base de données
        pushNotificationRepository.saveAll(notifications);
    
        // Envoi de chaque notification via WebSocket
        for (PushNotification notification : notifications) {
            messagingTemplate.convertAndSend("/topic/notifications/" + notification.getReceiverIds().get(0), notification); // Diffusion via WebSocket pour chaque destinataire
        }
    
        return notifications; // Retourner la liste des notifications
    }

    public List<Message> getNotificationsByReceiverId(UUID receiverId) {
    try {
        // Récupérer toutes les notifications pour le receiverId
        List<PushNotification> notifications = pushNotificationRepository.findByReceiverIdsContains(receiverId);

        // Convertir les PushNotification en Message (NotificationDTO)
        List<Message> messages = notifications.stream()
            .map(notification -> {
                Message message = new Message();
                message.setSubject(notification.getSubject());
                message.setMessage(notification.getMessage());
                message.setProperty(notification.getProperty().toString());  // Si PropertyType est un enum
                message.setTimestamp(notification.getTimestamp());
                return message;
            })
            .collect(Collectors.toList());

        return messages;
    } catch (Exception e) {
        System.err.println("Erreur dans la récupération des notifications pour l'ID " + receiverId + ": " + e.getMessage());
        throw new RuntimeException("Erreur lors de la récupération des notifications");
    }
}

    

    public List<PushNotification> getAllPushNotifications() {
        return pushNotificationRepository.findAll();
    }

    public Optional<PushNotification> getPushNotificationById(UUID id) {
        return pushNotificationRepository.findById(id);
    }

    public PushNotification updatePushNotification(UUID id, PushNotificationDTO pushDTO) {
        return pushNotificationRepository.findById(id).map(notification -> {
            notification.setSenderName(pushDTO.getSenderName()); // Correction ici
            notification.setRecieverName(pushDTO.getRecieverName()); // Utilisez setReceiverName
            notification.setSubject(pushDTO.getSubject());
            notification.setMessage(pushDTO.getMessage()); // Assurez-vous que cette propriété existe
            return pushNotificationRepository.save(notification);
        }).orElseThrow(() -> new RuntimeException("PushNotification not found with id " + id));
    }

    public void deletePushNotification(UUID id) {
        pushNotificationRepository.deleteById(id);
    }
}
