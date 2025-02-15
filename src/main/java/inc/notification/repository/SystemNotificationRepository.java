package inc.notification.repository;

import java.util.UUID;

import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.stereotype.Repository;

import inc.notification.model.SystemNotification;

@Repository
public interface SystemNotificationRepository extends CassandraRepository<SystemNotification, UUID> {
    // Recherche par partie du système
    SystemNotification findBySystemPart(String systemPart);
}
