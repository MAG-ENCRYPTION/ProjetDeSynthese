package ProjetDeSyntheseNotification.notification.controller;

import ProjetDeSyntheseNotification.notification.dto.SmsNotificationDTO;
import ProjetDeSyntheseNotification.notification.model.SmsNotification;
import ProjetDeSyntheseNotification.notification.service.SmsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/notifications/sms")
@Tag(name = "Sms Notification Controller")
public class SmsNotificationController {

    private final SmsService smsService;

    @Autowired

    public SmsNotificationController(SmsService smsService) {
        this.smsService = smsService;
    }

    @PostMapping
    public ResponseEntity<SmsNotification> createSmsNotification(@RequestBody SmsNotificationDTO smsDTO) {
        return ResponseEntity.ok(smsService.createSmsNotification(smsDTO));
    }

    @GetMapping
    public ResponseEntity<List<SmsNotification>> getAllSmsNotifications() {
        return ResponseEntity.ok(smsService.getAllSmsNotifications());
    }

    @GetMapping("/{id}")
    public ResponseEntity<SmsNotification> getSmsNotificationById(@PathVariable Long id) {
        Optional<SmsNotification> smsDTO = smsService.getSmsNotificationById(id);
        return smsDTO.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<SmsNotification> updateSmsNotification(@PathVariable Long id, @RequestBody SmsNotificationDTO smsDTO) {
        return ResponseEntity.ok(smsService.updateSmsNotification(id, smsDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSmsNotification(@PathVariable Long id) {
        smsService.deleteSmsNotification(id);
        return ResponseEntity.noContent().build();
    }
}
