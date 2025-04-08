package inc.yowyob.service.notification.application.service.impl;


import inc.yowyob.service.notification.application.dto.twilio.CreateTwilioSettingRequest;
import inc.yowyob.service.notification.application.dto.twilio.UpdateTwilioSettingRequest;
import inc.yowyob.service.notification.application.exceptions.TwilioSettingNotFoundException;
import inc.yowyob.service.notification.application.mappers.TwilioSettingMapper;
import inc.yowyob.service.notification.application.service.TwilioSettingService;
import inc.yowyob.service.notification.infrastructure.persistence.entities.TwilioSetting;
import inc.yowyob.service.notification.infrastructure.persistence.entities.keys.SmsSettingKey;
import inc.yowyob.service.notification.infrastructure.persistence.repository.TwilioSettingRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

/**
 * Service implementation for managing TwilioSetting entities.
 *
 * @author ETOUGUE
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class TwilioSettingServiceImpl implements TwilioSettingService {

    private final TwilioSettingRepository twilioSettingRepository;

    private final TwilioSettingMapper twilioSettingMapper;

    @Override
    public Mono<TwilioSetting> createTwilioSetting(UUID organizationId, CreateTwilioSettingRequest request) {
        log.info("Creating TwilioSetting: {}", request);
        return twilioSettingRepository.insert(twilioSettingMapper.toEntity(organizationId, request));
    }

    @Override
    public Mono<TwilioSetting> getTwilioSettingById(UUID organizationId, UUID id) {
        log.info("Fetching TwilioSetting with ID: {}", id);
        return twilioSettingRepository.findById(SmsSettingKey.from(organizationId, id))
                .switchIfEmpty(Mono.error(new TwilioSettingNotFoundException(id)));
    }

    @Override
    public Mono<TwilioSetting> getDefaultTwilioSettings(UUID organizationId) {
        return twilioSettingRepository.findByKeyOrganizationIdAndIsDefault(organizationId, true).last()
                .switchIfEmpty(Mono.error(new TwilioSettingNotFoundException()));
    }

    public Mono<TwilioSetting> getTwilioSettingByIdOrDefault(UUID organizationId, UUID id) {
        return twilioSettingRepository.findById(SmsSettingKey.from(organizationId, id))
                .switchIfEmpty(getDefaultTwilioSettings(organizationId));
    }

    @Override
    public Flux<TwilioSetting> getAllTwilioSettings(UUID organizationId) {
        log.info("Fetching all TwilioSettings");
        return twilioSettingRepository.findByKeyOrganizationId(organizationId);
    }

    @Override
    public Mono<TwilioSetting> updateTwilioSetting(UUID organizationId, UUID id, UpdateTwilioSettingRequest request) {
        log.info("Updating TwilioSetting with ID: {}", id);
        return this.getTwilioSettingById(organizationId, id).flatMap(twilioSetting -> {
            return twilioSettingRepository.save(twilioSettingMapper.update(twilioSetting, request));
        });
    }

    @Override
    public Mono<TwilioSetting> markAsDefault(UUID organizationId, UUID id) {
        log.info("Updating TwilioSetting with ID as default: {}", id);
        return this.getTwilioSettingById(organizationId, id).flatMap(twilioSetting -> {
            return this.twilioSettingRepository.findByKeyOrganizationId(organizationId)
                    .map(twilioSetting1 -> twilioSetting1.setIsDefault(twilioSetting1.getId().equals(id)))
                    .collectList()
                    .flatMapMany(twilioSettingRepository::saveAll)
                    .filter(smsSetting2 -> smsSetting2.getId().equals(id)).last();
        });
    }

    @Override
    public Mono<Void> deleteTwilioSetting(UUID organizationId, UUID id) {
        log.info("Deleting TwilioSetting with ID: {}", id);
        return this.getTwilioSettingById(organizationId, id).flatMap(twilioSettingRepository::delete);
    }
}
