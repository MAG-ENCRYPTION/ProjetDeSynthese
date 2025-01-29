package ProjetDeSyntheseNotification.notification.model;

import jakarta.persistence.*;

@Entity
public class CurrentNotification extends Notification {
    protected String type;
    protected int priority;

    public CurrentNotification(String message, String type, int priority) {
        super(message);
        this.type = type;
        this.priority = priority;
    }

    public CurrentNotification() {
    }
}
