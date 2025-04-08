package inc.yowyob.service.notification.application.dto.reminder;

import com.fasterxml.jackson.annotation.JsonProperty;
import inc.yowyob.service.notification.application.enums.ReminderType;
import inc.yowyob.utils.dto.EntityDto;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.UUID;


@NoArgsConstructor
@Data
@ToString
@EqualsAndHashCode(callSuper = false)
public class ReminderDto extends EntityDto {

    @JsonProperty(value = "id")
    protected UUID id;

    @JsonProperty(value = "design_template_id")
    protected UUID designTemplateId;

    @JsonProperty(value = "type")
    protected ReminderType type;

    @JsonProperty(value = "value")
    protected Integer value;

    @JsonProperty(value = "resource_id")
    protected UUID resourceId;

    @JsonProperty(value = "resource_type")
    protected String resourceType;
}
