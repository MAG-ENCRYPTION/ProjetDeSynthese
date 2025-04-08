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
public class ReminderKey {

    @PrimaryKeyColumn(name = "template_id", value = "template_id", ordinal = 0, type = PrimaryKeyType.PARTITIONED)
    private UUID templateId;

    @PrimaryKeyColumn(name = "id", value = "id", ordinal = 0, type = PrimaryKeyType.CLUSTERED)
    private UUID id;

    public static ReminderKey from(UUID templateId, UUID id) {
        return new ReminderKey(templateId, id);
    }

}
