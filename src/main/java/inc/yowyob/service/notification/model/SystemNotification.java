package inc.yowyob.service.notification.model;

import lombok.Data;

@Data
public class SystemNotification extends Notification {
    private String systemPart;

    public SystemNotification(String message, String systemPart) {
        super(message);
        this.systemPart = systemPart;
    }

    public SystemNotification() {
    }
}
