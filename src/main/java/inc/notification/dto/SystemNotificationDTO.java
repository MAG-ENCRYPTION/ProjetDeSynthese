package inc.notification.dto;

public class SystemNotificationDTO extends NotificationDTO {
    private String systemPart;

    public SystemNotificationDTO() {
    }

    public SystemNotificationDTO(Long id, String message, String systemPart) {
        super(id, message);
        this.systemPart = systemPart;
    }

    public String getSystemPart() {
        return systemPart;
    }

    public void setSystemPart(String systemPart) {
        this.systemPart = systemPart;
    }
}
