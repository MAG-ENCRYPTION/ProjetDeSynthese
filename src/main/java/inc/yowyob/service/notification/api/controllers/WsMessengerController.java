package inc.yowyob.service.notification.api.controllers;

import inc.yowyob.service.notification.application.dto.ws.WsMessageRequest;
import inc.yowyob.service.notification.application.service.WsService;
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
@RequestMapping("/organizations/{organization_id}/ws-messenger/{system_id}")
@RequiredArgsConstructor
@Validated
public class WsMessengerController {

    private final WsService wsService;

    /**
     * Creates a new WsSetting.
     *
     * @param organizationId The organization identifier
     * @param systemId       The system identifier
     * @param request        the DTO containing data for the mailer
     * @return a ResponseEntity with the created WsSettingDto
     */
    @PostMapping
    public Mono<Boolean> sendMessage(@PathVariable("organization_id") UUID organizationId, @PathVariable("system_id") String systemId, @Valid @RequestBody WsMessageRequest request) throws MessagingException {
        return wsService.sendSmsNotification(organizationId, systemId, request).map(unused -> true);
    }
}
