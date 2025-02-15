package inc.notification.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.cassandra.repository.CassandraRepository;

import inc.notification.dto.PushNotificationDTO;
import inc.notification.model.PushNotification;

public interface PushNotificationRepository extends CassandraRepository<PushNotification, UUID> {
    // Recherche par type et priorité
    PushNotification findBySenderNameAndRecieverName(String senderName, String recieverName);
    List<PushNotification> findByReceiverIdsContains(UUID receiverId);

    public PushNotification save(PushNotificationDTO pushDTO);
}
