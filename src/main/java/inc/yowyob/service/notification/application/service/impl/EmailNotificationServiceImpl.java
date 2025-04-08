package inc.yowyob.service.notification.application.service.impl;

import inc.yowyob.service.notification.application.dto.email.EmailNotificationRequest;
import inc.yowyob.service.notification.application.exceptions.EmailNotificationFoundException;
import inc.yowyob.service.notification.application.mappers.EmailNotificationMapper;
import inc.yowyob.service.notification.application.service.EmailNotificationService;
import inc.yowyob.service.notification.infrastructure.persistence.entities.EmailNotification;
import inc.yowyob.service.notification.infrastructure.persistence.entities.keys.NotificationKey;
import inc.yowyob.service.notification.infrastructure.persistence.repository.EmailNotificationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class EmailNotificationServiceImpl implements EmailNotificationService {

    private final EmailNotificationRepository emailNotificationRepository;

    private final EmailNotificationMapper emailNotificationMapper;

    @Override
    public Mono<EmailNotification> createEmailNotification(UUID organizationId, UUID templateId, EmailNotificationRequest request) {
        return emailNotificationRepository.insert(emailNotificationMapper.toEntity(organizationId, templateId, request));
    }

    @Override
    public Flux<EmailNotification> getAllEmailNotifications(UUID organizationId, UUID templateId) {
        return emailNotificationRepository.findByKeyOrganizationIdAndKeyTemplateId(organizationId, templateId);
    }

    @Override
    public Flux<EmailNotification> getAllEmailNotifications(UUID organizationId) {
        return emailNotificationRepository.findByKeyOrganizationId(organizationId);
    }

    @Override
    public Mono<EmailNotification> getEmailNotificationById(UUID organizationId, UUID templateId, UUID id) {
        return emailNotificationRepository.findById(NotificationKey.from(organizationId, templateId, id))
                .switchIfEmpty(Mono.error(new EmailNotificationFoundException(templateId, id)));
    }

    public Mono<Void> deleteEmailNotification(UUID organizationId, UUID templateId, UUID id) {
        return this.getEmailNotificationById(organizationId, templateId, id).flatMap(emailNotificationRepository::delete);
    }

}
