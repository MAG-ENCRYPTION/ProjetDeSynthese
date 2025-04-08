package inc.yowyob.service.notification.infrastructure.persistence.repository;

import inc.yowyob.service.notification.infrastructure.persistence.entities.PushNotification;
import inc.yowyob.service.notification.infrastructure.persistence.entities.WhatsappNotification;
import inc.yowyob.service.notification.infrastructure.persistence.entities.keys.NotificationKey;
import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.data.cassandra.repository.ReactiveCassandraRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

import java.util.UUID;

@Repository
public interface WhatsappNotificationRepository extends ReactiveCassandraRepository<WhatsappNotification, NotificationKey> {

    public Flux<WhatsappNotification> findByKeyOrganizationIdAndKeyTemplateId(UUID organizationId, UUID templateId);

    public Flux<WhatsappNotification> findByKeyOrganizationId(UUID organizationId);

}
