package inc.yowyob.service.notification.application.exceptions;

import java.util.UUID;

public class FirebaseSettingNotFoundException extends RuntimeException {

    public FirebaseSettingNotFoundException() {
        super("Default firebase settings not found");
    }

    public FirebaseSettingNotFoundException(UUID smtpSettingId) {
        super(String.format("Firebase setting %s not found", smtpSettingId));
    }
}
