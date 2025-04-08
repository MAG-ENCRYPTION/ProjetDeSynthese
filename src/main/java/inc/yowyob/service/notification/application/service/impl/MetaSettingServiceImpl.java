package inc.yowyob.service.notification.application.service.impl;


import inc.yowyob.service.notification.application.dto.meta.CreateMetaSettingRequest;
import inc.yowyob.service.notification.application.dto.meta.UpdateMetaSettingRequest;
import inc.yowyob.service.notification.application.exceptions.MetaSettingNotFoundException;
import inc.yowyob.service.notification.application.mappers.MetaSettingMapper;
import inc.yowyob.service.notification.application.service.MetaSettingService;
import inc.yowyob.service.notification.infrastructure.persistence.entities.MetaSetting;
import inc.yowyob.service.notification.infrastructure.persistence.entities.keys.MetaSettingKey;
import inc.yowyob.service.notification.infrastructure.persistence.repository.MetaSettingRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

/**
 * Service implementation for managing MetaSetting entities.
 *
 * @author ETOUGUE
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class MetaSettingServiceImpl implements MetaSettingService {


    private final MetaSettingRepository metaSettingRepository;

    private final MetaSettingMapper metaSettingMapper;

    @Override
    public Mono<MetaSetting> createMetaSetting(UUID organizationId, CreateMetaSettingRequest request) {
        log.info("Creating MetaSetting: {}", request);
        return metaSettingRepository.insert(metaSettingMapper.toEntity(organizationId, request));
    }

    @Override
    public Mono<MetaSetting> getMetaSettingById(UUID organizationId, UUID id) {
        log.info("Fetching MetaSetting with ID: {}", id);
        return metaSettingRepository.findById(MetaSettingKey.from(organizationId, id))
                .switchIfEmpty(Mono.error(new MetaSettingNotFoundException(id)));
    }

    public Mono<MetaSetting> getDefaultMetaSettings(UUID organizationId) {
        return metaSettingRepository.findByKeyOrganizationIdAndIsDefault(organizationId, true).last()
                .switchIfEmpty(Mono.error(new MetaSettingNotFoundException()));
    }

    public Mono<MetaSetting> getMetaSettingByIdOrDefault(UUID organizationId, UUID id) {
        return metaSettingRepository.findById(MetaSettingKey.from(organizationId, id))
                .switchIfEmpty(getDefaultMetaSettings(organizationId));
    }

    @Override
    public Flux<MetaSetting> getAllMetaSettings(UUID organizationId) {
        log.info("Fetching all meta settings");
        return metaSettingRepository.findByKeyOrganizationId(organizationId);
    }

    @Override
    public Mono<MetaSetting> updateMetaSetting(UUID organizationId, UUID id, UpdateMetaSettingRequest request) {
        log.info("Updating  meta setting with ID: {}", id);
        return this.getMetaSettingById(organizationId, id).flatMap(metaSetting -> {
            return metaSettingRepository.save(metaSettingMapper.update(metaSetting, request));
        });
    }

    @Override
    public Mono<MetaSetting> markAsDefault(UUID organizationId, UUID id) {
        log.info("Updating MetaSetting with ID as default: {}", id);
        return this.getMetaSettingById(organizationId, id).flatMap(metaSetting -> {
            return this.metaSettingRepository.findByKeyOrganizationId(organizationId)
                    .map(metaSetting1 -> metaSetting1.setIsDefault(metaSetting1.getId().equals(id)))
                    .collectList()
                    .flatMapMany(metaSettingRepository::saveAll)
                    .filter(metaSetting2 -> metaSetting2.getId().equals(id))
                    .last();
        });
    }

    @Override
    public Mono<Void> deleteMetaSetting(UUID organizationId, UUID id) {
        log.info("Deleting MetaSetting with ID: {}", id);
        return this.getMetaSettingById(organizationId, id).flatMap(metaSettingRepository::delete);
    }
}
