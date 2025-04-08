package inc.yowyob.service.notification.application.service;


import inc.yowyob.service.notification.application.dto.twilio.CreateTwilioSettingRequest;
import inc.yowyob.service.notification.application.dto.twilio.UpdateTwilioSettingRequest;
import inc.yowyob.service.notification.infrastructure.persistence.entities.TwilioSetting;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

/**
 * Service interface for managing TwilioSetting entities.
 *
 * @author douglas
 */
public interface TwilioSettingService {

    /**
     * Creates a new TwilioSetting.
     *
     * @param organizationId The ID of the organization
     * @param request        the DTO containing data for the new TwilioSetting
     * @return the created Sms setting entity
     */
    public Mono<TwilioSetting> createTwilioSetting(UUID organizationId, CreateTwilioSettingRequest request);

    /**
     * Retrieves an TwilioSetting by its ID.
     *
     * @param organizationId The ID of the organization
     * @param id             the ID of the TwilioSetting to retrieve
     * @return the found Sms setting entity, or null if not found
     */
    public Mono<TwilioSetting> getTwilioSettingById(UUID organizationId, UUID id);

    /**
     * Retrieves an TwilioSetting by its ID or return default smtp settings.
     *
     * @param organizationId The ID of the organization
     * @param id             the ID of the TwilioSetting to retrieve
     * @return the found Sms setting entity, or null if not found
     */
    public Mono<TwilioSetting> getTwilioSettingByIdOrDefault(UUID organizationId, UUID id);

    /**
     * Retrieves an TwilioSetting by its ID or return default smtp settings.
     *
     * @param organizationId The ID of the organization
     * @return the found Sms setting entity, or null if not found
     */
    public Mono<TwilioSetting> getDefaultTwilioSettings(UUID organizationId);

    /**
     * Retrieves all TwilioSettings.
     *
     * @param organizationId The ID of the organization
     * @return a list of all TwilioSetting entities
     */
    public Flux<TwilioSetting> getAllTwilioSettings(UUID organizationId);

    /**
     * Updates an existing TwilioSetting.
     *
     * @param organizationId The ID of the organization
     * @param id             the ID of the TwilioSetting to update
     * @param request        the DTO containing updated data
     * @return the updated Sms setting entity
     */
    public Mono<TwilioSetting> updateTwilioSetting(UUID organizationId, UUID id, UpdateTwilioSettingRequest request);

    /**
     * Updates an existing TwilioSetting as default.
     *
     * @param organizationId The ID of the organization
     * @param id             the ID of the TwilioSetting to update
     * @return the updated Sms setting entity
     */
    public Mono<TwilioSetting> markAsDefault(UUID organizationId, UUID id);

    /**
     * Deletes an TwilioSetting by its ID.
     *
     * @param organizationId The ID of the organization
     * @param id             the ID of the TwilioSetting to delete
     */
    public Mono<Void> deleteTwilioSetting(UUID organizationId, UUID id);
}