package inc.yowyob.service.notification.api.controllers;

import inc.yowyob.service.notification.application.dto.notification.NotificationDto;
import inc.yowyob.service.notification.application.mappers.NotificationMapper;
import inc.yowyob.service.notification.application.service.NotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

@RestController
@RequestMapping("/organizations/{organization_id}/templates")
@RequiredArgsConstructor
public class NotificationController {

    private final NotificationService notificationService;

    private final NotificationMapper notificationMapper;

    @GetMapping("/notifications")
    public Flux<NotificationDto> getAllNotifications(@PathVariable("organization_id") UUID organizationId) {
        return notificationService.getAllNotifications(organizationId).map(notificationMapper::toDto);
    }

    @GetMapping("/{template_id}/notifications")
    public Flux<NotificationDto> getAllNotifications(@PathVariable("organization_id") UUID organizationId, @PathVariable("template_id") UUID templateId) {
        return notificationService.getAllNotifications(organizationId, templateId).map(notificationMapper::toDto);
    }

    @GetMapping("/{template_id}/notifications/{notification_id}")
    public Mono<NotificationDto> getNotificationById(@PathVariable("organization_id") UUID organizationId, @PathVariable("template_id") UUID templateId, @PathVariable("notification_id") UUID id) {
        return notificationService.getNotificationById(organizationId, templateId, id).map(notificationMapper::toDto);
    }

    @DeleteMapping("/{template_id}/notifications/{notification_id}")
    public Mono<Void> deleteNotification(@PathVariable("organization_id") UUID organizationId, @PathVariable("template_id") UUID templateId, @PathVariable("notification_id") UUID id) {
        return notificationService.deleteNotification(organizationId, templateId, id);
    }
}
