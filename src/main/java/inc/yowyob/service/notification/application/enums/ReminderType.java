package inc.yowyob.service.notification.application.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ReminderType {
    DUE_IN("DUE_IN"),
    OVERDUE_IN("OVERDUE_IN");

    private final String value;

    public boolean is(ReminderType reminderType) {
        return reminderType != null && reminderType.value.equals(this.getValue());
    }

    public static ReminderType fromValue(String value) {
        for (ReminderType reminderType : values()) {
            if (reminderType.getValue().equals(value)) {
                return reminderType;
            }
        }
        throw new IllegalArgumentException("Invalid reminder type: " + value);
    }
}
