package inc.yowyob.service.notification.application.dto.service;

import com.fasterxml.jackson.annotation.JsonProperty;
import inc.yowyob.service.notification.application.enums.NotificationType;
import inc.yowyob.service.notification.application.enums.PriorityLevel;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.UUID;

@Data
public class UpdateServiceNotificationRequest {

    @NotBlank(message = "The notification name is required")
    @Size(max = 30, message = "The notification name must be at least 30 characters ")
    @JsonProperty("service_name")
    protected String serviceName;

    @NotBlank(message = "The notification type is required")
    @JsonProperty("type")
    private NotificationType type;

    @JsonProperty("template_id")
    protected UUID template_id;

    @JsonProperty("priority")
    protected PriorityLevel priority = PriorityLevel.LEVEL_7;

}