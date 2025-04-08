package inc.yowyob.service.notification.infrastructure.persistence.repository;

import inc.yowyob.service.notification.infrastructure.persistence.entities.FirebaseSetting;
import inc.yowyob.service.notification.infrastructure.persistence.entities.keys.FirebaseSettingKey;
import org.springframework.data.cassandra.repository.AllowFiltering;
import org.springframework.data.cassandra.repository.ReactiveCassandraRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

import java.util.UUID;

/**
 * @author ETOUGUE
 */
@Repository
public interface FirebaseSettingRepository extends ReactiveCassandraRepository<FirebaseSetting, FirebaseSettingKey> {
    @AllowFiltering
    public Flux<FirebaseSetting> findByKeyOrganizationId(UUID organizationId);

    public Flux<FirebaseSetting> findByKeyOrganizationIdAndIsDefault(UUID organizationId, boolean isDefault);

}