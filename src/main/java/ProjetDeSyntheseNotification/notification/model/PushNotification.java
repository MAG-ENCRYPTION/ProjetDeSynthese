package ProjetDeSyntheseNotification.notification.model;

import jakarta.persistence.*;

@Entity
public class PushNotification extends CurrentNotification {
    protected String senderName;
    protected String receiverName;
    protected String subject;

    public PushNotification(String message, String type, int priority, String senderName, String receiverName, String subject) {
        super(message, type, priority);
        this.senderName = senderName;
        this.receiverName = receiverName;
        this.subject = subject;
    }

    public PushNotification() {
    }


    public String getMessage() {
        return message;
    }

    public String getSubject() {
        return subject;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setSubject(String subject2) {
        this.subject = subject2;
    }

    public String getReceiverName() {
        return receiverName;
    }

    public void setSenderName(String receiverName2) {
        this.receiverName = receiverName2;
    }
}
