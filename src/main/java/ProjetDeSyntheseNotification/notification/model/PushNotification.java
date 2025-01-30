package ProjetDeSyntheseNotification.notification.model;

import jakarta.persistence.*;

@Entity
public class PushNotification extends CurrentNotification {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    protected String senderName;
    protected String receiverName;
    protected String subject;

    public PushNotification(String message, String type, int priority, String senderName, String receiverName, String subject) {
    }


    public PushNotification(Long id,String message, String type, int priority, String senderName, String receiverName, String subject) {
        super(message, type, priority);
        this.id=id;
        this.senderName = senderName;
        this.receiverName = receiverName;
        this.subject = subject;
    }

    public Long getId() {
        return id;
    }
    public String getSenderName() {
        return senderName;
    }

    public void setId(Long id) {
        this.id = id;
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

    public void setReceiverName(String receiverName) {
        this.receiverName = receiverName;
    }


    public void setSenderName(String receiverName2) {
        this.receiverName = receiverName2;
    }

    public void setPriority(int priority) {
    }

    public void setType(String type) {
    }
}
