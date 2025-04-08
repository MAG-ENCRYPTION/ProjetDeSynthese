package inc.yowyob.service.notification.application.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Encryption {
    SMTP("smtp"),
    TLS("tls");

    private final String value;

    public static Encryption fromValue(String value) {
        for (Encryption encryption : values()) {
            if (encryption.getValue().equals(value)) {
                return encryption;
            }
        }
        throw new IllegalArgumentException("Invalid encryption method: " + value);
    }
}
