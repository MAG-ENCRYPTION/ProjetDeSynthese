package inc.yowyob.service.notification.infrastructure.persistence.entities;

import inc.yowyob.service.notification.infrastructure.config.DatabaseSchema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.Table;

@Data
@EqualsAndHashCode(callSuper = true)
@Table(DatabaseSchema.PUSH_NOTIFICATION_TABLE)
public class PushNotification extends Notification {
    @Column("token")
    private String token;
}
