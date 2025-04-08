package inc.yowyob.service.notification.application.dto.smssetting;

import com.fasterxml.jackson.annotation.JsonProperty;
import inc.yowyob.service.notification.application.enums.ProviderType;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@ToString
public class CreateSmsSettingRequest {

    @JsonProperty(value = "account_id")
    protected String accountId;

    @JsonProperty(value = "auth_token")
    protected String authToken;

    @JsonProperty(value = "sender_phone_number")
    protected String senderPhoneNumber;

    @JsonProperty(value = "provider_type")
    protected ProviderType providerType;

}
