package inc.yowyob.service.notification.infrastructure.persistence.repository;

import inc.yowyob.service.notification.infrastructure.persistence.entities.TwilioSetting;
import inc.yowyob.service.notification.infrastructure.persistence.entities.keys.SmsSettingKey;
import org.springframework.data.cassandra.repository.AllowFiltering;
import org.springframework.data.cassandra.repository.ReactiveCassandraRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

import java.util.UUID;

/**
 * @author ETOUGUE
 */
@Repository
public interface TwilioSettingRepository extends ReactiveCassandraRepository<TwilioSetting, SmsSettingKey> {
    @AllowFiltering
    public Flux<TwilioSetting> findByKeyOrganizationIdAndIsDefault(UUID organizationId, boolean isDefault);

    public Flux<TwilioSetting> findByKeyOrganizationId(UUID organizationId);
}