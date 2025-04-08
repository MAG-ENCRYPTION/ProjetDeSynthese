package inc.yowyob.service.notification.api.controllers;

import inc.yowyob.service.notification.application.dto.email.EmailNotificationRequest;
import inc.yowyob.service.notification.application.dto.push.PushNotificationRequest;
import inc.yowyob.service.notification.application.service.EmailService;
import inc.yowyob.service.notification.application.service.PusherService;
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
@RequestMapping("/organizations/{organization_id}/pusher")
@RequiredArgsConstructor
@Validated
public class PusherController {

    private final PusherService pusherService;


    /**
     * Creates a new WsSetting.
     *
     * @param organizationId The organization identifier
     * @param request        the DTO containing data for the mailer
     * @return a ResponseEntity with the created WsSettingDto
     */
    @PostMapping
    public Mono<Boolean> create(@PathVariable("organization_id") UUID organizationId, @Valid @RequestBody PushNotificationRequest request) throws MessagingException {
        return pusherService.sendPushNotification(organizationId, request).map(unused -> true);
    }
}
