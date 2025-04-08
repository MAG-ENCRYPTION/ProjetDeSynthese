package inc.yowyob.service.notification.application.service;

import inc.yowyob.service.notification.application.dto.push.PushNotificationRequest;
import inc.yowyob.service.notification.infrastructure.persistence.entities.PushNotification;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;


public interface PushNotificationService {

    public Mono<PushNotification> createPushNotification(UUID organizationId, UUID templateId, PushNotificationRequest request);

    public Flux<PushNotification> getAllPushNotifications(UUID organizationId, UUID templateId);

    public Flux<PushNotification> getAllPushNotifications(UUID organizationId);

    public Mono<PushNotification> getPushNotificationById(UUID organizationId, UUID templateId, UUID id);

    public Mono<Void> deletePushNotification(UUID organizationId, UUID templateId, UUID id);

}
