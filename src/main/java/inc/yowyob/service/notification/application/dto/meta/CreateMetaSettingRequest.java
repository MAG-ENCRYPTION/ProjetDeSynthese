package inc.yowyob.service.notification.application.dto.meta;

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
public class CreateMetaSettingRequest {

    @NotBlank(message = "The phone number id is required")
    @JsonProperty(value = "phone_number_id")
    protected String phoneNumberId;

    @NotBlank(message = "The access token is required")
    @JsonProperty(value = "access_token")
    protected String accessToken;

    @JsonProperty(value = "sender_phone_number")
    protected String senderPhoneNumber;

}
