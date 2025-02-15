package inc.notification.model;

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
@Table("email_notification")
public class EmailNotification {
    @PrimaryKey
    @Column("email_id")
    private UUID email_id;

    @Column("reciever_email")
    private String email;

    @Column("message")
    private String message;

    @Column ("subject")
    private String subject;
    
    @Column("type")
    private String type;      // Assuming these fields exist in CurrentNotification
    
    @Column("priority")
    private Float priority;    
}

