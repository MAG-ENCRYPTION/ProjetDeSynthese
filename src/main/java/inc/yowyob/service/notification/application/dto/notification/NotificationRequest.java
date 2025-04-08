package inc.yowyob.service.notification.application.dto.notification;

import com.fasterxml.jackson.annotation.JsonProperty;
import inc.yowyob.service.notification.application.enums.NotificationType;
import inc.yowyob.service.notification.application.enums.PriorityLevel;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.*;

@Data
@EqualsAndHashCode(callSuper = false)
public class NotificationRequest {

    @JsonProperty("template_id")
    protected UUID templateId;

    @JsonProperty("subject")
    protected String subject;

    @NotEmpty(message = "The recipients are required")
    @JsonProperty("recipients")
    protected Set<String> recipients = new HashSet<>();

    @JsonProperty("priority")
    protected PriorityLevel priority;

    @JsonProperty("metadata")
    protected Map<String, String> metadata = new HashMap<>();

}