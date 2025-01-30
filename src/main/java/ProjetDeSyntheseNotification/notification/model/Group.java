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



    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName2) {
        this.groupName = groupName2;
    }



}
