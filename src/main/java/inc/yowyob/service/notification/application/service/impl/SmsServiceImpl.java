package inc.yowyob.service.notification.application.service.impl;

import inc.yowyob.service.notification.application.dto.sms.SmsNotificationRequest;
import inc.yowyob.service.notification.application.enums.ProviderType;
import inc.yowyob.service.notification.application.service.SmsNotificationService;
import inc.yowyob.service.notification.application.service.SmsService;
import inc.yowyob.service.notification.application.service.TemplateService;
import inc.yowyob.service.notification.application.service.TwilioService;
import inc.yowyob.service.notification.infrastructure.persistence.entities.DesignTemplate;
import inc.yowyob.service.notification.infrastructure.persistence.entities.SmsNotification;
import inc.yowyob.service.notification.infrastructure.persistence.entities.Template;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import reactor.core.publisher.Mono;

import java.util.Map;
import java.util.UUID;

@RequiredArgsConstructor
@Service
@Slf4j
public class SmsServiceImpl implements SmsService {

    private final TemplateService templateService;

    private final SmsNotificationService smsNotificationService;

    private final TemplateEngine templateEngine;

    private final TwilioService twilioService;


    @Override
    public Mono<Void> sendSmsNotification(UUID organizationId, SmsNotificationRequest request) {
        return smsNotificationService.createSmsNotification(organizationId, request.getTemplateId(), request).flatMap(smsNotification -> {
            return templateService.getTemplateByIdOrDefault(organizationId, request.getTemplateId()).flatMap(template -> {
                return send(smsNotification, template);
            });
        }).then();
    }

    public Mono<Void> send(SmsNotification smsNotification, Template template) {

        if (ProviderType.TWILIO.is(template.getProviderType())) {
            return twilioService.sendSmsNotification(smsNotification, template);
        }

        return Mono.empty();
    }

    private String buildEmailBody(DesignTemplate designTemplate, Context context) {
        return templateEngine.process(designTemplate.getHtml(), context);
    }

    private Context createContext(SmsNotification smsNotification) {
        Context context = new Context();

        for (Map.Entry<String, String> entry : smsNotification.getMetadata().entrySet()) {
            context.setVariable(entry.getKey().toUpperCase(), entry.getValue());
        }

        context.setVariable("SUBJECT", smsNotification.getSubject());

        return context;
    }

}
