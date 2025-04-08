package inc.yowyob.service.notification.application.dto.template;

import com.fasterxml.jackson.annotation.JsonProperty;
import inc.yowyob.utils.dto.EntityDto;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.Set;
import java.util.UUID;

@Data
@EqualsAndHashCode(callSuper = false)
@ToString
public class TemplateDto extends EntityDto {

    @JsonProperty(value = "organization_id")
    protected UUID organizationId;

    @JsonProperty(value = "id")
    protected UUID id;

    @JsonProperty(value = "setting_id")
    protected UUID settingId;

    @JsonProperty(value = "design_template_id")
    protected UUID designTemplateId;

    @JsonProperty(value = "title")
    protected String title;

    @JsonProperty(value = "cc")
    protected Set<String> cc;

    @JsonProperty(value = "is_default")
    protected Boolean isDefault;

}
