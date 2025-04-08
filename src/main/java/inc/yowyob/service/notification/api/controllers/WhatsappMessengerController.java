package inc.yowyob.service.notification.api.controllers;

import inc.yowyob.service.notification.application.dto.whatsapp.WhatsappNotificationRequest;
import inc.yowyob.service.notification.application.service.WhatsappService;
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
@RequestMapping("/organizations/{organization_id}/whatsapp-messenger")
@RequiredArgsConstructor
@Validated
public class WhatsappMessengerController {

    private final WhatsappService whatsappService;

    /**
     * Creates a new WsSetting.
     *
     * @param organizationId The organization identifier
     * @param request        the DTO containing data for the mailer
     * @return a ResponseEntity with the created WsSettingDto
     */
    @PostMapping
    public Mono<Boolean> sendWhatsappMessage(@PathVariable("organization_id") UUID organizationId, @Valid @RequestBody WhatsappNotificationRequest request) throws MessagingException {
        return whatsappService.sendWhatsappNotification(organizationId, request).map(unused -> true);
    }
}
