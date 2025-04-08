package inc.yowyob.service.notification.infrastructure.persistence.repository;

import java.util.UUID;

import inc.yowyob.service.notification.infrastructure.persistence.entities.PushNotification;
import inc.yowyob.service.notification.infrastructure.persistence.entities.keys.NotificationKey;
import org.springframework.data.cassandra.repository.CassandraRepository;

import inc.yowyob.service.notification.infrastructure.persistence.entities.Notification;
import org.springframework.data.cassandra.repository.ReactiveCassandraRepository;
import reactor.core.publisher.Flux;

public interface NotificationRepository extends ReactiveCassandraRepository<Notification, NotificationKey> {

    public Flux<Notification> findByKeyOrganizationIdAndKeyTemplateId(UUID organizationId, UUID templateId);

    public Flux<Notification> findByKeyOrganizationId(UUID organizationId);
}
