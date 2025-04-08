package inc.yowyob.service.notification.application.dto.meta;

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
public class MetaSettingDto extends EntityDto {

    @JsonProperty(value = "organization_id")
    protected UUID organizationId;

    @JsonProperty(value = "id")
    protected UUID id;

    @JsonProperty(value = "phone_number_id")
    protected String phoneNumberId;

    @JsonProperty(value = "access_token")
    protected String accessToken;

    @JsonProperty(value = "sender_phone_number")
    protected String senderPhoneNumber;

    @JsonProperty(value = "is_default")
    protected Boolean isDefault;

}
