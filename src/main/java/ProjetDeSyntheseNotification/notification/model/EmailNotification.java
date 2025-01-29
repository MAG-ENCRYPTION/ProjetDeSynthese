package ProjetDeSyntheseNotification.notification.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class EmailNotification extends CurrentNotification {
    @Id
    private Long id;
    private String email;
    private String subject;


    public void setSubject(String Subject) {
        this.subject = Subject;
    }
    public void setEmail(String email2) {
        this.email = email2;
    }
    public void setType(String type) {
        this.type = type;
    }
    public void setPriority(int priority) {
        this.priority = priority;
    }

    public String getEmail() {
        return email;
    }

    public String getSubject() {
        return subject;
    }

    public String getType() {
        return type;
    }
    public int getPriority() {
        return priority;
    }
}
