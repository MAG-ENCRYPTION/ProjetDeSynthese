package ProjetDeSyntheseNotification.notification.model;

import jakarta.persistence.*;

@Entity
@Table(name = "notification_group") // 🔥 Éviter le conflit avec le mot-clé SQL `group`
public class Group extends PushNotification {
    private String groupName;

    public Group(String message, String type, int priority, String senderName, String receiverName, String subject, String groupName) {
        super(message, type, priority, senderName, receiverName, subject);
        this.groupName = groupName;
    }

    public Group() {
    }

    public Object getGroupName() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getGroupName'");
    }

    public void setGroupName(Object groupName2) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setGroupName'");
    }
}
