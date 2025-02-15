package inc.notification.model;

import org.springframework.data.cassandra.core.mapping.Table;

import lombok.Data;

@Data
@Table("notification_group") // 🔥 Éviter le conflit avec le mot-clé SQL `group`
public class Group extends PushNotification {
    private String groupName;

    public Group(String message, String type, int priority, String senderName, String receiverName, String subject, String groupName) {
        super();
        this.groupName = groupName;
    }

    public Group() {
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName2) {
        this.groupName = groupName2;
    }



}
