package inc.yowyob.service.notification.application.dto.ws;

import com.fasterxml.jackson.annotation.JsonProperty;
import inc.yowyob.utils.dto.EntityDto;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.util.UUID;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ToString
public class WsSettingDto extends EntityDto {

    @JsonProperty(value = "organization_id")
    protected UUID organizationId;

    @JsonProperty(value = "system_id")
    protected String systemId;

    @JsonProperty(value = "topic")
    protected String topic;

    @JsonProperty(value = "webhook_url")
    protected String webhookUrl;

    @JsonProperty(value = "is_default")
    protected Boolean isDefault;

}
