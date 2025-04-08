package inc.yowyob.service.notification.application.dto.reminder;

import com.fasterxml.jackson.annotation.JsonProperty;
import inc.yowyob.service.notification.application.enums.ReminderType;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.UUID;


@NoArgsConstructor
@Data
@ToString
public class UpdateReminderRequest {

    @NotBlank(message = "The design template is required")
    @JsonProperty(value = "design_template_id")
    protected UUID designTemplateId;

    @NotBlank(message = "The reminder type is required")
    @JsonProperty(value = "type")
    protected ReminderType type;

    @NotBlank(message = "The reminder value is required")
    @JsonProperty(value = "value")
    protected Integer value;

}
