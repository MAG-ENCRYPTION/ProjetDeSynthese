package inc.yowyob.service.notification.infrastructure.persistence.entities;

import inc.yowyob.service.notification.infrastructure.config.DatabaseSchema;
import lombok.*;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.Table;

@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Data
@Table(DatabaseSchema.SMS_NOTIFICATION_TABLE)
public class SmsNotification extends Notification {

    @Column("sender_phone_number")
    private String senderPhoneNumber;

}
