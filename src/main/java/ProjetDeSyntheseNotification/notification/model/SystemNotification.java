package ProjetDeSyntheseNotification.notification.model;

import jakarta.persistence.*;

@Entity
public class SystemNotification extends Notification {
    private String systemPart;

    public SystemNotification(String message, String systemPart) {
        super(message);
        this.systemPart = systemPart;
    }

    public SystemNotification() {
    }
}
