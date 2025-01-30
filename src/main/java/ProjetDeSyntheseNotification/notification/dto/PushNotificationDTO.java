package ProjetDeSyntheseNotification.notification.dto;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;


public class PushNotificationDTO extends CurrentNotificationDTO {

    private Long id;
    private String senderName;
    private String receiverName;
    private String subject;

    public PushNotificationDTO() {
    }

    public PushNotificationDTO(Long id, String message, String type, int priority, String senderName, String receiverName, String subject) {
        super(id, message, type, priority);
        this.senderName = senderName;
        this.receiverName = receiverName;
        this.subject = subject;
    }

    public String getSenderName() {
        return senderName;
    }

    public void setSenderName(String senderName) {
        this.senderName = senderName;
    }

    public String getReceiverName() {
        return receiverName;
    }

    public void setReceiverName(String receiverName) {
        this.receiverName = receiverName;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }
}
