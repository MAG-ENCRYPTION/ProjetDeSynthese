package inc.yowyob.service.notification.application.exceptions;

import java.util.UUID;

public class ReminderNotFoundException extends RuntimeException {

    public ReminderNotFoundException(UUID emailReminderId) {
        super(String.format("Email reminder %s not found", emailReminderId));
    }
}
