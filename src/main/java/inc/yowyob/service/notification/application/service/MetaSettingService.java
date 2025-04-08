package inc.yowyob.service.notification.application.service;


import inc.yowyob.service.notification.application.dto.meta.CreateMetaSettingRequest;
import inc.yowyob.service.notification.application.dto.meta.UpdateMetaSettingRequest;
import inc.yowyob.service.notification.infrastructure.persistence.entities.MetaSetting;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

/**
 * Service interface for managing MetaSetting entities.
 *
 * @author douglas
 */
public interface MetaSettingService {

    /**
     * Creates a new MetaSetting.
     *
     * @param organizationId The ID of the organization
     * @param request the DTO containing data for the new MetaSetting
     * @return the created MetaSetting entity
     */
    public Mono<MetaSetting> createMetaSetting(UUID organizationId, CreateMetaSettingRequest request);

    /**
     * Retrieves an MetaSetting by its ID.
     *
     * @param organizationId The ID of the organization
     * @param id the ID of the MetaSetting to retrieve
     * @return the found MetaSetting entity, or null if not found
     */
    public Mono<MetaSetting> getMetaSettingById(UUID organizationId, UUID id);

    /**
     * Retrieves an MetaSetting by its ID or return default meta settings.
     *
     * @param organizationId The ID of the organization
     * @param id the ID of the MetaSetting to retrieve
     * @return the found MetaSetting entity, or null if not found
     */
    public Mono<MetaSetting> getMetaSettingByIdOrDefault(UUID organizationId, UUID id);

    /**
     * Retrieves an MetaSetting by its ID or return default meta settings.
     *
     * @param organizationId The ID of the organization
     * @return the found MetaSetting entity, or null if not found
     */
    public Mono<MetaSetting> getDefaultMetaSettings(UUID organizationId);
    /**
     * Retrieves all MetaSettings.
     *
     * @param organizationId The ID of the organization
     * @return a list of all MetaSetting entities
     */
    public Flux<MetaSetting> getAllMetaSettings(UUID organizationId);

    /**
     * Updates an existing MetaSetting.
     *
     * @param organizationId The ID of the organization
     * @param id      the ID of the MetaSetting to update
     * @param request the DTO containing updated data
     * @return the updated MetaSetting entity
     */
    public Mono<MetaSetting> updateMetaSetting(UUID organizationId, UUID id, UpdateMetaSettingRequest request);

    /**
     * Updates an existing MetaSetting as default.
     *
     * @param organizationId The ID of the organization
     * @param id the ID of the MetaSetting to update
     * @return the updated MetaSetting entity
     */
    public Mono<MetaSetting> markAsDefault(UUID organizationId, UUID id);

    /**
     * Deletes an MetaSetting by its ID.
     *
     * @param organizationId The ID of the organization
     * @param id the ID of the MetaSetting to delete
     */
    public Mono<Void> deleteMetaSetting(UUID organizationId, UUID id);
}