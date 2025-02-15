package inc.notification.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import inc.notification.dto.ResponseMessage;

@Service
public class pushService1 {

    private final SimpMessagingTemplate messagingTemplate;
    private final NotificationService1 notificationService1;

    @Autowired
    public pushService1(SimpMessagingTemplate messagingTemplate, NotificationService1 notificationService1) {
        this.messagingTemplate = messagingTemplate;
        this.notificationService1 = notificationService1;
    }

    public void notifyFrontend(final String message) {
        ResponseMessage response = new ResponseMessage(message);
        notificationService1.sendGlobalNotification();

        messagingTemplate.convertAndSend("/topic/messages", response);
    }

    public void notifyUser(final String id, final String message) {
        ResponseMessage response = new ResponseMessage(message);

        notificationService1.sendPrivateNotification(id, response);
        
    }
}