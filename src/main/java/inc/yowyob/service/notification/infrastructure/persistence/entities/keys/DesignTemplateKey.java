package inc.yowyob.service.notification.infrastructure.persistence.entities.keys;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;
import org.springframework.data.cassandra.core.cql.PrimaryKeyType;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyClass;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn;

import java.util.UUID;

/**
 * @author ETOUGUE
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@PrimaryKeyClass
@ToString
@SuperBuilder
public class DesignTemplateKey {
    @PrimaryKeyColumn(name = "organization_id", value = "organization_id", ordinal = 0, type = PrimaryKeyType.PARTITIONED)
    private UUID organizationId;

    @PrimaryKeyColumn(name = "id", value = "id", ordinal = 0, type = PrimaryKeyType.CLUSTERED)
    private UUID id;

    public static DesignTemplateKey from(UUID organizationId, UUID id) {
        return new DesignTemplateKey(organizationId, id);
    }

}
