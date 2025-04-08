package inc.yowyob.service.notification.application.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum PriorityLevel {
    LEVEL_1(1),
    LEVEL_2(2),
    LEVEL_3(3),
    LEVEL_4(4),
    LEVEL_5(5),
    LEVEL_6(6),
    LEVEL_7(7);

    private final int value;

    public boolean is(PriorityLevel priorityLevel){
        return priorityLevel != null && priorityLevel.value == this.getValue();
    }

    public static PriorityLevel fromValue(int value) {
        for (PriorityLevel level : values()) {
            if (level.getValue() == value) {
                return level;
            }
        }
        throw new IllegalArgumentException("Invalid priority level: " + value);
    }
}

