package inc.yowyob.service.notification.application.dto.reminder;

import com.fasterxml.jackson.annotation.JsonProperty;
import inc.yowyob.service.notification.application.enums.ReminderType;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.UUID;


@NoArgsConstructor
@Data
@ToString
public class ReminderRequest {

    @JsonProperty(value = "id")
    protected UUID id;

    @NotNull(message = "The design template is required")
    @JsonProperty(value = "design_template_id")
    protected UUID designTemplateId;

    @NotNull(message = "The reminder type is required")
    @JsonProperty(value = "type")
    protected ReminderType type;

    @NotNull(message = "The reminder value is required")
    @Min(value = 0, message = "The reminder value must be positive")
    @JsonProperty(value = "value")
    protected Integer value;

}
