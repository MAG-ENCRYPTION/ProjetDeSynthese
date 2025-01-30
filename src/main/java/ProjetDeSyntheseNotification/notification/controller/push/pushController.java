package ProjetDeSyntheseNotification.notification.controller.push;

import ProjetDeSyntheseNotification.notification.dto.Message;
import ProjetDeSyntheseNotification.notification.service.pushService1;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class pushController {

    @Autowired
    private pushService1 service;

    // Méthode pour envoyer un message global
    @PostMapping("/send-message")
    public ResponseEntity<Message> sendMessage(@RequestBody final Message message) {
        // Appel du service pour notifier le frontend
        service.notifyFrontend(message.getMessageContent());

        // Préparation d'un objet Message avec un contenu de retour
        Message responseMessage = new Message();
        responseMessage.setMessageContent("Message reçu et traité : " + message.getMessageContent());
        responseMessage.setStatus("success");

        // Créer un objet ResponseData avec un ID de requête unique
        Message.ResponseData responseData = new Message.ResponseData("req-" + System.currentTimeMillis());
        responseMessage.setData(responseData);

        // Retourner le message dans le format attendu
        return ResponseEntity.ok(responseMessage);
    }

    // Méthode pour envoyer un message privé à un utilisateur spécifique
    @PostMapping("/send-private-message/{id}")
    public ResponseEntity<Message> sendPrivateMessage(@PathVariable final String id,
                                                      @RequestBody final Message message) {
        // Appel du service pour notifier un utilisateur spécifique
        service.notifyUser(id, message.getMessageContent());

        // Préparation d'un objet Message avec un contenu de retour
        Message responseMessage = new Message();
        responseMessage.setMessageContent("Message privé envoyé à " + id + ": " + message.getMessageContent());
        responseMessage.setStatus("success");

        // Créer un objet ResponseData avec un ID de requête unique
        Message.ResponseData responseData = new Message.ResponseData("req-" + System.currentTimeMillis());
        responseMessage.setData(responseData);

        // Retourner le message dans le format attendu
        return ResponseEntity.ok(responseMessage);
    }
}
