package inc.yowyob.service.notification.infrastructure.persistence.repository;

import inc.yowyob.service.notification.infrastructure.persistence.entities.SmsNotification;
import inc.yowyob.service.notification.infrastructure.persistence.entities.keys.NotificationKey;
import org.springframework.data.cassandra.repository.ReactiveCassandraRepository;
import reactor.core.publisher.Flux;

import java.util.UUID;


public interface SmsNotificationRepository extends ReactiveCassandraRepository<SmsNotification, NotificationKey> {

    public Flux<SmsNotification> findByKeyOrganizationIdAndKeyTemplateId(UUID organizationId, UUID templateId);

    public Flux<SmsNotification> findByKeyOrganizationId(UUID organizationId);

}


