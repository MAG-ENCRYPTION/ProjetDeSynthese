package inc.yowyob.service.notification.application.exceptions;

import java.util.UUID;

public class PushNotificationFoundException extends RuntimeException {

    public PushNotificationFoundException(UUID templateId, UUID id) {
        super(String.format("Push notification not found for template %s and id %s", templateId, id));
    }
}
