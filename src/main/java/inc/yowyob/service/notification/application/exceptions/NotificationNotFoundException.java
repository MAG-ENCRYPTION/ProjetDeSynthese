package inc.yowyob.service.notification.application.exceptions;

import java.util.UUID;

public class NotificationNotFoundException extends RuntimeException {

    public NotificationNotFoundException(UUID notificationId) {
        super(String.format("Notification %s not found", notificationId));
    }
}
