package inc.yowyob.service.notification.infrastructure.persistence.entities;

import inc.yowyob.service.notification.infrastructure.config.DatabaseSchema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.Table;

import java.util.Set;
import java.util.UUID;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Table(DatabaseSchema.EMAIL_NOTIFICATION_TABLE)
public class EmailNotification extends Notification {

    @Column("cc")
    private Set<String> cc;

    @Column("bcc")
    private Set<String> bcc;

    @Column("sender_name")
    private String senderName;

    @Column("sender_email")
    private String senderEmail;

}

