package inc.yowyob.service.notification.application.exceptions;

import java.util.UUID;

public class ServiceNotificationNotFoundException extends RuntimeException {

    public ServiceNotificationNotFoundException(String service, UUID serviceNotificationId) {
        super(String.format("Service notification %s not found for service %s", serviceNotificationId, service));
    }
}
