package inc.yowyob.service.notification.application.exceptions;

import java.util.UUID;

public class SmsNotificationFoundException extends RuntimeException {

    public SmsNotificationFoundException(UUID templateId, UUID id) {
        super(String.format("Sms notification not found for template %s and id %s", templateId, id));
    }
}
