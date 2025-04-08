package inc.yowyob.service.notification.application.service;


import inc.yowyob.service.notification.application.dto.ws.CreateWsSettingRequest;
import inc.yowyob.service.notification.application.dto.ws.UpdateWsSettingRequest;
import inc.yowyob.service.notification.infrastructure.persistence.entities.WsSetting;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

/**
 * Service interface for managing WsSetting entities.
 *
 * @author douglas
 */
public interface WsSettingService {

    /**
     * Creates a new WsSetting.
     *
     * @param organizationId The ID of the organization
     * @param request        the DTO containing data for the new WsSetting
     * @return the created Sms setting entity
     */
    public Mono<WsSetting> createWsSetting(UUID organizationId, CreateWsSettingRequest request);

    /**
     * Retrieves an WsSetting by its ID.
     *
     * @param organizationId The ID of the organization
     * @param systemId       the ID of the organization system
     * @return the found Sms setting entity, or null if not found
     */
    public Mono<WsSetting> getWsSettingById(UUID organizationId, String systemId);

    /**
     * Retrieves an WsSetting by its ID or return default smtp settings.
     *
     * @param organizationId The ID of the organization
     * @param systemId       the ID of the organization system
     * @return the found Sms setting entity, or null if not found
     */
    public Mono<WsSetting> getWsSettingByIdOrDefault(UUID organizationId, String systemId);

    /**
     * Retrieves an WsSetting by its ID or return default smtp settings.
     *
     * @param organizationId The ID of the organization
     * @return the found Sms setting entity, or null if not found
     */
    public Mono<WsSetting> getDefaultWsSettings(UUID organizationId);

    /**
     * Retrieves all WsSettings.
     *
     * @param organizationId The ID of the organization
     * @return a list of all WsSetting entities
     */
    public Flux<WsSetting> getAllWsSettings(UUID organizationId);

    /**
     * Updates an existing WsSetting.
     *
     * @param organizationId The ID of the organization
     * @param systemId       the ID of the organization system
     * @param request        the DTO containing updated data
     * @return the updated Sms setting entity
     */
    public Mono<WsSetting> updateWsSetting(UUID organizationId, String systemId, UpdateWsSettingRequest request);

    /**
     * Updates an existing WsSetting as default.
     *
     * @param organizationId The ID of the organization
     * @param systemId       the ID of the organization system
     * @return the updated Sms setting entity
     */
    public Mono<WsSetting> markAsDefault(UUID organizationId, String systemId);

    /**
     * Deletes an WsSetting by its ID.
     *
     * @param organizationId The ID of the organization
     * @param systemId       the ID of the organization system
     */
    public Mono<Void> deleteWsSetting(UUID organizationId, String systemId);
}