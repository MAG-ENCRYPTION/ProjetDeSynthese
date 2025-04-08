package inc.yowyob.service.notification.infrastructure.persistence.repository;

import inc.yowyob.service.notification.infrastructure.persistence.entities.MetaSetting;
import inc.yowyob.service.notification.infrastructure.persistence.entities.keys.MetaSettingKey;
import org.springframework.data.cassandra.repository.AllowFiltering;
import org.springframework.data.cassandra.repository.ReactiveCassandraRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

import java.util.UUID;

/**
 * @author ETOUGUE
 */
@Repository
public interface MetaSettingRepository extends ReactiveCassandraRepository<MetaSetting, MetaSettingKey> {
    @AllowFiltering
    public Flux<MetaSetting> findByKeyOrganizationIdAndIsDefault(UUID organizationId, boolean isDefault);

    public Flux<MetaSetting> findByKeyOrganizationId(UUID organizationId);

}