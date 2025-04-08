package inc.yowyob.service.notification.application.service;

import inc.yowyob.service.notification.application.dto.email.EmailNotificationRequest;
import inc.yowyob.service.notification.infrastructure.persistence.entities.EmailNotification;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;


public interface EmailNotificationService {

    public Mono<EmailNotification> createEmailNotification(UUID organizationId, UUID templateId, EmailNotificationRequest request);

    public Flux<EmailNotification> getAllEmailNotifications(UUID organizationId, UUID templateId);

    public Flux<EmailNotification> getAllEmailNotifications(UUID organizationId);

    public Mono<EmailNotification> getEmailNotificationById(UUID organizationId, UUID templateId, UUID id);

    public Mono<Void> deleteEmailNotification(UUID organizationId, UUID templateId, UUID id);

}
