package inc.yowyob.service.notification.application.exceptions;

import java.util.UUID;

public class EmailNotificationFoundException extends RuntimeException {

    public EmailNotificationFoundException() {
        super("Default email notification not found");
    }

    public EmailNotificationFoundException(UUID templateId, UUID id) {
        super(String.format("Default email notification not found for template %s and id %s", templateId, id));
    }
}
