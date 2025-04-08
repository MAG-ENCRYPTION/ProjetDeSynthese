package inc.yowyob.service.notification.application.dto.firebase;

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
public class FirebaseSettingDto extends EntityDto {

    @JsonProperty(value = "organization_id")
    protected UUID organizationId;

    @JsonProperty(value = "id")
    protected UUID id;

    @JsonProperty(value = "project_id")
    protected String projectId;

    @JsonProperty(value = "private_key")
    protected String privateKey;

    @JsonProperty(value = "client_email")
    protected String clientEmail;

    @JsonProperty(value = "is_default")
    protected Boolean isDefault;

}
