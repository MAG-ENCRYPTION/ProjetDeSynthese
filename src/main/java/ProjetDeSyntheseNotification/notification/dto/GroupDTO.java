package ProjetDeSyntheseNotification.notification.dto;

public class GroupDTO extends PushNotificationDTO {
    private String groupName;

    public GroupDTO() {
    }

    public GroupDTO(Long id, String message, String type, int priority, String senderName, String receiverName, String subject, String groupName) {
        super(id, message, type, priority, senderName, receiverName, subject);
        this.groupName = groupName;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }
}
