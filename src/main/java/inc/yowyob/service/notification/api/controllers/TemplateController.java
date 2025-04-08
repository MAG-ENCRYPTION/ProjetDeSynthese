package inc.yowyob.service.notification.api.controllers;

import inc.yowyob.service.notification.application.dto.template.CreateTemplateRequest;
import inc.yowyob.service.notification.application.dto.template.TemplateDto;
import inc.yowyob.service.notification.application.dto.template.UpdateTemplateRequest;
import inc.yowyob.service.notification.application.mappers.TemplateMapper;
import inc.yowyob.service.notification.application.service.TemplateService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

/**
 * Controller for managing EmailTemplate entities.
 *
 * @author douglas
 */
@RestController
@RequestMapping("/organizations/{organization_id}/email-templates")
@Validated
@RequiredArgsConstructor
public class TemplateController {

    private final TemplateService templateService;

    private final TemplateMapper templateMapper;

    /**
     * Creates a new EmailTemplate.
     *
     * @param organizationId The organization identifier
     * @param request        the DTO containing data for the new
     *                       EmailTemplate
     * @return a ResponseEntity with the created EmailTemplateDto
     */
    @PostMapping
    public Mono<TemplateDto> createTemplate(@PathVariable("organization_id") UUID organizationId, @Valid @RequestBody CreateTemplateRequest request) {
        return templateService.createTemplate(organizationId, request).map(templateMapper::toDto);
    }

    /**
     * Retrieves an EmailTemplate by its ID.
     *
     * @param id the ID of the EmailTemplate to retrieve
     * @return a ResponseEntity with the EmailTemplateDto if found, or a not
     * found status
     */
    @GetMapping("/{email_template_id}")
    public Mono<TemplateDto> getTemplateById(@PathVariable("organization_id") UUID organizationId, @PathVariable("email_template_id") UUID id) {
        return templateService.getTemplateById(organizationId, id).map(templateMapper::toDto);
    }

    /**
     * Retrieves an EmailTemplate by its ID.
     *
     * @param id the ID of the EmailTemplate to retrieve
     * @return a ResponseEntity with the EmailTemplateDto if found, or a not
     * found status
     */
    @GetMapping("/{email_template_id}/default")
    public Mono<TemplateDto> markTemplateAsDefault(@PathVariable("organization_id") UUID organizationId, @PathVariable("email_template_id") UUID id) {
        return templateService.markAsDefault(organizationId, id).map(templateMapper::toDto);
    }

    /**
     * Retrieves all EmailTemplates.
     *
     * @return a ResponseEntity with a list of EmailTemplateDto
     */
    @GetMapping
    public Flux<TemplateDto> getAllTemplates(@PathVariable("organization_id") UUID organizationId) {
        return templateService.getAllTemplates(organizationId).map(templateMapper::toDto);
    }

    /**
     * Updates an existing EmailTemplate.
     *
     * @param id      the ID of the EmailTemplate to update
     * @param request the DTO containing updated values
     * @return a ResponseEntity with the updated EmailTemplateDto
     */
    @PutMapping("/{email_template_id}")
    public Mono<TemplateDto> updateTemplate(@PathVariable("organization_id") UUID organizationId, @PathVariable("email_template_id") UUID id, @Valid @RequestBody UpdateTemplateRequest request) {
        return templateService.updateTemplate(organizationId, id, request).map(templateMapper::toDto);
    }

    /**
     * Deletes an EmailTemplate by its ID.
     *
     * @param id the ID of the EmailTemplate to delete
     * @return status
     */
    @DeleteMapping("/{id}")
    public Mono<Void> deleteTemplate(@PathVariable("organization_id") UUID organizationId, @PathVariable UUID id) {
        return templateService.deleteTemplate(organizationId, id);
    }
}
