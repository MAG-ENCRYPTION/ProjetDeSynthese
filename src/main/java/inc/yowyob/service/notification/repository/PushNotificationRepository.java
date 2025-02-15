package inc.yowyob.service.notification.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.cassandra.repository.CassandraRepository;

import inc.yowyob.service.notification.dto.PushNotificationDTO;
import inc.yowyob.service.notification.model.PushNotification;

public interface PushNotificationRepository extends CassandraRepository<PushNotification, UUID> {
    // Recherche par type et priorité
    PushNotification findBySenderNameAndRecieverName(String senderName, String recieverName);
    List<PushNotification> findByReceiverIdsContains(UUID receiverId);

    public PushNotification save(PushNotificationDTO pushDTO);
}
