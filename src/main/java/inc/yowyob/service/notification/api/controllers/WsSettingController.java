package inc.yowyob.service.notification.api.controllers;

import inc.yowyob.service.notification.application.dto.ws.CreateWsSettingRequest;
import inc.yowyob.service.notification.application.dto.ws.UpdateWsSettingRequest;
import inc.yowyob.service.notification.application.dto.ws.WsSettingDto;
import inc.yowyob.service.notification.application.mappers.WsSettingMapper;
import inc.yowyob.service.notification.application.service.WsSettingService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

/**
 * Controller for managing WsSetting entities.
 *
 * @author ETOUGUE
 */
@RestController
@RequestMapping("/organizations/{organization_id}/ws-settings")
@RequiredArgsConstructor
@Validated
public class WsSettingController {

    private final WsSettingService wsSettingService;

    private final WsSettingMapper wsSettingMapper;

    /**
     * Creates a new WsSetting.
     *
     * @param organizationId The organization identifier
     * @param request        the DTO containing data for the new WsSetting
     * @return a ResponseEntity with the created WsSettingDto
     */
    @PostMapping
    public Mono<WsSettingDto> create(@PathVariable("organization_id") UUID organizationId, @Valid @RequestBody CreateWsSettingRequest request) {
        return wsSettingService.createWsSetting(organizationId, request).map(wsSettingMapper::toDto);
    }

    /**
     * Retrieves an WsSetting by its ID.
     *
     * @param organizationId The organization identifier
     * @param systemId       The ID of the system to retrieve
     * @return a ResponseEntity with the WsSettingDto if found, or a not found status
     */
    @GetMapping("/{system_id}")
    public Mono<WsSettingDto> getById(@PathVariable("organization_id") UUID organizationId, @PathVariable("system_id") String systemId) {
        return wsSettingService.getWsSettingById(organizationId, systemId).map(wsSettingMapper::toDto);
    }

    /**
     * Retrieves all WsSettings.
     *
     * @param organizationId The organization identifier
     * @return a ResponseEntity with a list of WsSettingDto
     */
    @GetMapping
    public Flux<WsSettingDto> getAllWsSettings(@PathVariable("organization_id") UUID organizationId) {
        return wsSettingService.getAllWsSettings(organizationId).map(wsSettingMapper::toDto);
    }

    /**
     * Updates an existing WsSetting.
     *
     * @param organizationId The organization identifier
     * @param systemId       The ID of the system to update
     * @param request        the DTO containing updated values
     * @return a ResponseEntity with the updated WsSettingDto
     */
    @PutMapping("/{system_id}")
    public Mono<WsSettingDto> updateWsSetting(@PathVariable("organization_id") UUID organizationId, @PathVariable("system_id") String systemId, @Valid @RequestBody UpdateWsSettingRequest request) {
        return wsSettingService.updateWsSetting(organizationId, systemId, request).map(wsSettingMapper::toDto);
    }

    /**
     * Updates an existing WsSetting.
     *
     * @param organizationId The organization identifier
     * @param systemId       The ID of the system to update
     * @return a ResponseEntity with the updated WsSettingDto
     */
    @PutMapping("/{system_id}/default")
    public Mono<WsSettingDto> markWsSettingAsDefault(@PathVariable("organization_id") UUID organizationId, @PathVariable("system_id") String systemId) {
        return wsSettingService.markAsDefault(organizationId, systemId).map(wsSettingMapper::toDto);
    }

    /**
     * Deletes an WsSetting by its ID.
     *
     * @param organizationId The organization identifier
     * @param systemId       The ID of the system to update
     * @return the status
     */
    @DeleteMapping("/{system_id}")
    public Mono<Void> deleteWsSetting(@PathVariable("organization_id") UUID organizationId, @PathVariable("system_id") String systemId) {
        return wsSettingService.deleteWsSetting(organizationId, systemId);
    }
}
