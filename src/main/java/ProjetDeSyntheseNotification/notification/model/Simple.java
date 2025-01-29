package ProjetDeSyntheseNotification.notification.model;

import jakarta.persistence.*;

@Entity
public class Simple extends PushNotification {
    public Simple(String message, String type, int priority, String senderName, String receiverName, String subject) {
        super(message, type, priority, senderName, receiverName, subject);
    }

    public Simple() {
    }



}
