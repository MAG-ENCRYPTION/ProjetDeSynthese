package inc.yowyob.service.notification.application.service;

import inc.yowyob.service.notification.application.dto.template.CreateTemplateRequest;
import inc.yowyob.service.notification.application.dto.template.UpdateTemplateRequest;
import inc.yowyob.service.notification.infrastructure.persistence.entities.Template;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

/**
 * @author ETOUGUE
 * Service interface for managing EmailTemplate entities.
 */
public interface TemplateService {

    /**
     * Creates a new EmailTemplate.
     *
     * @param organizationId The ID of the organization
     * @param request the DTO containing data for the new EmailTemplate
     * @return the created EmailTemplate entity
     */
    public Mono<Template> createTemplate(UUID organizationId, CreateTemplateRequest request);

    /**
     * Retrieves an EmailTemplate by its ID.
     *
     * @param organizationId The ID of the organization
     * @param id the ID of the EmailTemplate to retrieve
     * @return the found EmailTemplate entity, or null if not found
     */
    public Mono<Template> getTemplateById(UUID organizationId, UUID id);

    /**
     * Retrieves an EmailTemplate by its ID or default.
     *
     * @param organizationId The ID of the organization
     * @param id the ID of the EmailTemplate to retrieve
     * @return the found EmailTemplate entity, or null if not found
     */
    public Mono<Template> getTemplateByIdOrDefault(UUID organizationId, UUID id);

    /**
     * Retrieves all EmailTemplates.
     *
     * @param organizationId The ID of the organization
     * @return a list of all EmailTemplate entities
     */
    public Flux<Template> getAllTemplates(UUID organizationId);

    /**
     * Retrieves all EmailTemplates.
     *
     * @param organizationId The ID of the organization
     * @return a list of all EmailTemplate entities
     */
    public Mono<Template> getDefaultTemplate(UUID organizationId);

    /**
     * Updates an existing EmailTemplate.
     *
     * @param organizationId The ID of the organization
     * @param id                     the ID of the EmailTemplate to update
     * @param request the DTO containing updated data
     * @return the updated EmailTemplate entity
     */
    public Mono<Template> updateTemplate(UUID organizationId, UUID id, UpdateTemplateRequest request);

    /**
     * Updates an existing EmailTemplate.
     *
     * @param organizationId The ID of the organization
     * @param id  the ID of the EmailTemplate to update
     * @return the updated EmailTemplate entity
     */
    public Mono<Template> markAsDefault(UUID organizationId, UUID id);

    /**
     * Deletes an EmailTemplate by its ID.
     *
     * @param organizationId The ID of the organization
     * @param id the ID of the EmailTemplate to delete
     */
    public Mono<Void> deleteTemplate(UUID organizationId, UUID id);
}
