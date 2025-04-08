package inc.yowyob.service.notification.infrastructure.persistence.repository;

import inc.yowyob.service.notification.infrastructure.persistence.entities.DesignTemplate;
import inc.yowyob.service.notification.infrastructure.persistence.entities.keys.DesignTemplateKey;
import org.springframework.data.cassandra.repository.AllowFiltering;
import org.springframework.data.cassandra.repository.ReactiveCassandraRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

import java.util.UUID;

/**
 * @author ETOUGUE
 */
@Repository
public interface DesignTemplateRepository extends ReactiveCassandraRepository<DesignTemplate, DesignTemplateKey> {

    public Flux<DesignTemplate> findByKeyOrganizationId(UUID organizationId);

    @AllowFiltering
    public Flux<DesignTemplate> findByKeyOrganizationIdAndIsDefault(UUID organizationId, boolean isDefault);
}