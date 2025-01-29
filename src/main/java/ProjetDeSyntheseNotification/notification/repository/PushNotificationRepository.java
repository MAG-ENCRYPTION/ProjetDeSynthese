package ProjetDeSyntheseNotification.notification.repository;

import ProjetDeSyntheseNotification.notification.model.PushNotification;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ProjetDeSyntheseNotification.notification.dto.PushNotificationDTO;

@Repository
public interface PushNotificationRepository extends JpaRepository<PushNotification, Long> {
    // Recherche par expéditeur ou destinataire
    PushNotification findBySenderNameAndReceiverName(String senderName, String receiverName);

    public PushNotification save(PushNotificationDTO pushDTO);
}
