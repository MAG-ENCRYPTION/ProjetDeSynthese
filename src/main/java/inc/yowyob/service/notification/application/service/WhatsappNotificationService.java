package inc.yowyob.service.notification.application.service;

import inc.yowyob.service.notification.application.dto.whatsapp.WhatsappNotificationRequest;
import inc.yowyob.service.notification.infrastructure.persistence.entities.WhatsappNotification;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

public interface WhatsappNotificationService {

    public Mono<WhatsappNotification> createWhatsappNotification(UUID organizationId, UUID templateId, WhatsappNotificationRequest request);

    public Flux<WhatsappNotification> getAllWhatsappNotifications(UUID organizationId, UUID templateId);

    public Flux<WhatsappNotification> getAllWhatsappNotifications(UUID organizationId);

    public Mono<WhatsappNotification> getWhatsappNotificationById(UUID organizationId, UUID templateId, UUID id);

    public Mono<Void> deleteWhatsappNotification(UUID organizationId, UUID templateId, UUID id);

}
