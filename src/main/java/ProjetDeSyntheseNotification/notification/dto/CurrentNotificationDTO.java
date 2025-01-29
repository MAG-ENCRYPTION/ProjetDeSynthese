package ProjetDeSyntheseNotification.notification.dto;

public class CurrentNotificationDTO extends NotificationDTO {
    private String type;
    private int priority;

    public CurrentNotificationDTO() {
    }

    public CurrentNotificationDTO(Long id, String message, String type, int priority) {
        super(id, message);
        this.type = type;
        this.priority = priority;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }
}
