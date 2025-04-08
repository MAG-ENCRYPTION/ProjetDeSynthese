package inc.yowyob.service.notification.infrastructure.persistence.repository;

import inc.yowyob.service.notification.infrastructure.persistence.entities.EmailNotification;
import inc.yowyob.service.notification.infrastructure.persistence.entities.PushNotification;
import inc.yowyob.service.notification.infrastructure.persistence.entities.keys.NotificationKey;
import org.springframework.data.cassandra.repository.ReactiveCassandraRepository;
import reactor.core.publisher.Flux;

import java.util.UUID;


public interface EmailNotificationRepository extends ReactiveCassandraRepository<EmailNotification, NotificationKey> {
    public Flux<EmailNotification> findByKeyOrganizationIdAndKeyTemplateId(UUID organizationId, UUID templateId);

    public Flux<EmailNotification> findByKeyOrganizationId(UUID organizationId);
}
