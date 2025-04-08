package inc.yowyob.service.notification.application.service;


import inc.yowyob.service.notification.application.dto.smtp.CreateSmtpSettingRequest;
import inc.yowyob.service.notification.application.dto.smtp.UpdateSmtpSettingRequest;
import inc.yowyob.service.notification.infrastructure.persistence.entities.SmtpSetting;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

/**
 * Service interface for managing SmtpSetting entities.
 *
 * @author douglas
 */
public interface SmtpSettingService {

    /**
     * Creates a new SmtpSetting.
     *
     * @param organizationId The ID of the organization
     * @param request the DTO containing data for the new SmtpSetting
     * @return the created SmtpSetting entity
     */
    public Mono<SmtpSetting> createSmtpSetting(UUID organizationId, CreateSmtpSettingRequest request);

    /**
     * Retrieves an SmtpSetting by its ID.
     *
     * @param organizationId The ID of the organization
     * @param id the ID of the SmtpSetting to retrieve
     * @return the found SmtpSetting entity, or null if not found
     */
    public Mono<SmtpSetting> getSmtpSettingById(UUID organizationId, UUID id);

    /**
     * Retrieves an SmtpSetting by its ID or return default smtp settings.
     *
     * @param organizationId The ID of the organization
     * @param id the ID of the SmtpSetting to retrieve
     * @return the found SmtpSetting entity, or null if not found
     */
    public Mono<SmtpSetting> getSmtpSettingByIdOrDefault(UUID organizationId, UUID id);

    /**
     * Retrieves an SmtpSetting by its ID or return default smtp settings.
     *
     * @param organizationId The ID of the organization
     * @return the found SmtpSetting entity, or null if not found
     */
    public Mono<SmtpSetting> getDefaultSmtpSettings(UUID organizationId);
    /**
     * Retrieves all SmtpSettings.
     *
     * @param organizationId The ID of the organization
     * @return a list of all SmtpSetting entities
     */
    public Flux<SmtpSetting> getAllSmtpSettings(UUID organizationId);

    /**
     * Updates an existing SmtpSetting.
     *
     * @param organizationId The ID of the organization
     * @param id      the ID of the SmtpSetting to update
     * @param request the DTO containing updated data
     * @return the updated SmtpSetting entity
     */
    public Mono<SmtpSetting> updateSmtpSetting(UUID organizationId, UUID id, UpdateSmtpSettingRequest request);

    /**
     * Updates an existing SmtpSetting as default.
     *
     * @param organizationId The ID of the organization
     * @param id the ID of the SmtpSetting to update
     * @return the updated SmtpSetting entity
     */
    public Mono<SmtpSetting> markAsDefault(UUID organizationId, UUID id);

    /**
     * Deletes an SmtpSetting by its ID.
     *
     * @param organizationId The ID of the organization
     * @param id the ID of the SmtpSetting to delete
     */
    public Mono<Void> deleteSmtpSetting(UUID organizationId, UUID id);
}