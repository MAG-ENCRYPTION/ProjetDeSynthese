package inc.yowyob.service.notification.application.service.impl;


import inc.yowyob.service.notification.application.dto.firebase.CreateFirebaseSettingRequest;
import inc.yowyob.service.notification.application.dto.firebase.UpdateFirebaseSettingRequest;
import inc.yowyob.service.notification.application.exceptions.FirebaseSettingNotFoundException;
import inc.yowyob.service.notification.application.mappers.FirebaseSettingMapper;
import inc.yowyob.service.notification.application.service.FirebaseSettingService;
import inc.yowyob.service.notification.infrastructure.persistence.entities.FirebaseSetting;
import inc.yowyob.service.notification.infrastructure.persistence.entities.keys.FirebaseSettingKey;
import inc.yowyob.service.notification.infrastructure.persistence.repository.FirebaseSettingRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

/**
 * Service implementation for managing FirebaseSetting entities.
 *
 * @author ETOUGUE
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class FirebaseSettingServiceImpl implements FirebaseSettingService {

    private final FirebaseSettingRepository firebaseSettingRepository;

    private final FirebaseSettingMapper firebaseSettingMapper;

    @Override
    public Mono<FirebaseSetting> createFirebaseSetting(UUID organizationId, CreateFirebaseSettingRequest request) {
        log.info("Creating FirebaseSetting: {}", request);
        return firebaseSettingRepository.insert(firebaseSettingMapper.toEntity(organizationId, request));
    }

    @Override
    public Mono<FirebaseSetting> getFirebaseSettingById(UUID organizationId, UUID id) {
        log.info("Fetching FirebaseSetting with ID: {}", id);
        return firebaseSettingRepository.findById(FirebaseSettingKey.from(organizationId, id))
                .switchIfEmpty(Mono.error(new FirebaseSettingNotFoundException(id)));
    }

    @Override
    public Mono<FirebaseSetting> getDefaultFirebaseSettings(UUID organizationId) {
        return firebaseSettingRepository.findByKeyOrganizationIdAndIsDefault(organizationId, true).last()
                .switchIfEmpty(Mono.error(new FirebaseSettingNotFoundException()));
    }

    public Mono<FirebaseSetting> getFirebaseSettingByIdOrDefault(UUID organizationId, UUID id) {
        return firebaseSettingRepository.findById(FirebaseSettingKey.from(organizationId, id))
                .switchIfEmpty(getDefaultFirebaseSettings(organizationId));
    }

    @Override
    public Flux<FirebaseSetting> getAllFirebaseSettings(UUID organizationId) {
        log.info("Fetching all FirebaseSettings");
        return firebaseSettingRepository.findByKeyOrganizationId(organizationId);
    }

    @Override
    public Mono<FirebaseSetting> updateFirebaseSetting(UUID organizationId, UUID id, UpdateFirebaseSettingRequest request) {
        log.info("Updating FirebaseSetting with ID: {}", id);
        return this.getFirebaseSettingById(organizationId, id).flatMap(firebaseSetting -> {
            return firebaseSettingRepository.save(firebaseSettingMapper.update(firebaseSetting, request));
        });
    }

    @Override
    public Mono<FirebaseSetting> markAsDefault(UUID organizationId, UUID id) {
        log.info("Updating FirebaseSetting with ID as default: {}", id);
        return this.getFirebaseSettingById(organizationId, id).flatMap(firebaseSetting -> {
            return this.firebaseSettingRepository.findByKeyOrganizationId(organizationId)
                    .map(firebaseSetting1 -> firebaseSetting1.setIsDefault(firebaseSetting1.getId().equals(id)))
                    .collectList()
                    .flatMapMany(firebaseSettingRepository::saveAll)
                    .filter(firebaseSetting1 -> firebaseSetting1.getId().equals(id)).last();
        });
    }

    @Override
    public Mono<Void> deleteFirebaseSetting(UUID organizationId, UUID id) {
        log.info("Deleting FirebaseSetting with ID: {}", id);
        return this.getFirebaseSettingById(organizationId, id).flatMap(firebaseSettingRepository::delete);
    }
}
