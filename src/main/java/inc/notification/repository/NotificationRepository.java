package inc.notification.repository;

import java.util.UUID;

import org.springframework.data.cassandra.repository.CassandraRepository;

import inc.notification.model.Notification;

public interface NotificationRepository extends CassandraRepository<Notification, UUID> {
    // Recherche par type et priorité
    Notification findByMessage(String message);
}
