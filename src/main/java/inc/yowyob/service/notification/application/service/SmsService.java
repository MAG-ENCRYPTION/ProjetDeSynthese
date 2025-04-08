package inc.yowyob.service.notification.application.service;

import inc.yowyob.service.notification.application.dto.sms.SmsNotificationRequest;
import reactor.core.publisher.Mono;

import java.util.UUID;

public interface SmsService {
    public Mono<Void> sendSmsNotification(UUID organizationId, SmsNotificationRequest request);
}
