package inc.yowyob.service.notification.application.service.impl;

import inc.yowyob.service.notification.application.dto.push.PushNotificationRequest;
import inc.yowyob.service.notification.application.enums.ProviderType;
import inc.yowyob.service.notification.application.service.*;
import inc.yowyob.service.notification.infrastructure.persistence.entities.DesignTemplate;
import inc.yowyob.service.notification.infrastructure.persistence.entities.PushNotification;
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
public class PusherServiceImpl implements PusherService {

    private final TemplateService templateService;

    private final PushNotificationService pushNotificationService;

    private final FirebaseService firebaseService;


    @Override
    public Mono<Void> sendPushNotification(UUID organizationId, PushNotificationRequest request) {
        return pushNotificationService.createPushNotification(organizationId, request.getTemplateId(), request).flatMap(pushNotification -> {
            return templateService.getTemplateByIdOrDefault(organizationId, request.getTemplateId()).flatMap(template -> {
                return send(pushNotification, template);
            });
        }).then();
    }


    public Mono<Void> send(PushNotification pushNotification, Template template) {

        if(ProviderType.FIREBASE.is(template.getProviderType())){
            return firebaseService.sendPushNotification(template, pushNotification).then();
        }

        return Mono.empty();
    }

}
