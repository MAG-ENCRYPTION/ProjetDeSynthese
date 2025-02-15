package inc.notification.model;

import java.util.UUID;
import org.springframework.data.cassandra.core.mapping.Table;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import lombok.Data;

@Data
@Table("notification") // Specify the table name in Cassandra
public class Notification {

    @PrimaryKey // Marks this field as the primary key
    private UUID id;

    protected String message;

    // Constructor to initialize message
    public Notification(String message) {
        this.message = message;
    }

    // Default constructor
    public Notification() {
    }
}