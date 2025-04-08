package inc.yowyob.service.notification.infrastructure.persistence.repository;

import inc.yowyob.service.notification.infrastructure.persistence.entities.PushNotification;
import inc.yowyob.service.notification.infrastructure.persistence.entities.keys.NotificationKey;
import org.springframework.data.cassandra.repository.ReactiveCassandraRepository;
import reactor.core.publisher.Flux;

import java.util.UUID;

public interface PushNotificationRepository extends ReactiveCassandraRepository<PushNotification, NotificationKey> {

    public Flux<PushNotification> findByKeyOrganizationIdAndKeyTemplateId(UUID organizationId, UUID templateId);

    public Flux<PushNotification> findByKeyOrganizationId(UUID organizationId);

}
