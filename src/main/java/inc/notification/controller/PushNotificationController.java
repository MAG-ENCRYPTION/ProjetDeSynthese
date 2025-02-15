package inc.notification.controller;

import org.apache.hc.core5.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import inc.notification.dto.Message;
import inc.notification.dto.PushNotificationDTO;
import inc.notification.model.PushNotification;
import inc.notification.service.PushService;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@CrossOrigin(originPatterns = "*")
@RestController
@RequestMapping("/api/v1/notifications/push")

public class PushNotificationController {

    private final PushService pushService;

    @Autowired
    public PushNotificationController(PushService pushService) {
        this.pushService = pushService;
    }
    
    @PostMapping("/send")
        public ResponseEntity<List<PushNotification>> sendNotification(@RequestBody PushNotificationDTO pushDTO) {
            // Appel au service pour créer les notifications pour tous les receiverIds
            List<PushNotification> savedNotifications = pushService.createPushNotification(pushDTO);
            return ResponseEntity.ok(savedNotifications); // Retourne la liste des notifications créées
        }

        // Méthode d'exemple pour un autre post (non utilisée ici)
        public String postMethodName(@RequestBody String entity) {
            //TODO: process POST request
            return entity;
        }

    @GetMapping("/notifications/{receiverId}")
        public ResponseEntity<List<Message>> getNotificationsByReceiverId(@PathVariable UUID receiverId) {
            try {
                // Récupérer les notifications du service
                List<Message> notifications = pushService.getNotificationsByReceiverId(receiverId);
        
                // Retourner la liste de notifications dans la réponse
                return ResponseEntity.ok(notifications);
            } catch (Exception e) {
                System.err.println("Erreur lors de la récupération des notifications : " + e.getMessage());
        
                // Retourner un statut interne serveur (500) en cas d'erreur
                return ResponseEntity.status(HttpStatus.SC_INTERNAL_SERVER_ERROR).build();
            }
        }
        
    //@PostMapping("/group")
    //public ResponseEntity<PushNotification> createGroupPushNotification(@RequestBody PushNotificationDTO pushDTO) {
        //PushNotification createdNotification = pushService.createPushNotification(pushDTO);
        //return ResponseEntity.ok(createdNotification);
    //}

    @GetMapping
    public ResponseEntity<List<PushNotification>> getAllPushNotifications() {
        List<PushNotification> notifications = pushService.getAllPushNotifications();
        return ResponseEntity.ok(notifications);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PushNotification> getPushNotificationById(@PathVariable UUID id) {
        Optional<PushNotification> pushDTO = pushService.getPushNotificationById(id);
        return pushDTO.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<PushNotification> updatePushNotification(@PathVariable UUID id, @RequestBody PushNotificationDTO pushDTO) {
        PushNotification updatedNotification = pushService.updatePushNotification(id, pushDTO);
        return ResponseEntity.ok(updatedNotification);
    }



    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePushNotification(@PathVariable UUID id) {
        pushService.deletePushNotification(id);
        return ResponseEntity.noContent().build();
    }
}
