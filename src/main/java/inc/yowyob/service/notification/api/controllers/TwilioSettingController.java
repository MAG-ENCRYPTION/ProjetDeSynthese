package inc.yowyob.service.notification.api.controllers;

import inc.yowyob.service.notification.application.dto.twilio.CreateTwilioSettingRequest;
import inc.yowyob.service.notification.application.dto.twilio.TwilioSettingDto;
import inc.yowyob.service.notification.application.dto.twilio.UpdateTwilioSettingRequest;
import inc.yowyob.service.notification.application.mappers.TwilioSettingMapper;
import inc.yowyob.service.notification.application.service.TwilioSettingService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

/**
 * Controller for managing TwilioSetting entities.
 *
 * @author ETOUGUE
 */
@RestController
@RequestMapping("/organizations/{organization_id}/twilio-settings")
@RequiredArgsConstructor
@Validated
public class TwilioSettingController {

    private final TwilioSettingService twilioSettingService;

    private final TwilioSettingMapper twilioSettingMapper;

    /**
     * Creates a new TwilioSetting.
     *
     * @param organizationId The organization identifier
     * @param request        the DTO containing data for the new TwilioSetting
     * @return a ResponseEntity with the created TwilioSettingDto
     */
    @PostMapping
    public Mono<TwilioSettingDto> create(@PathVariable("organization_id") UUID organizationId, @Valid @RequestBody CreateTwilioSettingRequest request) {
        return twilioSettingService.createTwilioSetting(organizationId, request).map(twilioSettingMapper::toDto);
    }

    /**
     * Retrieves an TwilioSetting by its ID.
     *
     * @param organizationId The organization identifier
     * @param id             the ID of the TwilioSetting to retrieve
     * @return a ResponseEntity with the TwilioSettingDto if found, or a not found status
     */
    @GetMapping("/{twilio_setting_id}")
    public Mono<TwilioSettingDto> getById(@PathVariable("organization_id") UUID organizationId, @PathVariable("twilio_setting_id") UUID id) {
        return twilioSettingService.getTwilioSettingById(organizationId, id).map(twilioSettingMapper::toDto);
    }

    /**
     * Retrieves all TwilioSettings.
     *
     * @param organizationId The organization identifier
     * @return a ResponseEntity with a list of TwilioSettingDto
     */
    @GetMapping
    public Flux<TwilioSettingDto> getAllTwilioSettings(@PathVariable("organization_id") UUID organizationId) {
        return twilioSettingService.getAllTwilioSettings(organizationId).map(twilioSettingMapper::toDto);
    }

    /**
     * Updates an existing TwilioSetting.
     *
     * @param organizationId The organization identifier
     * @param id             the ID of the TwilioSetting to update
     * @param request        the DTO containing updated values
     * @return a ResponseEntity with the updated TwilioSettingDto
     */
    @PutMapping("/{twilio_setting_id}")
    public Mono<TwilioSettingDto> updateTwilioSetting(@PathVariable("organization_id") UUID organizationId, @PathVariable("twilio_setting_id") UUID id, @Valid @RequestBody UpdateTwilioSettingRequest request) {
        return twilioSettingService.updateTwilioSetting(organizationId, id, request).map(twilioSettingMapper::toDto);
    }

    /**
     * Updates an existing TwilioSetting.
     *
     * @param organizationId The organization identifier
     * @param id             the ID of the TwilioSetting to update
     * @return a ResponseEntity with the updated TwilioSettingDto
     */
    @PutMapping("/{twilio_setting_id}/default")
    public Mono<TwilioSettingDto> markTwilioSettingAsDefault(@PathVariable("organization_id") UUID organizationId, @PathVariable("twilio_setting_id") UUID id) {
        return twilioSettingService.markAsDefault(organizationId, id).map(twilioSettingMapper::toDto);
    }

    /**
     * Deletes an TwilioSetting by its ID.
     *
     * @param organizationId The organization identifier
     * @param id             the ID of the TwilioSetting to delete
     * @return the status
     */
    @DeleteMapping("/{twilio_setting_id}")
    public Mono<Void> deleteTwilioSetting(@PathVariable("organization_id") UUID organizationId, @PathVariable("twilio_setting_id") UUID id) {
        return twilioSettingService.deleteTwilioSetting(organizationId, id);
    }
}
