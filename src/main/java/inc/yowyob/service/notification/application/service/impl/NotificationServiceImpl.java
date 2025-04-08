package inc.yowyob.service.notification.application.service.impl;

import inc.yowyob.service.notification.application.dto.notification.NotificationRequest;
import inc.yowyob.service.notification.application.exceptions.NotificationNotFoundException;
import inc.yowyob.service.notification.application.mappers.NotificationMapper;
import inc.yowyob.service.notification.application.service.NotificationService;
import inc.yowyob.service.notification.infrastructure.persistence.entities.Notification;
import inc.yowyob.service.notification.infrastructure.persistence.entities.keys.NotificationKey;
import inc.yowyob.service.notification.infrastructure.persistence.repository.NotificationRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

/**
 * Service implementation for managing Notification entities.
 *
 * @author ETOUGUE
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class NotificationServiceImpl implements NotificationService {

    private final NotificationRepository notificationRepository;

    private final NotificationMapper notificationMapper;

    @Override
    public Mono<Notification> createNotification(UUID organizationId, UUID templateId, NotificationRequest request) {
        log.info("Creating Notification: {}", request);
        return notificationRepository.insert(notificationMapper.toEntity(organizationId, templateId, request));
    }

    @Override
    public Mono<Notification> getNotificationById(UUID organizationId, UUID templateId, UUID id) {
        log.info("Fetching Notification with ID: {}", id);
        return notificationRepository.findById(NotificationKey.from(organizationId, templateId, id))
                .switchIfEmpty(Mono.error(new NotificationNotFoundException(id)));
    }

    @Override
    public Flux<Notification> getAllNotifications(UUID organizationId, UUID templateId) {
        log.info("Fetching all Notifications");
        return notificationRepository.findByKeyOrganizationIdAndKeyTemplateId(organizationId, templateId);
    }

    @Override
    public Flux<Notification> getAllNotifications(UUID organizationId) {
        log.info("Fetching all Notifications for organization {}", organizationId);
        return notificationRepository.findByKeyOrganizationId(organizationId);
    }

    @Override
    public Mono<Void> deleteNotification(UUID organizationId, UUID templateId, UUID id) {
        log.info("Deleting Notification with ID: {}", id);
        return this.getNotificationById(organizationId, templateId, id).flatMap(notificationRepository::delete);
    }
}
