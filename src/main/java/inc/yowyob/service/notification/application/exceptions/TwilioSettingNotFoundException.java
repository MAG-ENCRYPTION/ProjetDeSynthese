package inc.yowyob.service.notification.application.exceptions;

import java.util.UUID;

public class TwilioSettingNotFoundException extends RuntimeException {

    public TwilioSettingNotFoundException() {
        super("Default twilio settings not found");
    }

    public TwilioSettingNotFoundException(UUID smtpSettingId) {
        super(String.format("Twilio setting %s not found", smtpSettingId));
    }
}
