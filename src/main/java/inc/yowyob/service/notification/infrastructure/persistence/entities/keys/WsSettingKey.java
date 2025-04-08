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
public class WsSettingKey {

    @PrimaryKeyColumn(name = "organization_id", value = "organization_id", ordinal = 0, type = PrimaryKeyType.PARTITIONED)
    private UUID organizationId;

    @PrimaryKeyColumn(name = "system_id", value = "system_id", ordinal = 0, type = PrimaryKeyType.CLUSTERED)
    private String systemId;

    public static WsSettingKey from(UUID organizationId, String systemId) {
        return new WsSettingKey(organizationId, systemId);
    }

}
