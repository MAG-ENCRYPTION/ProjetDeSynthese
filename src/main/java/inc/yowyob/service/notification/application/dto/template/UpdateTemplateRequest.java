package inc.yowyob.service.notification.application.dto.template;

import com.fasterxml.jackson.annotation.JsonProperty;
import inc.yowyob.service.notification.application.dto.reminder.ReminderRequest;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.ToString;

import java.util.*;

@Data
@ToString
public class UpdateTemplateRequest {

    @NotBlank(message = "The smtp setting is required")
    @JsonProperty(value = "setting_id")
    protected UUID settingId;

    @NotBlank(message = "The design template is required")
    @JsonProperty(value = "design_template_id")
    protected UUID designTemplateId;

    @NotBlank(message = "The title is required")
    @Size(max = 255, message = "The title must be at least 255 characters")
    @JsonProperty(value = "title")
    protected String title;

    @JsonProperty(value = "cc")
    protected Set<String> cc = new HashSet<>();

    @JsonProperty(value = "reminders")
    protected List<ReminderRequest> reminders = new ArrayList<>();

}
