package inc.yowyob.service.notification.service;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

import inc.yowyob.service.notification.dto.WhatsAppNotificationDTO;
import inc.yowyob.service.notification.model.WhatsAppNotification;
import inc.yowyob.service.notification.repository.WhatsAppNotificationRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

@Service
public class WhatsAppNotificationService {

    private static final Logger logger = LoggerFactory.getLogger(WhatsAppNotificationService.class);

    private String accountSid;
    private String authToken;
    private String whatsappNumber;

    @Autowired
    private WhatsAppNotificationRepository repository;

    public WhatsAppNotificationService() {
        loadCredentialsFromJson();  // Charger les tokens depuis le fichier JSON
        Twilio.init(accountSid, authToken);
    }

    private void loadCredentialsFromJson() {
        ObjectMapper objectMapper = new ObjectMapper();
        try (InputStream inputStream = getClass().getClassLoader().getResourceAsStream("config.json")) {
            if (inputStream == null) {
                throw new RuntimeException("Config file not found in resources folder");
            }
            JsonNode rootNode = objectMapper.readTree(inputStream);
            JsonNode codeNode = rootNode.path("code");

            accountSid = codeNode.path("account-sid").asText();
            authToken = codeNode.path("auth-token").asText();
            whatsappNumber = codeNode.path("whatsapp-number").asText();

            logger.info("WhatsApp credentials loaded successfully from JSON.");
        } catch (IOException e) {
            logger.error("Failed to load credentials from config.json", e);
            throw new RuntimeException("Failed to load credentials from config.json", e);
        }
    }

    public void sendWhatsAppMessage(WhatsAppNotificationDTO whatsAppNotificationDTO) {
        try {
            Message message = Message.creator(
                            new PhoneNumber("whatsapp:" + whatsAppNotificationDTO.getTo()), // Destinataire
                            new PhoneNumber(whatsappNumber), // Numéro Twilio WhatsApp
                            whatsAppNotificationDTO.getMessage()) // Message
                    .create();

            logger.info("WhatsApp Message SID: {}", message.getSid());

            repository.save(new WhatsAppNotification() {{
                setWhatsappNotificationId(UUID.randomUUID()); // Générer un ID unique
                setTo(whatsAppNotificationDTO.getTo());
                setMessage(whatsAppNotificationDTO.getMessage());
            }});

        } catch (Exception e) {
            logger.error("Error sending WhatsApp message: {}", e.getMessage());
        }
    }
}
