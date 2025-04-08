package inc.yowyob.service.notification.application.service.impl;

import inc.yowyob.service.notification.application.dto.template.CreateTemplateRequest;
import inc.yowyob.service.notification.application.dto.template.UpdateTemplateRequest;
import inc.yowyob.service.notification.application.exceptions.TemplateNotFoundException;
import inc.yowyob.service.notification.application.mappers.TemplateMapper;
import inc.yowyob.service.notification.application.service.ReminderService;
import inc.yowyob.service.notification.application.service.TemplateService;
import inc.yowyob.service.notification.infrastructure.persistence.entities.Template;
import inc.yowyob.service.notification.infrastructure.persistence.entities.keys.TemplateKey;
import inc.yowyob.service.notification.infrastructure.persistence.repository.TemplateRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

/**
 * Service implementation for managing Template entities.
 *
 * @author ETOUGUE
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class TemplateServiceImpl implements TemplateService {

    private final TemplateRepository templateRepository;

    private final TemplateMapper templateMapper;

    private final ReminderService reminderService;

    @Override
    public Mono<Template> createTemplate(UUID organizationId, CreateTemplateRequest request) {
        log.info("Creating Template: {}", request);
        return templateRepository.insert(templateMapper.toEntity(organizationId, request)).flatMap(template -> {
            if (request.getReminders() != null && !request.getReminders().isEmpty()) {
                reminderService.saveReminders(template.getId(), request.getReminders());
            }
            return Mono.just(template);
        });
    }

    @Override
    public Mono<Template> getTemplateById(UUID organizationId, UUID id) {
        log.info("Fetching Template with ID: {}", id);
        return templateRepository.findById(TemplateKey.from(organizationId, id))
                .switchIfEmpty(Mono.error(new TemplateNotFoundException(id)));
    }

    @Override
    public Mono<Template> getTemplateByIdOrDefault(UUID organizationId, UUID id) {
        return templateRepository.findById(TemplateKey.from(organizationId, id))
                .switchIfEmpty(getDefaultTemplate(organizationId));
    }

    @Override
    public Flux<Template> getAllTemplates(UUID organizationId) {
        log.info("Fetching all Templates");
        return templateRepository.findByKeyOrganizationId(organizationId);
    }

    public Mono<Template> getDefaultTemplate(UUID organizationId) {
        return templateRepository.findByKeyOrganizationIdAndIsDefault(organizationId, true).last()
                .switchIfEmpty(Mono.error(new TemplateNotFoundException()));
    }

    @Override
    public Mono<Template> updateTemplate(UUID organizationId, UUID id, UpdateTemplateRequest request) {
        log.info("Updating Template with ID: {}", id);
        return this.getTemplateById(organizationId, id).flatMap(emailTemplate -> {
            return templateRepository.save(templateMapper.update(emailTemplate, request));
        }).flatMap(template -> {
            if (request.getReminders() != null && !request.getReminders().isEmpty()) {
                reminderService.saveReminders(template.getId(), request.getReminders());
            }
            return Mono.just(template);
        });
    }

    @Override
    public Mono<Template> markAsDefault(UUID organizationId, UUID id) {
        log.info("Updating Email template with ID as default: {}", id);
        return this.getTemplateById(organizationId, id).flatMap(emailTemplate -> {
            return this.templateRepository.findByKeyOrganizationId(organizationId)
                    .map(emailTemplate1 -> emailTemplate1.setIsDefault(emailTemplate1.getId().equals(id)))
                    .collectList()
                    .flatMapMany(templateRepository::saveAll)
                    .filter(emailTemplate2 -> emailTemplate2.getId().equals(id))
                    .last();
        });
    }

    @Override
    public Mono<Void> deleteTemplate(UUID organizationId, UUID id) {
        log.info("Deleting Template with ID: {}", id);
        return this.getTemplateById(organizationId, id).flatMap(templateRepository::delete);
    }
}
