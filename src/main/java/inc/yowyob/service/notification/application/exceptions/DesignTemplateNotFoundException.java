package inc.yowyob.service.notification.application.exceptions;

import java.util.UUID;

public class DesignTemplateNotFoundException extends RuntimeException {

    public DesignTemplateNotFoundException() {
        super("Default design template not found");
    }

    public DesignTemplateNotFoundException(UUID designTemplateId) {
        super(String.format("Design template %s not found", designTemplateId));
    }
}
