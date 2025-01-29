package ProjetDeSyntheseNotification.notification.dto;

public class SimpleDTO extends PushNotificationDTO {
    public SimpleDTO() {
    }

    public SimpleDTO(Long id, String message, String type, int priority, String senderName, String receiverName, String subject) {
        super(id, message, type, priority, senderName, receiverName, subject);
    }
}
