package inc.yowyob.service.notification.infrastructure.persistence.repository;

import inc.yowyob.service.notification.infrastructure.persistence.entities.Template;
import inc.yowyob.service.notification.infrastructure.persistence.entities.keys.TemplateKey;
import org.springframework.data.cassandra.repository.AllowFiltering;
import org.springframework.data.cassandra.repository.ReactiveCassandraRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

import java.util.UUID;

/**
 * @author ETOUGUE
 */
@Repository
public interface TemplateRepository extends ReactiveCassandraRepository<Template, TemplateKey> {

    public Flux<Template> findByKeyOrganizationId(UUID organizationId);

    @AllowFiltering
    public Flux<Template> findByKeyOrganizationIdAndIsDefault(UUID organizationId, boolean isDefault);
}