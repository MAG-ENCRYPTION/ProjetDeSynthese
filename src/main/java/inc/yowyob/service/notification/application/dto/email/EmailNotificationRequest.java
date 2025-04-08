package inc.yowyob.service.notification.application.dto.email;

import com.fasterxml.jackson.annotation.JsonProperty;
import inc.yowyob.service.notification.application.dto.notification.NotificationRequest;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.HashSet;
import java.util.Set;

@Data
@EqualsAndHashCode(callSuper = true)
public class EmailNotificationRequest extends NotificationRequest {

    @JsonProperty("cc")
    private Set<String> cc = new HashSet<>();

    @JsonProperty("bcc")
    private Set<String> bcc = new HashSet<>();

    @JsonProperty("sender_name")
    private String senderName;

    @JsonProperty("sender_email")
    private String senderEmail;

}

