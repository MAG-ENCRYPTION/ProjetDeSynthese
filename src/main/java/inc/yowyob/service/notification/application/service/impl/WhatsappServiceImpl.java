package inc.yowyob.service.notification.application.service.impl;

import inc.yowyob.service.notification.application.dto.whatsapp.WhatsappNotificationRequest;
import inc.yowyob.service.notification.application.enums.ProviderType;
import inc.yowyob.service.notification.application.service.*;
import inc.yowyob.service.notification.infrastructure.persistence.entities.Template;
import inc.yowyob.service.notification.infrastructure.persistence.entities.WhatsappNotification;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.UUID;

@RequiredArgsConstructor
@Service
@Slf4j
public class WhatsappServiceImpl implements WhatsappService {

    private final TemplateService templateService;

    private final WhatsappNotificationService whatsappNotificationService;

    private final TwilioService twilioService;

    private final MetaService metaService;


    @Override
    public Mono<Void> sendWhatsappNotification(UUID organizationId, WhatsappNotificationRequest request) {
        return whatsappNotificationService.createWhatsappNotification(organizationId, request.getTemplateId(), request).flatMap(whatsappNotification -> {
            return templateService.getTemplateByIdOrDefault(organizationId, request.getTemplateId()).flatMap(template -> {
                return send(whatsappNotification, template);
            });
        }).then();
    }

    public Mono<Void> send(WhatsappNotification whatsappNotification, Template template) {

        if (ProviderType.TWILIO.is(template.getProviderType())) {
            return twilioService.sendWhatsappNotification(whatsappNotification, template);
        } else if (ProviderType.FACEBOOK.is(template.getProviderType())) {
            return metaService.sendWhatsappNotification(whatsappNotification, template);
        }

        return Mono.empty();
    }

}
