package inc.yowyob.service.notification.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import inc.yowyob.service.notification.dto.EmailNotificationDTO;
import inc.yowyob.service.notification.model.EmailNotification;
import inc.yowyob.service.notification.service.EmailService;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/notifications/email")
public class EmailNotificationController {


    @Autowired
    private EmailService emailService;

    @PostMapping
    public ResponseEntity<EmailNotificationDTO> createEmailNotification(@RequestBody EmailNotificationDTO emailDTO) {
        return ResponseEntity.ok(emailService.createEmailNotification(emailDTO));
    }

    @GetMapping
    public ResponseEntity<List<EmailNotification>> getAllEmailNotifications() {
        return ResponseEntity.ok(emailService.getAllEmailNotifications());
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmailNotificationDTO> getEmailNotificationById(@PathVariable UUID id) {
        Optional<EmailNotificationDTO> emailDTO = emailService.getEmailNotificationById(id);
        return emailDTO.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<EmailNotificationDTO> updateEmailNotification(@PathVariable UUID id, @RequestBody EmailNotificationDTO emailDTO) {
        return ResponseEntity.ok(emailService.updateEmailNotification(id, emailDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEmailNotification(@PathVariable UUID id) {
        emailService.deleteEmailNotification(id);
        return ResponseEntity.noContent().build();
    }
}
