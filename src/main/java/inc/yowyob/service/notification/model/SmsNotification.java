package inc.yowyob.service.notification.model;

import java.util.UUID;

import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table("sms_notifications")
public class SmsNotification {
    
    @PrimaryKey
    @Column("notification_id")
    private UUID notificationId;
    @Column("number")
    private String number;
    @Column("message")
    private String message;
    @Column("type")
    private String type;
    @Column("priority")
    private Float priority;


}
