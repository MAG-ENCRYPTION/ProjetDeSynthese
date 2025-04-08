package inc.yowyob.service.notification.api.controllers;

import inc.yowyob.service.notification.application.dto.email.EmailNotificationRequest;
import inc.yowyob.service.notification.application.service.EmailService;
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
@RequestMapping("/organizations/{organization_id}/mailer")
@RequiredArgsConstructor
@Validated
public class MailerController {

    private final EmailService emailService;


    /**
     * Creates a new WsSetting.
     *
     * @param organizationId The organization identifier
     * @param request        the DTO containing data for the mailer
     * @return a ResponseEntity with the created WsSettingDto
     */
    @PostMapping
    public Mono<Boolean> create(@PathVariable("organization_id") UUID organizationId, @Valid @RequestBody EmailNotificationRequest request) throws MessagingException {
        return emailService.sendEmail(organizationId, request).map(unused -> true);
    }
}
