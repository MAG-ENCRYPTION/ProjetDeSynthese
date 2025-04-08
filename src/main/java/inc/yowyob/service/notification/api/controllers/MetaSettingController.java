package inc.yowyob.service.notification.api.controllers;

import inc.yowyob.service.notification.application.dto.meta.CreateMetaSettingRequest;
import inc.yowyob.service.notification.application.dto.meta.MetaSettingDto;
import inc.yowyob.service.notification.application.dto.meta.UpdateMetaSettingRequest;
import inc.yowyob.service.notification.application.mappers.MetaSettingMapper;
import inc.yowyob.service.notification.application.service.MetaSettingService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

/**
 * Controller for managing MetaSetting entities.
 *
 * @author ETOUGUE
 */
@RestController
@RequestMapping("/organizations/{organization_id}/meta-settings")
@RequiredArgsConstructor
@Validated
public class MetaSettingController {

    private final MetaSettingService metaSettingService;

    private final MetaSettingMapper metaSettingMapper;

    /**
     * Creates a new MetaSetting.
     *
     * @param organizationId The organization identifier
     * @param request        the DTO containing data for the new MetaSetting
     * @return a ResponseEntity with the created MetaSettingDto
     */
    @PostMapping
    public Mono<MetaSettingDto> create(@PathVariable("organization_id") UUID organizationId, @Valid @RequestBody CreateMetaSettingRequest request) {
        return metaSettingService.createMetaSetting(organizationId, request).map(metaSettingMapper::toDto);
    }

    /**
     * Retrieves an MetaSetting by its ID.
     *
     * @param organizationId The organization identifier
     * @param id             the ID of the MetaSetting to retrieve
     * @return a ResponseEntity with the MetaSettingDto if found, or a not found status
     */
    @GetMapping("/{meta_setting_id}")
    public Mono<MetaSettingDto> getById(@PathVariable("organization_id") UUID organizationId, @PathVariable("meta_setting_id") UUID id) {
        return metaSettingService.getMetaSettingById(organizationId, id).map(metaSettingMapper::toDto);
    }

    /**
     * Retrieves all MetaSettings.
     *
     * @param organizationId The organization identifier
     * @return a ResponseEntity with a list of MetaSettingDto
     */
    @GetMapping
    public Flux<MetaSettingDto> getAllMetaSettings(@PathVariable("organization_id") UUID organizationId) {
        return metaSettingService.getAllMetaSettings(organizationId).map(metaSettingMapper::toDto);
    }

    /**
     * Updates an existing MetaSetting.
     *
     * @param organizationId The organization identifier
     * @param id             the ID of the MetaSetting to update
     * @param request        the DTO containing updated values
     * @return a ResponseEntity with the updated MetaSettingDto
     */
    @PutMapping("/{meta_setting_id}")
    public Mono<MetaSettingDto> updateMetaSetting(@PathVariable("organization_id") UUID organizationId, @PathVariable("meta_setting_id") UUID id, @Valid @RequestBody UpdateMetaSettingRequest request) {
        return metaSettingService.updateMetaSetting(organizationId, id, request).map(metaSettingMapper::toDto);
    }

    /**
     * Updates an existing MetaSetting.
     *
     * @param organizationId The organization identifier
     * @param id             the ID of the MetaSetting to update
     * @return a ResponseEntity with the updated MetaSettingDto
     */
    @PutMapping("/{meta_setting_id}/default")
    public Mono<MetaSettingDto> markMetaSettingAsDefault(@PathVariable("organization_id") UUID organizationId, @PathVariable("meta_setting_id") UUID id) {
        return metaSettingService.markAsDefault(organizationId, id).map(metaSettingMapper::toDto);
    }

    /**
     * Deletes an MetaSetting by its ID.
     *
     * @param organizationId The organization identifier
     * @param id             the ID of the MetaSetting to delete
     * @return the status
     */
    @DeleteMapping("/{meta_setting_id}")
    public Mono<Void> deleteMetaSetting(@PathVariable("organization_id") UUID organizationId, @PathVariable("meta_setting_id") UUID id) {
        return metaSettingService.deleteMetaSetting(organizationId, id);
    }
}
