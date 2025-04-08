package inc.yowyob.service.notification.application.dto.firebase;

import com.fasterxml.jackson.annotation.JsonProperty;
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
public class CreateFirebaseSettingRequest {

    @JsonProperty(value = "project_id")
    protected String projectId;

    @JsonProperty(value = "private_key")
    protected String privateKey;

    @JsonProperty(value = "client_email")
    protected String clientEmail;

}
