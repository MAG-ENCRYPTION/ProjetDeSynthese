package inc.yowyob.service.notification.application.service;

import inc.yowyob.service.notification.application.dto.sms.SmsNotificationRequest;
import inc.yowyob.service.notification.infrastructure.persistence.entities.SmsNotification;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;


public interface SmsNotificationService {

    public Mono<SmsNotification> createSmsNotification(UUID organizationId, UUID templateId, SmsNotificationRequest request);

    public Flux<SmsNotification> getAllSmsNotifications(UUID organizationId, UUID templateId);

    public Flux<SmsNotification> getAllSmsNotifications(UUID organizationId);

    public Mono<SmsNotification> getSmsNotificationById(UUID organizationId, UUID templateId, UUID id);

    public Mono<Void> deleteSmsNotification(UUID organizationId, UUID templateId, UUID id);

}
