package inc.yowyob.service.notification.model;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

import inc.yowyob.service.notification.model.enums.PropertyType;
import inc.yowyob.service.notification.model.enums.PushNotificationType;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
@Table("push_notifications")
public class PushNotification {
    @PrimaryKey
    @Column("push_notifications_id")
    private UUID push_notifications_id;
    @Column("receiver_ids")
    private List<UUID> receiverIds;  // Liste de UUID pour plusieurs destinataires
    @Column("sender_name")
    private String senderName;
    @Column("group_name")
    private String groupName;
    @Column("priority")
    private int priority;  
    @Column("property_type")
    private PropertyType property;
    @Column("receiver_name")
    private String recieverName;
    @Column("message")
    private String message;
    @Column("push_notification_type")
    private PushNotificationType type;
    @Column("subject")
    private String subject;
    @Column("timestamp")
    private LocalDateTime timestamp; 

    
}
