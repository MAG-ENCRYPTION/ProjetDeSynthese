package inc.yowyob.service.notification.infrastructure.persistence.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import inc.yowyob.scylladb.entity.Entity;
import inc.yowyob.service.notification.application.enums.NotificationStatus;
import inc.yowyob.service.notification.application.enums.NotificationType;
import inc.yowyob.service.notification.application.enums.PriorityLevel;
import inc.yowyob.service.notification.infrastructure.config.DatabaseSchema;
import inc.yowyob.service.notification.infrastructure.persistence.entities.keys.NotificationKey;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Delegate;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

import java.util.Map;
import java.util.Set;
import java.util.UUID;

@Data
@EqualsAndHashCode(callSuper = false)
@Table(DatabaseSchema.NOTIFICATION_TABLE) // Specify the table name in Cassandra
public class Notification extends Entity {

    @PrimaryKey
    @Delegate
    protected NotificationKey key;

    @Column("subject")
    protected String subject;

    @Column("body")
    protected String body;

    @Column("recipients")
    protected Set<String> recipients;

    @Column("type")
    protected NotificationType type;

    @Column("priority")
    protected PriorityLevel priority;

    @JsonProperty("metadata")
    protected Map<String, String> metadata;

    @JsonProperty("status")
    protected NotificationStatus status;

}