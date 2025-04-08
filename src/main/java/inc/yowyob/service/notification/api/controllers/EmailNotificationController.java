package inc.yowyob.service.notification.api.controllers;

import inc.yowyob.service.notification.application.dto.email.EmailNotificationDto;
import inc.yowyob.service.notification.application.mappers.EmailNotificationMapper;
import inc.yowyob.service.notification.application.service.EmailNotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

@RestController
@RequestMapping("/organizations/{organization_id}/templates")
@RequiredArgsConstructor
public class EmailNotificationController {

    private final EmailNotificationService emailNotificationService;

    private final EmailNotificationMapper emailNotificationMapper;

    @GetMapping("/email-notifications")
    public Flux<EmailNotificationDto> getAllEmailNotifications(@PathVariable("organization_id") UUID organizationId) {
        return emailNotificationService.getAllEmailNotifications(organizationId).map(emailNotificationMapper::toDto);
    }

    @GetMapping("/{template_id}/email-notifications")
    public Flux<EmailNotificationDto> getAllEmailNotifications(@PathVariable("organization_id") UUID organizationId, @PathVariable("template_id") UUID templateId) {
        return emailNotificationService.getAllEmailNotifications(organizationId, templateId).map(emailNotificationMapper::toDto);
    }

    @GetMapping("/{template_id}/email-notifications/{notification_id}")
    public Mono<EmailNotificationDto> getEmailNotificationById(@PathVariable("organization_id") UUID organizationId, @PathVariable("template_id") UUID templateId, @PathVariable("notification_id") UUID id) {
        return emailNotificationService.getEmailNotificationById(organizationId, templateId, id).map(emailNotificationMapper::toDto);
    }

    @DeleteMapping("/{template_id}/email-notifications/{notification_id}")
    public Mono<Void> deleteEmailNotification(@PathVariable("organization_id") UUID organizationId, @PathVariable("template_id") UUID templateId, @PathVariable("notification_id") UUID id) {
        return emailNotificationService.deleteEmailNotification(organizationId, templateId, id);
    }
}
