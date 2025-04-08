package inc.yowyob.service.notification.application.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ProviderType {
    TWILIO("twilio"),
    DEFAULT("default"),
    FACEBOOK("facebook"),
    FIREBASE("firebase");

    private final String value;

    public boolean is(ProviderType providerType){
        return providerType != null && providerType.value.equals(this.getValue());
    }

    public static ProviderType fromValue(String value) {
        for (ProviderType smsProviderType : values()) {
            if (smsProviderType.getValue().equals(value)) {
                return smsProviderType;
            }
        }
        return DEFAULT;
    }
}
