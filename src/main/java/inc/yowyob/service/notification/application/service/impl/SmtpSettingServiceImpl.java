package inc.yowyob.service.notification.application.service.impl;


import inc.yowyob.service.notification.application.dto.smtp.CreateSmtpSettingRequest;
import inc.yowyob.service.notification.application.dto.smtp.UpdateSmtpSettingRequest;
import inc.yowyob.service.notification.application.exceptions.SmtpSettingNotFoundException;
import inc.yowyob.service.notification.application.mappers.SmtpSettingMapper;
import inc.yowyob.service.notification.application.service.SmtpSettingService;
import inc.yowyob.service.notification.infrastructure.persistence.entities.SmtpSetting;
import inc.yowyob.service.notification.infrastructure.persistence.entities.keys.SmtpSettingKey;
import inc.yowyob.service.notification.infrastructure.persistence.repository.SmtpSettingRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

/**
 * Service implementation for managing SmtpSetting entities.
 *
 * @author ETOUGUE
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class SmtpSettingServiceImpl implements SmtpSettingService {


    private final SmtpSettingRepository smtpSettingRepository;

    private final SmtpSettingMapper smtpSettingMapper;

    @Override
    public Mono<SmtpSetting> createSmtpSetting(UUID organizationId, CreateSmtpSettingRequest request) {
        log.info("Creating SmtpSetting: {}", request);
        return smtpSettingRepository.insert(smtpSettingMapper.toEntity(organizationId, request));
    }

    @Override
    public Mono<SmtpSetting> getSmtpSettingById(UUID organizationId, UUID id) {
        log.info("Fetching SmtpSetting with ID: {}", id);
        return smtpSettingRepository.findById(SmtpSettingKey.from(organizationId, id))
                .switchIfEmpty(Mono.error(new SmtpSettingNotFoundException(id)));
    }

    public Mono<SmtpSetting> getDefaultSmtpSettings(UUID organizationId) {
        return smtpSettingRepository.findByKeyOrganizationIdAndIsDefault(organizationId, true).last()
                .switchIfEmpty(Mono.error(new SmtpSettingNotFoundException()));
    }

    public Mono<SmtpSetting> getSmtpSettingByIdOrDefault(UUID organizationId, UUID id) {
        return smtpSettingRepository.findById(SmtpSettingKey.from(organizationId, id))
                .switchIfEmpty(getDefaultSmtpSettings(organizationId));
    }

    @Override
    public Flux<SmtpSetting> getAllSmtpSettings(UUID organizationId) {
        log.info("Fetching all smtp settings");
        return smtpSettingRepository.findByKeyOrganizationId(organizationId);
    }

    @Override
    public Mono<SmtpSetting> updateSmtpSetting(UUID organizationId, UUID id, UpdateSmtpSettingRequest request) {
        log.info("Updating  smtp setting with ID: {}", id);
        return this.getSmtpSettingById(organizationId, id).flatMap(smtpSetting -> {
            return smtpSettingRepository.save(smtpSettingMapper.update(smtpSetting, request));
        });
    }

    @Override
    public Mono<SmtpSetting> markAsDefault(UUID organizationId, UUID id) {
        log.info("Updating SmtpSetting with ID as default: {}", id);
        return this.getSmtpSettingById(organizationId, id).flatMap(smtpSetting -> {
            return this.smtpSettingRepository.findByKeyOrganizationId(organizationId)
                    .map(smtpSetting1 -> smtpSetting1.setIsDefault(smtpSetting1.getId().equals(id)))
                    .collectList()
                    .flatMapMany(smtpSettingRepository::saveAll)
                    .filter(smtpSetting2 -> smtpSetting2.getId().equals(id))
                    .last();
        });
    }

    @Override
    public Mono<Void> deleteSmtpSetting(UUID organizationId, UUID id) {
        log.info("Deleting SmtpSetting with ID: {}", id);
        return this.getSmtpSettingById(organizationId, id).flatMap(smtpSettingRepository::delete);
    }
}
