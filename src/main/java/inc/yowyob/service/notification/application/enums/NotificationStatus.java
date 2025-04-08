package inc.yowyob.service.notification.application.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum NotificationStatus {
    PENDING("PENDING"),
    SUCCESS("SUCCESS"),
    FAILED("FAILED");

    private final String value;

    public boolean is(NotificationStatus notificationStatus) {
        return notificationStatus != null && notificationStatus.value.equals(this.getValue());
    }

    public static NotificationStatus fromValue(String value) {
        for (NotificationStatus notificationStatus : values()) {
            if (notificationStatus.getValue().equals(value)) {
                return notificationStatus;
            }
        }
        throw new IllegalArgumentException("Invalid notification type: " + value);
    }
}
