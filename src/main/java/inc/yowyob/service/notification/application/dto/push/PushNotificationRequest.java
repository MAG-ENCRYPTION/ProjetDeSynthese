package inc.yowyob.service.notification.application.dto.push;

import com.fasterxml.jackson.annotation.JsonProperty;
import inc.yowyob.service.notification.application.dto.notification.NotificationDto;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Set;

@Data
@EqualsAndHashCode(callSuper = true)
public class PushNotificationRequest extends NotificationDto {
    @JsonProperty("tokens")
    private Set<String> recipients;
}
