package inc.yowyob.service.notification.application.service.impl;

import inc.yowyob.service.notification.application.dto.push.PushNotificationRequest;
import inc.yowyob.service.notification.application.exceptions.PushNotificationFoundException;
import inc.yowyob.service.notification.application.mappers.PushNotificationMapper;
import inc.yowyob.service.notification.application.service.PushNotificationService;
import inc.yowyob.service.notification.infrastructure.persistence.entities.PushNotification;
import inc.yowyob.service.notification.infrastructure.persistence.entities.keys.NotificationKey;
import inc.yowyob.service.notification.infrastructure.persistence.repository.PushNotificationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PushNotificationServiceImpl implements PushNotificationService {

    private final PushNotificationRepository pushNotificationRepository;

    private final PushNotificationMapper pushNotificationMapper;

    @Override
    public Mono<PushNotification> createPushNotification(UUID templateId, UUID organizationId, PushNotificationRequest request) {
        return pushNotificationRepository.insert(pushNotificationMapper.toEntity(organizationId, templateId, request));
    }

    @Override
    public Flux<PushNotification> getAllPushNotifications(UUID organizationId, UUID templateId) {
        return pushNotificationRepository.findByKeyOrganizationIdAndKeyTemplateId(organizationId, templateId);
    }

    @Override
    public Flux<PushNotification> getAllPushNotifications(UUID organizationId) {
        return pushNotificationRepository.findByKeyOrganizationId(organizationId);
    }

    @Override
    public Mono<PushNotification> getPushNotificationById(UUID organizationId, UUID templateId, UUID id) {
        return pushNotificationRepository.findById(NotificationKey.from(organizationId, templateId, id))
                .switchIfEmpty(Mono.error(new PushNotificationFoundException(templateId, id)));
    }

    public Mono<Void> deletePushNotification(UUID organizationId, UUID templateId, UUID id) {
        return this.getPushNotificationById(organizationId, templateId, id).flatMap(pushNotificationRepository::delete);
    }

}
