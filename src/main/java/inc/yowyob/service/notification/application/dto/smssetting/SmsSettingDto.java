package inc.yowyob.service.notification.application.dto.smssetting;

import com.fasterxml.jackson.annotation.JsonProperty;
import inc.yowyob.service.notification.application.enums.ProviderType;
import inc.yowyob.utils.dto.EntityDto;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.UUID;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@ToString

public class SmsSettingDto extends EntityDto {

    @JsonProperty(value = "id")
    protected UUID id;

    @JsonProperty(value = "account_id")
    protected String accountId;

    @JsonProperty(value = "auth_token")
    protected String authToken;

    @JsonProperty(value = "provider_type")
    protected ProviderType providerType;

    @JsonProperty(value = "sender_phone_number")
    protected String senderPhoneNumber;

    @JsonProperty(value = "is_default")
    protected Boolean isDefault;

}
