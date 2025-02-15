package inc.notification.model;

import java.util.UUID;

import org.springframework.data.cassandra.core.mapping.PrimaryKey;

import lombok.Data;

@Data
public class Log {
    @PrimaryKey
    private UUID id;
    private String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    private String timestamp;
}
