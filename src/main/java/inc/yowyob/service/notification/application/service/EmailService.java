package inc.yowyob.service.notification.application.service;

import inc.yowyob.service.notification.application.dto.email.EmailNotificationRequest;
import inc.yowyob.service.notification.infrastructure.persistence.entities.EmailNotification;
import jakarta.mail.MessagingException;
import reactor.core.publisher.Mono;

import java.util.UUID;

public interface EmailService {
    public Mono<Void> sendEmail(UUID organizationId, EmailNotificationRequest emailNotification) throws MessagingException;
}
