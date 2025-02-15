package inc.yowyob.service.notification.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import inc.yowyob.service.notification.dto.WhatsAppNotificationDTO;
import inc.yowyob.service.notification.service.WhatsAppNotificationService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/notifications/whatsapp")
public class WhatsAppNotificationController {

    private final WhatsAppNotificationService whatsAppService;

    @Autowired
    public WhatsAppNotificationController(WhatsAppNotificationService whatsAppService) {
        this.whatsAppService = whatsAppService;
    }

    /**
     * Envoie un message WhatsApp.
     *
     * @param whatsAppNotificationDTO Le DTO contenant les informations du message.
     * @return Le DTO avec les informations du message envoyé.
     */
    @PostMapping
    public ResponseEntity<WhatsAppNotificationDTO> sendWhatsAppMessage(@RequestBody WhatsAppNotificationDTO whatsAppNotificationDTO) {
        whatsAppService.sendWhatsAppMessage(whatsAppNotificationDTO);
        return ResponseEntity.ok(whatsAppNotificationDTO);
    }

    /**
     * Récupère tous les messages WhatsApp envoyés.
     *
     * @return Une liste de DTO représentant les messages WhatsApp.
     */
    @GetMapping
    public ResponseEntity<List<WhatsAppNotificationDTO>> getAllWhatsAppMessages() {
        // Implémentez cette méthode si vous stockez les messages dans une base de données.
        // Pour l'instant, retournez une liste vide ou une réponse non implémentée.
        return ResponseEntity.notFound().build();
    }

    /**
     * Récupère un message WhatsApp par son ID.
     *
     * @param index L'ID du message.
     * @return Le DTO correspondant au message WhatsApp.
     */
    @GetMapping("/{index}")
    public ResponseEntity<WhatsAppNotificationDTO> getWhatsAppMessageById(@PathVariable int index) {
        // Implémentez cette méthode si vous stockez les messages dans une base de données.
        // Pour l'instant, retournez une réponse non implémentée.
        return ResponseEntity.notFound().build();
    }

    /**
     * Supprime un message WhatsApp par son ID.
     *
     * @param index L'ID du message à supprimer.
     * @return Une réponse vide avec un statut 204 (No Content).
     */
    @DeleteMapping("/{index}")
    public ResponseEntity<Void> deleteWhatsAppMessage(@PathVariable int index) {
        // Implémentez cette méthode si vous stockez les messages dans une base de données.
        // Pour l'instant, retournez une réponse non implémentée.
        return ResponseEntity.noContent().build();
    }
}