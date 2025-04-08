package inc.yowyob.service.notification.application.exceptions;

import java.util.UUID;

public class MetaSettingNotFoundException extends RuntimeException {

    public MetaSettingNotFoundException() {
        super("Default meta settings not found");
    }

    public MetaSettingNotFoundException(UUID smtpSettingId) {
        super(String.format("Meta setting %s not found", smtpSettingId));
    }
}
