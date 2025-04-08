package inc.yowyob.service.notification.api.controllers;

import inc.yowyob.service.notification.application.dto.push.PushNotificationRequest;
import inc.yowyob.service.notification.application.dto.sms.SmsNotificationRequest;
import inc.yowyob.service.notification.application.service.PusherService;
import inc.yowyob.service.notification.application.service.SmsService;
import jakarta.mail.MessagingException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.UUID;

/**
 * Controller for managing WsSetting entities.
 *
 * @author ETOUGUE
 */
@RestController
@RequestMapping("/organizations/{organization_id}/sms-notifier")
@RequiredArgsConstructor
@Validated
public class SmsNotifierController {

    private final SmsService smsService;

    /**
     * Creates a new WsSetting.
     *
     * @param organizationId The organization identifier
     * @param request        the DTO containing data for the mailer
     * @return a ResponseEntity with the created WsSettingDto
     */
    @PostMapping
    public Mono<Boolean> create(@PathVariable("organization_id") UUID organizationId, @Valid @RequestBody SmsNotificationRequest request) throws MessagingException {
        return smsService.sendSmsNotification(organizationId, request).map(unused -> true);
    }
}
