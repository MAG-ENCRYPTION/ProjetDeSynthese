package inc.yowyob.service.notification.application.exceptions;

import java.util.UUID;

public class WhatsappNotificationFoundException extends RuntimeException {

    public WhatsappNotificationFoundException(UUID templateId, UUID id) {
        super(String.format("Whatsapp notification not found for template %s and id %s", templateId, id));
    }
}
