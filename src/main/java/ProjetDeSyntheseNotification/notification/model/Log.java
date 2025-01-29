package ProjetDeSyntheseNotification.notification.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;

@Entity
public class Log {
    @Id
    @GeneratedValue
    private Long id;
    private String message;
    private String timestamp;
}
