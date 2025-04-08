package inc.yowyob.service.notification.infrastructure.persistence.repository;

import inc.yowyob.service.notification.infrastructure.persistence.entities.SmtpSetting;
import inc.yowyob.service.notification.infrastructure.persistence.entities.keys.SmtpSettingKey;
import org.springframework.data.cassandra.repository.AllowFiltering;
import org.springframework.data.cassandra.repository.ReactiveCassandraRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

import java.util.UUID;

/**
 * @author ETOUGUE
 */
@Repository
public interface SmtpSettingRepository extends ReactiveCassandraRepository<SmtpSetting, SmtpSettingKey> {
    @AllowFiltering
    public Flux<SmtpSetting> findByKeyOrganizationIdAndIsDefault(UUID organizationId, boolean isDefault);

    public Flux<SmtpSetting> findByKeyOrganizationId(UUID organizationId);

}