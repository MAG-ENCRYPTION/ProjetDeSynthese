package ProjetDeSyntheseNotification.notification.controller;

import ProjetDeSyntheseNotification.notification.dto.EMAILNotificationDTO;
import ProjetDeSyntheseNotification.notification.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/notifications/email")
public class EmailNotificationController {

    private final EmailService emailService;

    @Autowired
    public EmailNotificationController(EmailService emailService) {
        this.emailService = emailService;
    }

    @PostMapping
    public ResponseEntity<EMAILNotificationDTO> createEmailNotification(@RequestBody EMAILNotificationDTO emailDTO) {
        return ResponseEntity.ok(emailService.createEmailNotification(emailDTO));
    }

    @GetMapping
    public ResponseEntity<List<EMAILNotificationDTO>> getAllEmailNotifications() {
        return ResponseEntity.ok(emailService.getAllEmailNotifications());
    }

    @GetMapping("/{id}")
    public ResponseEntity<EMAILNotificationDTO> getEmailNotificationById(@PathVariable Long id) {
        Optional<EMAILNotificationDTO> emailDTO = emailService.getEmailNotificationById(id);
        return emailDTO.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<EMAILNotificationDTO> updateEmailNotification(@PathVariable Long id, @RequestBody EMAILNotificationDTO emailDTO) {
        return ResponseEntity.ok(emailService.updateEmailNotification(id, emailDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEmailNotification(@PathVariable Long id) {
        emailService.deleteEmailNotification(id);
        return ResponseEntity.noContent().build();
    }
}
