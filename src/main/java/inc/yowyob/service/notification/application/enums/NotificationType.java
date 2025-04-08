package inc.yowyob.service.notification.application.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum NotificationType {
    EMAIL("EMAIL"),
    SMS("SMS"),
    PUSH("PUSH"),
    WHATSAPP("WHATSAPP");

    private final String value;

    public boolean is(NotificationType notificationType) {
        return notificationType != null && notificationType.value.equals(this.getValue());
    }

    public static NotificationType fromValue(String value) {
        for (NotificationType notificationType : values()) {
            if (notificationType.getValue().equals(value)) {
                return notificationType;
            }
        }
        throw new IllegalArgumentException("Invalid notification type: " + value);
    }
}
