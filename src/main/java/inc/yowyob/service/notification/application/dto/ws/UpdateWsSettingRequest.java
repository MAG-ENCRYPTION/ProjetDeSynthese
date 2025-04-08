package inc.yowyob.service.notification.application.dto.ws;

import com.fasterxml.jackson.annotation.JsonProperty;
import inc.yowyob.utils.dto.EntityDto;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ToString
public class UpdateWsSettingRequest extends EntityDto {

    @NotBlank(message = "The Topic is required")
    @JsonProperty(value = "topic")
    protected String topic;

    @JsonProperty(value = "webhook_url")
    protected String webhookUrl;

}
