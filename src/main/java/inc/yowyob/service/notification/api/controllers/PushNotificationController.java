package inc.yowyob.service.notification.api.controllers;

import inc.yowyob.service.notification.application.dto.push.PushNotificationDto;
import inc.yowyob.service.notification.application.mappers.PushNotificationMapper;
import inc.yowyob.service.notification.application.service.PushNotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

@RestController
@RequestMapping("/organizations/{organization_id}/templates")
@RequiredArgsConstructor
public class PushNotificationController {
    
    private final PushNotificationService pushNotificationService;

    private final PushNotificationMapper pushNotificationMapper;

    @GetMapping("/push-notifications")
    public Flux<PushNotificationDto> getAllPushNotifications(@PathVariable("organization_id") UUID organizationId) {
        return pushNotificationService.getAllPushNotifications(organizationId).map(pushNotificationMapper::toDto);
    }

    @GetMapping("/{template_id}/push-notifications")
    public Flux<PushNotificationDto> getAllPushNotifications(@PathVariable("organization_id") UUID organizationId, @PathVariable("template_id") UUID templateId) {
        return pushNotificationService.getAllPushNotifications(organizationId, templateId).map(pushNotificationMapper::toDto);
    }

    @GetMapping("/{template_id}/push-notifications/{notification_id}")
    public Mono<PushNotificationDto> getPushNotificationById(@PathVariable("organization_id") UUID organizationId, @PathVariable("template_id") UUID templateId, @PathVariable("notification_id") UUID id) {
        return pushNotificationService.getPushNotificationById(organizationId, templateId, id).map(pushNotificationMapper::toDto);
    }

    @DeleteMapping("/{template_id}/push-notifications/{notification_id}")
    public Mono<Void> deletePushNotification(@PathVariable("organization_id") UUID organizationId, @PathVariable("template_id") UUID templateId, @PathVariable("notification_id") UUID id) {
        return pushNotificationService.deletePushNotification(organizationId, templateId, id);
    }
}
