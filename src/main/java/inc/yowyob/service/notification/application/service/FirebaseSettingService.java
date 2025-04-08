package inc.yowyob.service.notification.application.service;


import inc.yowyob.service.notification.application.dto.firebase.CreateFirebaseSettingRequest;
import inc.yowyob.service.notification.application.dto.firebase.UpdateFirebaseSettingRequest;
import inc.yowyob.service.notification.infrastructure.persistence.entities.FirebaseSetting;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

/**
 * Service interface for managing FirebaseSetting entities.
 *
 * @author douglas
 */
public interface FirebaseSettingService {

    /**
     * Creates a new FirebaseSetting.
     *
     * @param organizationId The ID of the organization
     * @param request        the DTO containing data for the new FirebaseSetting
     * @return the created Sms setting entity
     */
    public Mono<FirebaseSetting> createFirebaseSetting(UUID organizationId, CreateFirebaseSettingRequest request);

    /**
     * Retrieves an FirebaseSetting by its ID.
     *
     * @param organizationId The ID of the organization
     * @param id             the ID of the FirebaseSetting to retrieve
     * @return the found Sms setting entity, or null if not found
     */
    public Mono<FirebaseSetting> getFirebaseSettingById(UUID organizationId, UUID id);

    /**
     * Retrieves an FirebaseSetting by its ID or return default smtp settings.
     *
     * @param organizationId The ID of the organization
     * @param id             the ID of the FirebaseSetting to retrieve
     * @return the found Sms setting entity, or null if not found
     */
    public Mono<FirebaseSetting> getFirebaseSettingByIdOrDefault(UUID organizationId, UUID id);

    /**
     * Retrieves an FirebaseSetting by its ID or return default smtp settings.
     *
     * @param organizationId The ID of the organization
     * @return the found Sms setting entity, or null if not found
     */
    public Mono<FirebaseSetting> getDefaultFirebaseSettings(UUID organizationId);

    /**
     * Retrieves all FirebaseSettings.
     *
     * @param organizationId The ID of the organization
     * @return a list of all FirebaseSetting entities
     */
    public Flux<FirebaseSetting> getAllFirebaseSettings(UUID organizationId);

    /**
     * Updates an existing FirebaseSetting.
     *
     * @param organizationId The ID of the organization
     * @param id             the ID of the FirebaseSetting to update
     * @param request        the DTO containing updated data
     * @return the updated Sms setting entity
     */
    public Mono<FirebaseSetting> updateFirebaseSetting(UUID organizationId, UUID id, UpdateFirebaseSettingRequest request);

    /**
     * Updates an existing FirebaseSetting as default.
     *
     * @param organizationId The ID of the organization
     * @param id             the ID of the FirebaseSetting to update
     * @return the updated Sms setting entity
     */
    public Mono<FirebaseSetting> markAsDefault(UUID organizationId, UUID id);

    /**
     * Deletes an FirebaseSetting by its ID.
     *
     * @param organizationId The ID of the organization
     * @param id             the ID of the FirebaseSetting to delete
     */
    public Mono<Void> deleteFirebaseSetting(UUID organizationId, UUID id);
}