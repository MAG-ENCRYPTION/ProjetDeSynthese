package inc.yowyob.service.notification.infrastructure.persistence.entities;

import inc.yowyob.service.notification.infrastructure.config.DatabaseSchema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.Table;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ToString
@Table(DatabaseSchema.WHATS_APP_NOTIFICATION_TABLE)// Nom de la table en base de données
public class WhatsappNotification extends Notification {
    @Column("sender_phone_number")
    private String senderPhoneNumber;
}

