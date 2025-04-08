package inc.yowyob.service.notification.application.service.impl;

import inc.yowyob.service.notification.application.dto.sms.SmsNotificationRequest;
import inc.yowyob.service.notification.application.exceptions.SmsNotificationFoundException;
import inc.yowyob.service.notification.application.mappers.SmsNotificationMapper;
import inc.yowyob.service.notification.application.service.SmsNotificationService;
import inc.yowyob.service.notification.infrastructure.persistence.entities.SmsNotification;
import inc.yowyob.service.notification.infrastructure.persistence.entities.keys.NotificationKey;
import inc.yowyob.service.notification.infrastructure.persistence.repository.SmsNotificationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class SmsNotificationServiceImpl implements SmsNotificationService {

    private final SmsNotificationRepository smsNotificationRepository;

    private final SmsNotificationMapper smsNotificationMapper;

    @Override
    public Mono<SmsNotification> createSmsNotification(UUID organizationId, UUID templateId, SmsNotificationRequest request) {
        return smsNotificationRepository.insert(smsNotificationMapper.toEntity(organizationId, templateId, request));
    }

    @Override
    public Flux<SmsNotification> getAllSmsNotifications(UUID organizationId, UUID templateId) {
        return smsNotificationRepository.findByKeyOrganizationIdAndKeyTemplateId(organizationId, templateId);
    }

    @Override
    public Flux<SmsNotification> getAllSmsNotifications(UUID organizationId) {
        return smsNotificationRepository.findByKeyOrganizationId(organizationId);
    }

    @Override
    public Mono<SmsNotification> getSmsNotificationById(UUID organizationId, UUID templateId, UUID id) {
        return smsNotificationRepository.findById(NotificationKey.from(organizationId, templateId, id))
                .switchIfEmpty(Mono.error(new SmsNotificationFoundException(templateId, id)));
    }

    public Mono<Void> deleteSmsNotification(UUID organizationId, UUID templateId, UUID id) {
        return this.getSmsNotificationById(organizationId, templateId, id).flatMap(smsNotificationRepository::delete);
    }

}
