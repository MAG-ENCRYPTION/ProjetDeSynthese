package inc.yowyob.service.notification.application.dto.twilio;

import com.fasterxml.jackson.annotation.JsonProperty;
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
public class UpdateTwilioSettingRequest {

    @NotBlank(message = "The account id is required")
    @JsonProperty(value = "account_id")
    protected String accountId;

    @NotBlank(message = "The authentication token is required")
    @JsonProperty(value = "auth_token")
    protected String authToken;

    @NotBlank(message = "The sender phone number is required")
    @JsonProperty(value = "sender_phone_number")
    protected String senderPhoneNumber;

}
