package inc.yowyob.service.notification.infrastructure.persistence.repository;

import inc.yowyob.service.notification.infrastructure.persistence.entities.Reminder;
import inc.yowyob.service.notification.infrastructure.persistence.entities.keys.ReminderKey;
import org.springframework.data.cassandra.repository.ReactiveCassandraRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

import java.util.UUID;

/**
 * @author ETOUGUE
 */
@Repository
public interface ReminderRepository extends ReactiveCassandraRepository<Reminder, ReminderKey> {
    public Flux<Reminder> findByKeyTemplateId(UUID templateId);
}