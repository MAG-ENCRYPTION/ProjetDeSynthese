package inc.yowyob.service.notification.application.service;

import inc.yowyob.service.notification.application.dto.push.PushNotificationRequest;
import reactor.core.publisher.Mono;

import java.util.UUID;

public interface PusherService {
    public Mono<Void> sendPushNotification(UUID organizationId, PushNotificationRequest request);
}
