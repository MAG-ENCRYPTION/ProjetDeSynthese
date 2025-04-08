package inc.yowyob.service.notification.api.controllers;

import inc.yowyob.service.notification.application.dto.designtemplate.CreateDesignTemplateRequest;
import inc.yowyob.service.notification.application.dto.designtemplate.DesignTemplateDto;
import inc.yowyob.service.notification.application.dto.designtemplate.UpdateDesignTemplateRequest;
import inc.yowyob.service.notification.application.mappers.DesignTemplateMapper;
import inc.yowyob.service.notification.application.service.DesignTemplateService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

/**
 * @author ETOUGUE
 */
@RestController
@RequestMapping("/organizations/{organization_id}/design-templates")
@Validated
@RequiredArgsConstructor
public class DesignTemplateController {

    private final DesignTemplateService designTemplateService;

    private final DesignTemplateMapper designTemplateMapper;

    /**
     * Creates a new DesignTemplate.
     *
     * @param request the DesignTemplate object to create
     * @return the created DesignTemplate object
     */
    @PostMapping
    public Mono<DesignTemplateDto> createDesignTemplate(@PathVariable("organization_id") UUID organizationId, @Valid @RequestBody CreateDesignTemplateRequest request) {
        return designTemplateService.createDesignTemplate(organizationId, request).map(designTemplateMapper::toDto);
    }

    /**
     * Retrieves a DesignTemplate by its ID.
     *
     * @param id the ID of the DesignTemplate to retrieve
     * @return the requested DesignTemplate object
     */
    @GetMapping("/{design_template_id}")
    public Mono<DesignTemplateDto> getDesignTemplateById(@PathVariable("organization_id") UUID organizationId, @PathVariable("design_template_id") UUID id) {
        return designTemplateService.getDesignTemplateById(organizationId, id).map(designTemplateMapper::toDto);
    }

    /**
     * Retrieves all DesignTemplates.
     *
     * @return a list of all available DesignTemplate objects
     */
    @GetMapping
    public Flux<DesignTemplateDto> getAllDesignTemplate(@PathVariable("organization_id") UUID organizationId) {
        return designTemplateService.getAllDesignTemplates(organizationId).map(designTemplateMapper::toDto);
    }

    /**
     * Updates an existing DesignTemplate.
     *
     * @param id      the DesignTemplate id
     * @param request the DesignTemplate object with updated values
     * @return the updated DesignTemplate object
     */
    @PutMapping("/{design_template_id}")
    public Mono<DesignTemplateDto> updateDesignTemplate(@PathVariable("organization_id") UUID organizationId, @PathVariable("design_template_id") UUID id, @Valid @RequestBody UpdateDesignTemplateRequest request) {
        return designTemplateService.updateDesignTemplate(organizationId, id, request).map(designTemplateMapper::toDto);
    }

    /**
     * Updates an existing DesignTemplate.
     *
     * @param id the DesignTemplate id
     * @return the updated DesignTemplate object
     */
    @PutMapping("/{design_template_id}/default")
    public Mono<DesignTemplateDto> markAsDefault(@PathVariable("organization_id") UUID organizationId, @PathVariable("design_template_id") UUID id) {
        return designTemplateService.markAsDefault(organizationId, id).map(designTemplateMapper::toDto);
    }

    /**
     * Deletes a DesignTemplate by its ID.
     *
     * @param id the ID of the DesignTemplate to delete
     * @return the status
     */
    @DeleteMapping("/{design_template_id}")
    public Mono<Void> delete(@PathVariable("organization_id") UUID organizationId, @PathVariable("design_template_id") UUID id) {
        return designTemplateService.deleteDesignTemplate(organizationId, id);
    }
}
