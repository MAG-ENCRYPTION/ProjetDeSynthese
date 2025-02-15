package inc.yowyob.service.notification.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

import inc.yowyob.service.notification.dto.SmsNotificationDTO;
import inc.yowyob.service.notification.model.SmsNotification;
import inc.yowyob.service.notification.repository.SmsNotificationRepository;

import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

@Service
public class SmsService {

    private static final Logger logger = LoggerFactory.getLogger(SmsService.class);

    private final SmsNotificationRepository smsNotificationRepository;
    private String accountSid;
    private String authToken;
    private String fromNumber;

    @Autowired
    public SmsService(SmsNotificationRepository smsNotificationRepository) {
        this.smsNotificationRepository = smsNotificationRepository;
        loadCredentialsFromJson();
        Twilio.init(accountSid, authToken);
    }

    private void loadCredentialsFromJson() {
        ObjectMapper objectMapper = new ObjectMapper();
        try (InputStream inputStream = getClass().getClassLoader().getResourceAsStream("config.json")) {
            if (inputStream == null) {
                throw new RuntimeException("Config file not found in resources folder");
            }
            JsonNode rootNode = objectMapper.readTree(inputStream);
            JsonNode smsNode = rootNode.path("code").path("sms");
            accountSid = smsNode.path("account-sid").asText();
            authToken = smsNode.path("auth-token").asText();
            fromNumber = smsNode.path("from-number").asText();

            logger.info("SMS credentials loaded successfully from JSON.");
        } catch (IOException e) {
            logger.error("Failed to load credentials from config.json", e);
            throw new RuntimeException("Failed to load credentials from config.json", e);
        }
    }

    public SmsNotification createSmsNotification(SmsNotificationDTO smsDTO) {
        SmsNotification smsNotification = new SmsNotification();
        smsNotification.setNotificationId(UUID.randomUUID());
        smsNotification.setNumber(smsDTO.getNumber());
        smsNotification.setMessage(smsDTO.getMessage());
        smsNotification.setType(smsDTO.getType());
        smsNotification.setPriority(smsDTO.getPriority());

        try {
            sendSms(smsNotification);
            logger.info("SMS sent successfully to {}", smsNotification.getNumber());
        } catch (Exception e) {
            logger.error("Failed to send SMS to {}", smsNotification.getNumber(), e);
        }

        return smsNotificationRepository.save(smsNotification);
    }

    private void sendSms(SmsNotification smsNotification) {
        Message message = Message.creator(
                new PhoneNumber(smsNotification.getNumber()),
                new PhoneNumber(fromNumber),
                smsNotification.getMessage()
        ).create();

        logger.info("Message sent with SID: {}", message.getSid());
    }

    public void receive(MultiValueMap<String, String> map) {
        // TODO: Implement SMS receiving logic here
    }
}
