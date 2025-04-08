package inc.yowyob.service.notification.application.service;

import inc.yowyob.service.notification.infrastructure.persistence.entities.Template;
import inc.yowyob.service.notification.infrastructure.persistence.entities.WhatsappNotification;
import reactor.core.publisher.Mono;

public interface MetaService {
    public Mono<Void> sendWhatsappNotification(WhatsappNotification whatsappNotification, Template template);
}
