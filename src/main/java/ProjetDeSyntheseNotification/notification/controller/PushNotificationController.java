package ProjetDeSyntheseNotification.notification.controller;

import ProjetDeSyntheseNotification.notification.dto.PushNotificationDTO;
import ProjetDeSyntheseNotification.notification.service.PushService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import ProjetDeSyntheseNotification.notification.model.PushNotification;

@RestController
@RequestMapping("/api/v1/notifications/push")
public class PushNotificationController {

    private final PushService pushService;

    @Autowired
    public PushNotificationController(PushService pushService) {
        this.pushService = pushService;
    }

    @PostMapping("/simple")
    public ResponseEntity<PushNotification> createSimplePushNotification(@RequestBody PushNotificationDTO pushDTO) {
        return ResponseEntity.ok(pushService.createPushNotification(pushDTO));
    }

    @PostMapping("/group")
    public ResponseEntity<PushNotification> createGroupPushNotification(@RequestBody PushNotificationDTO pushDTO) {
        return ResponseEntity.ok(pushService.createPushNotification(pushDTO));
    }

    @GetMapping
    public ResponseEntity<List<PushNotification>> getAllPushNotifications() {
        return ResponseEntity.ok(pushService.getAllPushNotifications());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PushNotification> getPushNotificationById(@PathVariable Long id) {
        Optional<PushNotification> pushDTO = pushService.getPushNotificationById(id);
        return pushDTO.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<PushNotification> updatePushNotification(@PathVariable Long id, @RequestBody PushNotificationDTO pushDTO) {
        return ResponseEntity.ok(pushService.updatePushNotification(id, pushDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePushNotification(@PathVariable Long id) {
        pushService.deletePushNotification(id);
        return ResponseEntity.noContent().build();
    }
}
