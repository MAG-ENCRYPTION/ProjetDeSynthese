package inc.notification.dto;

public class GroupDTO extends PushNotificationDTO {
    private String groupName;

    public GroupDTO() {
    }

    public GroupDTO(Long id, String message, String type, Float priority, String senderName, String receiverName, String subject, String groupName) {
        super();
        this.groupName = groupName;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }
}
