package inc.yowyob.service.notification.application.dto.smtp;

import com.fasterxml.jackson.annotation.JsonProperty;
import inc.yowyob.service.notification.application.enums.Encryption;
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
public class SmtpSettingDto extends EntityDto {

    @JsonProperty(value = "organization_id")
    protected UUID organizationId;

    @JsonProperty(value = "id")
    protected UUID id;

    @JsonProperty(value = "host")
    protected String host;

    @JsonProperty(value = "port")
    protected Integer port;

    @JsonProperty(value = "encryption")
    protected Encryption encryption;

    @JsonProperty(value = "username")
    protected String username;

    @JsonProperty(value = "password")
    protected String password;

    @JsonProperty(value = "sender_email")
    protected String senderEmail;

    @JsonProperty(value = "sender_name")
    protected String senderName;

}
