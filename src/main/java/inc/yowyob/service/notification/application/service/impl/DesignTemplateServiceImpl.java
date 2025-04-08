package inc.yowyob.service.notification.application.service.impl;

import inc.yowyob.service.notification.application.dto.designtemplate.CreateDesignTemplateRequest;
import inc.yowyob.service.notification.application.dto.designtemplate.UpdateDesignTemplateRequest;
import inc.yowyob.service.notification.application.exceptions.DesignTemplateNotFoundException;
import inc.yowyob.service.notification.application.mappers.DesignTemplateMapper;
import inc.yowyob.service.notification.application.service.DesignTemplateService;
import inc.yowyob.service.notification.infrastructure.persistence.entities.DesignTemplate;
import inc.yowyob.service.notification.infrastructure.persistence.entities.keys.DesignTemplateKey;
import inc.yowyob.service.notification.infrastructure.persistence.repository.DesignTemplateRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

/**
 * @author douglas
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class DesignTemplateServiceImpl implements DesignTemplateService {

    private final DesignTemplateRepository designTemplateRepository;

    private final DesignTemplateMapper designTemplateMapper;

    @Override
    public Mono<DesignTemplate> createDesignTemplate(UUID organizationId, CreateDesignTemplateRequest request) {
        log.info("Creating DesignTemplate: {}", request);
        return designTemplateRepository.insert(designTemplateMapper.toEntity(organizationId, request));
    }

    @Override
    public Mono<DesignTemplate> getDesignTemplateById(UUID organizationId, UUID id) {
        log.info("Fetching DesignTemplate with ID: {}", id);
        return designTemplateRepository.findById(DesignTemplateKey.from(organizationId, id))
                .switchIfEmpty(Mono.error(new DesignTemplateNotFoundException(id)));
    }

    public Mono<DesignTemplate> getDesignTemplateByIdOrDefault(UUID organizationId, UUID id) {
        return getDesignTemplateById(organizationId, id).switchIfEmpty(getDefaultDesignTemplate(organizationId));
    }

    @Override
    public Mono<DesignTemplate> getDefaultDesignTemplate(UUID organizationId) {
        return designTemplateRepository.findByKeyOrganizationIdAndIsDefault(organizationId, true).last()
                .switchIfEmpty(Mono.error(new DesignTemplateNotFoundException()));
    }

    @Override
    public Flux<DesignTemplate> getAllDesignTemplates(UUID organizationId) {
        log.info("Fetching all design templates");
        return designTemplateRepository.findByKeyOrganizationId(organizationId);
    }

    @Override
    public Mono<DesignTemplate> updateDesignTemplate(UUID organizationId, UUID id, UpdateDesignTemplateRequest request) {
        log.info("Updating DesignTemplate: {}", request);
        return this.getDesignTemplateById(organizationId, id).flatMap(designTemplate -> {
            return designTemplateRepository.save(designTemplateMapper.update(designTemplate, request));
        });
    }

    @Override
    public Mono<DesignTemplate> markAsDefault(UUID organizationId, UUID id) {
        log.info("Updating SmtpSetting with ID as default: {}", id);
        return this.getDesignTemplateById(organizationId, id).flatMap(smtpSetting -> {
            return this.designTemplateRepository.findByKeyOrganizationId(organizationId)
                    .map(designTemplate1 -> designTemplate1.setIsDefault(designTemplate1.getId().equals(id)))
                    .collectList()
                    .flatMapMany(designTemplateRepository::saveAll)
                    .filter(designTemplate2 -> designTemplate2.getId().equals(id))
                    .last();
        });
    }

    @Override
    public Mono<Void> deleteDesignTemplate(UUID organizationId, UUID id) {
        log.info("Deleting DesignTemplate with ID: {}", id);
        return this.getDesignTemplateById(organizationId, id).flatMap(designTemplateRepository::delete);
    }
}
