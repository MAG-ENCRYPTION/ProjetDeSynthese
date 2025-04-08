package inc.yowyob.service.notification.application.service;

import inc.yowyob.service.notification.infrastructure.persistence.entities.SmsNotification;
import inc.yowyob.service.notification.infrastructure.persistence.entities.Template;
import inc.yowyob.service.notification.infrastructure.persistence.entities.WhatsappNotification;
import reactor.core.publisher.Mono;

public interface TwilioService {
    public Mono<Void> sendSmsNotification(SmsNotification smsNotification, Template template);

    public Mono<Void> sendWhatsappNotification(WhatsappNotification whatsappNotification, Template template);
}
