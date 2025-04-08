package inc.yowyob.service.notification.api.controllers;

import inc.yowyob.service.notification.application.dto.sms.SmsNotificationDto;
import inc.yowyob.service.notification.application.mappers.SmsNotificationMapper;
import inc.yowyob.service.notification.application.service.SmsNotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

@RestController
@RequestMapping("/organizations/{organization_id}/templates")
@RequiredArgsConstructor
public class SmsNotificationController {

    private final SmsNotificationService smsNotificationService;

    private final SmsNotificationMapper smsNotificationMapper;

    @GetMapping("/sms-notifications")
    public Flux<SmsNotificationDto> getAllSmsNotifications(@PathVariable("organization_id") UUID organizationId) {
        return smsNotificationService.getAllSmsNotifications(organizationId).map(smsNotificationMapper::toDto);
    }

    @GetMapping("/{template_id}/sms-notifications")
    public Flux<SmsNotificationDto> getAllSmsNotifications(@PathVariable("organization_id") UUID organizationId, @PathVariable("template_id") UUID templateId) {
        return smsNotificationService.getAllSmsNotifications(organizationId, templateId).map(smsNotificationMapper::toDto);
    }

    @GetMapping("/{template_id}/sms-notifications/{notification_id}")
    public Mono<SmsNotificationDto> getSmsNotificationById(@PathVariable("organization_id") UUID organizationId, @PathVariable("template_id") UUID templateId, @PathVariable("notification_id") UUID id) {
        return smsNotificationService.getSmsNotificationById(organizationId, templateId, id).map(smsNotificationMapper::toDto);
    }

    @DeleteMapping("/{template_id}/sms-notifications/{notification_id}")
    public Mono<Void> deleteSmsNotification(@PathVariable("organization_id") UUID organizationId, @PathVariable("template_id") UUID templateId, @PathVariable("notification_id") UUID id) {
        return smsNotificationService.deleteSmsNotification(organizationId, templateId, id);
    }
}
