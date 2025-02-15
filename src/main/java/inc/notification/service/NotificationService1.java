package inc.notification.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import inc.notification.dto.ResponseMessage;

@Service
public class NotificationService1 {
    private final SimpMessagingTemplate messagingTemplate;

    @Autowired
    public NotificationService1(SimpMessagingTemplate messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }

    public void sendGlobalNotification() {
        ResponseMessage message = new ResponseMessage("Global Notification");

        messagingTemplate.convertAndSend("/topic/global-notifications", message);
    }

    public void sendPrivateNotification(final String userId, ResponseMessage message) {
        
        messagingTemplate.convertAndSendToUser(userId,"/topic/private-messages", message);
        
    }
}