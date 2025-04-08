package inc.yowyob.service.notification.infrastructure.persistence.repository;

import inc.yowyob.service.notification.infrastructure.persistence.entities.WsSetting;
import inc.yowyob.service.notification.infrastructure.persistence.entities.keys.SmsSettingKey;
import inc.yowyob.service.notification.infrastructure.persistence.entities.keys.WsSettingKey;
import org.springframework.data.cassandra.repository.AllowFiltering;
import org.springframework.data.cassandra.repository.ReactiveCassandraRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

import java.util.UUID;

/**
 * @author ETOUGUE
 */
@Repository
public interface WsSettingRepository extends ReactiveCassandraRepository<WsSetting, WsSettingKey> {
    @AllowFiltering
    public Flux<WsSetting> findByKeyOrganizationIdAndIsDefault(UUID organizationId, boolean isDefault);

    public Flux<WsSetting> findByKeyOrganizationId(UUID organizationId);
}