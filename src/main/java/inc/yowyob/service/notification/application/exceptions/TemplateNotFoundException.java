package inc.yowyob.service.notification.application.exceptions;

import java.util.UUID;

public class TemplateNotFoundException extends RuntimeException {

    public TemplateNotFoundException() {
        super("Default email template not found");
    }

    public TemplateNotFoundException(UUID emailTemplateId) {
        super(String.format("Email template %s not found", emailTemplateId));
    }
}
