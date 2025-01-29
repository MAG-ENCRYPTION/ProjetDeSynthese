package ProjetDeSyntheseNotification.notification.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "sms_notifications")
public class SmsNotification extends CurrentNotification {
    private String number;

    public SmsNotification(String message, String type, int priority, String number) {
        super(message, type, priority);
        this.number = number;
    }

    public SmsNotification() {
        super(); // Appelle le constructeur par défaut de CurrentNotification
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }
}
