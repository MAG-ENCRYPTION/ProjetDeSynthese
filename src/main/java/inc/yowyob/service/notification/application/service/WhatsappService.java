package inc.yowyob.service.notification.application.service;

import inc.yowyob.service.notification.application.dto.whatsapp.WhatsappNotificationRequest;
import reactor.core.publisher.Mono;

import java.util.UUID;

public interface WhatsappService {
    public Mono<Void> sendWhatsappNotification(UUID organizationId, WhatsappNotificationRequest request);
}
