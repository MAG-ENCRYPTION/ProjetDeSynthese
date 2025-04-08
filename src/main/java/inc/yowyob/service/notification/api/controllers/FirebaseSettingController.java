package inc.yowyob.service.notification.api.controllers;

import inc.yowyob.service.notification.application.dto.firebase.CreateFirebaseSettingRequest;
import inc.yowyob.service.notification.application.dto.firebase.FirebaseSettingDto;
import inc.yowyob.service.notification.application.dto.firebase.UpdateFirebaseSettingRequest;
import inc.yowyob.service.notification.application.mappers.FirebaseSettingMapper;
import inc.yowyob.service.notification.application.service.FirebaseSettingService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

/**
 * Controller for managing FirebaseSetting entities.
 *
 * @author ETOUGUE
 */
@RestController
@RequestMapping("/organizations/{organization_id}/firebase-settings")
@RequiredArgsConstructor
@Validated
public class FirebaseSettingController {

    private final FirebaseSettingService firebaseSettingService;

    private final FirebaseSettingMapper firebaseSettingMapper;

    /**
     * Creates a new FirebaseSetting.
     *
     * @param organizationId The organization identifier
     * @param request        the DTO containing data for the new FirebaseSetting
     * @return a ResponseEntity with the created FirebaseSettingDto
     */
    @PostMapping
    public Mono<FirebaseSettingDto> create(@PathVariable("organization_id") UUID organizationId, @Valid @RequestBody CreateFirebaseSettingRequest request) {
        return firebaseSettingService.createFirebaseSetting(organizationId, request).map(firebaseSettingMapper::toDto);
    }

    /**
     * Retrieves an FirebaseSetting by its ID.
     *
     * @param organizationId The organization identifier
     * @param id             the ID of the FirebaseSetting to retrieve
     * @return a ResponseEntity with the FirebaseSettingDto if found, or a not found status
     */
    @GetMapping("/{firebase_setting_id}")
    public Mono<FirebaseSettingDto> getById(@PathVariable("organization_id") UUID organizationId, @PathVariable("firebase_setting_id") UUID id) {
        return firebaseSettingService.getFirebaseSettingById(organizationId, id).map(firebaseSettingMapper::toDto);
    }

    /**
     * Retrieves all FirebaseSettings.
     *
     * @param organizationId The organization identifier
     * @return a ResponseEntity with a list of FirebaseSettingDto
     */
    @GetMapping
    public Flux<FirebaseSettingDto> getAllFirebaseSettings(@PathVariable("organization_id") UUID organizationId) {
        return firebaseSettingService.getAllFirebaseSettings(organizationId).map(firebaseSettingMapper::toDto);
    }

    /**
     * Updates an existing FirebaseSetting.
     *
     * @param organizationId The organization identifier
     * @param id             the ID of the FirebaseSetting to update
     * @param request        the DTO containing updated values
     * @return a ResponseEntity with the updated FirebaseSettingDto
     */
    @PutMapping("/{firebase_setting_id}")
    public Mono<FirebaseSettingDto> updateFirebaseSetting(@PathVariable("organization_id") UUID organizationId, @PathVariable("firebase_setting_id") UUID id, @Valid @RequestBody UpdateFirebaseSettingRequest request) {
        return firebaseSettingService.updateFirebaseSetting(organizationId, id, request).map(firebaseSettingMapper::toDto);
    }

    /**
     * Updates an existing FirebaseSetting.
     *
     * @param organizationId The organization identifier
     * @param id             the ID of the FirebaseSetting to update
     * @return a ResponseEntity with the updated FirebaseSettingDto
     */
    @PutMapping("/{firebase_setting_id}/default")
    public Mono<FirebaseSettingDto> markFirebaseSettingAsDefault(@PathVariable("organization_id") UUID organizationId, @PathVariable("firebase_setting_id") UUID id) {
        return firebaseSettingService.markAsDefault(organizationId, id).map(firebaseSettingMapper::toDto);
    }

    /**
     * Deletes an FirebaseSetting by its ID.
     *
     * @param organizationId The organization identifier
     * @param id             the ID of the FirebaseSetting to delete
     * @return the status
     */
    @DeleteMapping("/{firebase_setting_id}")
    public Mono<Void> deleteFirebaseSetting(@PathVariable("organization_id") UUID organizationId, @PathVariable("firebase_setting_id") UUID id) {
        return firebaseSettingService.deleteFirebaseSetting(organizationId, id);
    }
}
