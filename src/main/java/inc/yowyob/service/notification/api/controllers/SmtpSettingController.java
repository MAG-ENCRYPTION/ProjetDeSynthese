package inc.yowyob.service.notification.api.controllers;

import inc.yowyob.service.notification.application.dto.smtp.CreateSmtpSettingRequest;
import inc.yowyob.service.notification.application.dto.smtp.SmtpSettingDto;
import inc.yowyob.service.notification.application.dto.smtp.UpdateSmtpSettingRequest;
import inc.yowyob.service.notification.application.mappers.SmtpSettingMapper;
import inc.yowyob.service.notification.application.service.SmtpSettingService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

/**
 * Controller for managing SmtpSetting entities.
 *
 * @author ETOUGUE
 */
@RestController
@RequestMapping("/organizations/{organization_id}/smtp-settings")
@RequiredArgsConstructor
@Validated
public class SmtpSettingController {

    private final SmtpSettingService smtpSettingService;

    private final SmtpSettingMapper smtpSettingMapper;

    /**
     * Creates a new SmtpSetting.
     *
     * @param organizationId The organization identifier
     * @param request        the DTO containing data for the new SmtpSetting
     * @return a ResponseEntity with the created SmtpSettingDto
     */
    @PostMapping
    public Mono<SmtpSettingDto> create(@PathVariable("organization_id") UUID organizationId, @Valid @RequestBody CreateSmtpSettingRequest request) {
        return smtpSettingService.createSmtpSetting(organizationId, request).map(smtpSettingMapper::toDto);
    }

    /**
     * Retrieves an SmtpSetting by its ID.
     *
     * @param organizationId The organization identifier
     * @param id             the ID of the SmtpSetting to retrieve
     * @return a ResponseEntity with the SmtpSettingDto if found, or a not found status
     */
    @GetMapping("/{smtp_setting_id}")
    public Mono<SmtpSettingDto> getById(@PathVariable("organization_id") UUID organizationId, @PathVariable("smtp_setting_id") UUID id) {
        return smtpSettingService.getSmtpSettingById(organizationId, id).map(smtpSettingMapper::toDto);
    }

    /**
     * Retrieves all SmtpSettings.
     *
     * @param organizationId The organization identifier
     * @return a ResponseEntity with a list of SmtpSettingDto
     */
    @GetMapping
    public Flux<SmtpSettingDto> getAllSmtpSettings(@PathVariable("organization_id") UUID organizationId) {
        return smtpSettingService.getAllSmtpSettings(organizationId).map(smtpSettingMapper::toDto);
    }

    /**
     * Updates an existing SmtpSetting.
     *
     * @param organizationId The organization identifier
     * @param id             the ID of the SmtpSetting to update
     * @param request        the DTO containing updated values
     * @return a ResponseEntity with the updated SmtpSettingDto
     */
    @PutMapping("/{smtp_setting_id}")
    public Mono<SmtpSettingDto> updateSmtpSetting(@PathVariable("organization_id") UUID organizationId, @PathVariable("smtp_setting_id") UUID id, @Valid @RequestBody UpdateSmtpSettingRequest request) {
        return smtpSettingService.updateSmtpSetting(organizationId, id, request).map(smtpSettingMapper::toDto);
    }

    /**
     * Updates an existing SmtpSetting.
     *
     * @param organizationId The organization identifier
     * @param id             the ID of the SmtpSetting to update
     * @return a ResponseEntity with the updated SmtpSettingDto
     */
    @PutMapping("/{smtp_setting_id}/default")
    public Mono<SmtpSettingDto> markSmtpSettingAsDefault(@PathVariable("organization_id") UUID organizationId, @PathVariable("smtp_setting_id") UUID id) {
        return smtpSettingService.markAsDefault(organizationId, id).map(smtpSettingMapper::toDto);
    }

    /**
     * Deletes an SmtpSetting by its ID.
     *
     * @param organizationId The organization identifier
     * @param id             the ID of the SmtpSetting to delete
     * @return the status
     */
    @DeleteMapping("/{smtp_setting_id}")
    public Mono<Void> deleteSmtpSetting(@PathVariable("organization_id") UUID organizationId, @PathVariable("smtp_setting_id") UUID id) {
        return smtpSettingService.deleteSmtpSetting(organizationId, id);
    }
}
