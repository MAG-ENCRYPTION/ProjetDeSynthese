package inc.yowyob.service.notification.application.dto.email;

import com.fasterxml.jackson.annotation.JsonProperty;
import inc.yowyob.service.notification.application.dto.notification.NotificationDto;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Set;
import java.util.UUID;

@Data
@EqualsAndHashCode(callSuper = false)
public class EmailNotificationDto extends NotificationDto {

    @JsonProperty("template_id")
    private UUID templateId;
    
    @JsonProperty("cc")
    private Set<String> cc;

    @JsonProperty("bcc")
    private Set<String> bcc;

    @JsonProperty("sender_name")
    private String senderName;

    @JsonProperty("sender_email")
    private String senderEmail;

}

