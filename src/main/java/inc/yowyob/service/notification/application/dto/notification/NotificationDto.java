package inc.yowyob.service.notification.application.dto.notification;

import com.fasterxml.jackson.annotation.JsonProperty;
import inc.yowyob.service.notification.application.enums.NotificationType;
import inc.yowyob.service.notification.application.enums.PriorityLevel;
import inc.yowyob.utils.dto.EntityDto;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

@Data
@EqualsAndHashCode(callSuper = false)
public class NotificationDto extends EntityDto {

    @JsonProperty("template_id")
    protected UUID templateId;

    @JsonProperty("id")
    protected UUID id;

    @JsonProperty("subject")
    private String subject;

    @JsonProperty("body")
    protected String body;

    @JsonProperty("recipients")
    private Set<String> recipients;

    @JsonProperty("type")
    protected NotificationType type;

    @JsonProperty("priority")
    protected PriorityLevel priority;

    @JsonProperty("metadata")
    protected Map<String, String> metadata = new HashMap<>();

}