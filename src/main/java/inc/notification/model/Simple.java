package inc.notification.model;

import lombok.Data;

@Data
public class Simple extends PushNotification {
    public Simple(String message, String type, int priority, String senderName, String receiverName, String subject) {
        super();
    }

    public Simple() {
    }



}
