package inc.yowyob.service.notification.application.service;

import inc.yowyob.service.notification.application.dto.designtemplate.CreateDesignTemplateRequest;
import inc.yowyob.service.notification.application.dto.designtemplate.UpdateDesignTemplateRequest;
import inc.yowyob.service.notification.infrastructure.persistence.entities.DesignTemplate;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

/**
 * @author ETOUGUE
 */
public interface DesignTemplateService {
    /**
     * Creates a new DesignTemplate.
     *
     * @param organizationId the ID of the organization
     * @param request        the DesignTemplate object to be created
     * @return the created DesignTemplate object
     */
    public Mono<DesignTemplate> createDesignTemplate(UUID organizationId, CreateDesignTemplateRequest request);

    /**
     * Retrieves a DesignTemplate by its ID.
     *
     * @param organizationId the ID of the organization
     * @param id             the ID of the DesignTemplate to retrieve
     * @return the found DesignTemplate object, or null if not found
     */
    public Mono<DesignTemplate> getDesignTemplateById(UUID organizationId, UUID id);

    /**
     * Retrieves a DesignTemplate by its ID.
     *
     * @param organizationId the ID of the organization
     * @param id             the ID of the DesignTemplate to retrieve
     * @return the found DesignTemplate object, or null if not found
     */
    public Mono<DesignTemplate> getDesignTemplateByIdOrDefault(UUID organizationId, UUID id);

    /**
     * Retrieves a DesignTemplate by its ID.
     *
     * @param organizationId the ID of the organization
     * @return the found DesignTemplate object, or null if not found
     */
    public Mono<DesignTemplate> getDefaultDesignTemplate(UUID organizationId);

    /**
     * Retrieves all DesignTemplates.
     *
     * @param organizationId the ID of the organization
     * @return a list of all DesignTemplate objects
     */
    public Flux<DesignTemplate> getAllDesignTemplates(UUID organizationId);

    /**
     * Updates an existing DesignTemplate.
     *
     * @param organizationId the ID of the organization
     * @param id             the DesignTemplate id
     * @param request        the DesignTemplate object with updated values
     * @return the updated DesignTemplate object
     */
    public Mono<DesignTemplate> updateDesignTemplate(UUID organizationId, UUID id, UpdateDesignTemplateRequest request);

    /**
     * Updates an existing DesignTemplate as default.
     *
     * @param organizationId the ID of the organization
     * @param id             the DesignTemplate id
     * @return the updated DesignTemplate object
     */
    public Mono<DesignTemplate> markAsDefault(UUID organizationId, UUID id);


    /**
     * Deletes a DesignTemplate by its ID.
     *
     * @param organizationId the ID of the organization
     * @param id             the ID of the DesignTemplate to delete
     */
    public Mono<Void> deleteDesignTemplate(UUID organizationId, UUID id);
}
