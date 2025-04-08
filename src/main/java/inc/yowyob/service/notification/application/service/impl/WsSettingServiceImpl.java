package inc.yowyob.service.notification.application.service.impl;


import inc.yowyob.service.notification.application.dto.ws.CreateWsSettingRequest;
import inc.yowyob.service.notification.application.dto.ws.UpdateWsSettingRequest;
import inc.yowyob.service.notification.application.exceptions.WsSettingNotFoundException;
import inc.yowyob.service.notification.application.mappers.WsSettingMapper;
import inc.yowyob.service.notification.application.service.WsSettingService;
import inc.yowyob.service.notification.infrastructure.persistence.entities.WsSetting;
import inc.yowyob.service.notification.infrastructure.persistence.entities.keys.WsSettingKey;
import inc.yowyob.service.notification.infrastructure.persistence.repository.WsSettingRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

/**
 * Service implementation for managing WsSetting entities.
 *
 * @author ETOUGUE
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class WsSettingServiceImpl implements WsSettingService {

    private final WsSettingRepository wsSettingRepository;

    private final WsSettingMapper wsSettingMapper;

    @Override
    public Mono<WsSetting> createWsSetting(UUID organizationId, CreateWsSettingRequest request) {
        log.info("Creating WsSetting: {}", request);
        return wsSettingRepository.insert(wsSettingMapper.toEntity(organizationId, request));
    }

    @Override
    public Mono<WsSetting> getWsSettingById(UUID organizationId, String systemId) {
        log.info("Fetching WsSetting with ID: {}", systemId);
        return wsSettingRepository.findById(WsSettingKey.from(organizationId, systemId))
                .switchIfEmpty(Mono.error(new WsSettingNotFoundException(systemId)));
    }

    @Override
    public Mono<WsSetting> getDefaultWsSettings(UUID organizationId) {
        return wsSettingRepository.findByKeyOrganizationIdAndIsDefault(organizationId, true).last()
                .switchIfEmpty(Mono.error(new WsSettingNotFoundException()));
    }

    public Mono<WsSetting> getWsSettingByIdOrDefault(UUID organizationId, String systemId) {
        return wsSettingRepository.findById(WsSettingKey.from(organizationId, systemId))
                .switchIfEmpty(getDefaultWsSettings(organizationId));
    }

    @Override
    public Flux<WsSetting> getAllWsSettings(UUID organizationId) {
        log.info("Fetching all WsSettings");
        return wsSettingRepository.findByKeyOrganizationId(organizationId);
    }

    @Override
    public Mono<WsSetting> updateWsSetting(UUID organizationId, String systemId, UpdateWsSettingRequest request) {
        log.info("Updating WsSetting with ID: {}", systemId);
        return this.getWsSettingById(organizationId, systemId).flatMap(wsSetting -> {
            return wsSettingRepository.save(wsSettingMapper.update(wsSetting, request));
        });
    }

    @Override
    public Mono<WsSetting> markAsDefault(UUID organizationId, String systemId) {
        log.info("Updating WsSetting with ID as default: {}", systemId);
        return this.getWsSettingById(organizationId, systemId).flatMap(wsSetting -> {
            return this.wsSettingRepository.findByKeyOrganizationId(organizationId)
                    .map(wsSetting1 -> wsSetting1.setIsDefault(wsSetting1.getSystemId().equals(systemId)))
                    .collectList()
                    .flatMapMany(wsSettingRepository::saveAll)
                    .filter(smsSetting2 -> smsSetting2.getSystemId().equals(systemId)).last();
        });
    }

    @Override
    public Mono<Void> deleteWsSetting(UUID organizationId, String systemId) {
        log.info("Deleting WsSetting with ID: {}", systemId);
        return this.getWsSettingById(organizationId, systemId).flatMap(wsSettingRepository::delete);
    }
}
