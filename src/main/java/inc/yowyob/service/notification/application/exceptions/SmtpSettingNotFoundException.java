package inc.yowyob.service.notification.application.exceptions;

import java.util.UUID;

public class SmtpSettingNotFoundException extends RuntimeException {

    public SmtpSettingNotFoundException() {
        super("Default smtp settings not found");
    }

    public SmtpSettingNotFoundException(UUID smtpSettingId) {
        super(String.format("Smtp setting %s not found", smtpSettingId));
    }
}
