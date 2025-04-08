package inc.yowyob.service.notification.application.dto.twilio;

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
public class TwilioSettingDto extends EntityDto {

    @JsonProperty(value = "organization_id")
    protected UUID organizationId;

    @JsonProperty(value = "id")
    protected UUID id;

    @JsonProperty(value = "account_id")
    protected String accountId;

    @JsonProperty(value = "auth_token")
    protected String authToken;

    @JsonProperty(value = "sender_phone_number")
    protected String senderPhoneNumber;

    @JsonProperty(value = "is_default")
    protected Boolean isDefault;

}
