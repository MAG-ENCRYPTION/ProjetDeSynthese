package inc.yowyob.service.notification.application.service.impl;

import inc.yowyob.service.notification.application.dto.whatsapp.WhatsappNotificationRequest;
import inc.yowyob.service.notification.application.exceptions.WhatsappNotificationFoundException;
import inc.yowyob.service.notification.application.mappers.WhatsappNotificationMapper;
import inc.yowyob.service.notification.application.service.WhatsappNotificationService;
import inc.yowyob.service.notification.infrastructure.persistence.entities.WhatsappNotification;
import inc.yowyob.service.notification.infrastructure.persistence.entities.keys.NotificationKey;
import inc.yowyob.service.notification.infrastructure.persistence.repository.WhatsappNotificationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class WhatsappNotificationServiceImpl implements WhatsappNotificationService {

    private final WhatsappNotificationRepository whatsappNotificationRepository;

    private final WhatsappNotificationMapper whatsappNotificationMapper;

    @Override
    public Mono<WhatsappNotification> createWhatsappNotification(UUID organizationId, UUID templateId, WhatsappNotificationRequest request) {
        return whatsappNotificationRepository.insert(whatsappNotificationMapper.toEntity(organizationId, templateId, request));
    }

    @Override
    public Flux<WhatsappNotification> getAllWhatsappNotifications(UUID organizationId) {
        return whatsappNotificationRepository.findByKeyOrganizationId(organizationId);
    }

    @Override
    public Flux<WhatsappNotification> getAllWhatsappNotifications(UUID organizationId, UUID templateId) {
        return whatsappNotificationRepository.findByKeyOrganizationIdAndKeyTemplateId(organizationId, templateId);
    }


    @Override
    public Mono<WhatsappNotification> getWhatsappNotificationById(UUID organizationId, UUID templateId, UUID id) {
        return whatsappNotificationRepository.findById(NotificationKey.from(organizationId, templateId, id))
                .switchIfEmpty(Mono.error(new WhatsappNotificationFoundException(templateId, id)));
    }

    public Mono<Void> deleteWhatsappNotification(UUID organizationId, UUID templateId, UUID id) {
        return this.getWhatsappNotificationById(organizationId, templateId, id).flatMap(whatsappNotificationRepository::delete);
    }

}
