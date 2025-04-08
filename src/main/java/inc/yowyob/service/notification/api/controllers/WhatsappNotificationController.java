package inc.yowyob.service.notification.api.controllers;

import inc.yowyob.service.notification.application.dto.whatsapp.WhatsappNotificationDto;
import inc.yowyob.service.notification.application.mappers.WhatsappNotificationMapper;
import inc.yowyob.service.notification.application.service.WhatsappNotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

@RestController
@RequestMapping("/organizations/{organization_id}/templates")
@RequiredArgsConstructor
public class WhatsappNotificationController {

    private final WhatsappNotificationService whatsappNotificationService;

    private final WhatsappNotificationMapper whatsappNotificationMapper;

    @GetMapping("/whatsapp-notifications")
    public Flux<WhatsappNotificationDto> getAllWhatsappNotifications(@PathVariable("organization_id") UUID organizationId) {
        return whatsappNotificationService.getAllWhatsappNotifications(organizationId).map(whatsappNotificationMapper::toDto);
    }

    @GetMapping("/{template_id}/whatsapp-notifications")
    public Flux<WhatsappNotificationDto> getAllWhatsappNotifications(@PathVariable("organization_id") UUID organizationId, @PathVariable("template_id") UUID templateId) {
        return whatsappNotificationService.getAllWhatsappNotifications(organizationId, templateId).map(whatsappNotificationMapper::toDto);
    }

    @GetMapping("/{template_id}/whatsapp-notifications/{notification_id}")
    public Mono<WhatsappNotificationDto> getWhatsappNotificationById(@PathVariable("organization_id") UUID organizationId, @PathVariable("template_id") UUID templateId, @PathVariable("notification_id") UUID id) {
        return whatsappNotificationService.getWhatsappNotificationById(organizationId, templateId, id).map(whatsappNotificationMapper::toDto);
    }

    @DeleteMapping("/{template_id}/whatsapp-notifications/{notification_id}")
    public Mono<Void> deleteWhatsappNotification(@PathVariable("organization_id") UUID organizationId, @PathVariable("template_id") UUID templateId, @PathVariable("notification_id") UUID id) {
        return whatsappNotificationService.deleteWhatsappNotification(organizationId, templateId, id);
    }
}
