package inc.yowyob.service.notification.application.dto.smtp;

import com.fasterxml.jackson.annotation.JsonProperty;
import inc.yowyob.service.notification.application.enums.Encryption;
import jakarta.validation.constraints.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@ToString
public class UpdateSmtpSettingRequest {

    @NotBlank(message = "The smtp host is required")
    @JsonProperty(value = "host")
    protected String host;

    @NotNull(message = "The smtp port is required")
    @Min(value = 1, message = "The port must be a valid positive integer")
    @Max(value = 65535, message = "The port must be a valid positive integer")
    @JsonProperty(value = "port")
    protected Integer port;

    @NotNull(message = "The smtp encryption is required")
    @JsonProperty(value = "encryption")
    protected Encryption encryption;

    @NotBlank(message = "The smtp username is required")
    @Email(message = "The smtp username must ne a valid email")
    @Size(max = 100, message = "The smtp username must be at least 100 characters")
    @JsonProperty(value = "username")
    protected String username;

    @JsonProperty(value = "password")
    protected String password;

    @NotBlank(message = "The sender email is required")
    @Email(message = "The sender email must ne a valid email")
    @JsonProperty(value = "sender_email")
    protected String senderEmail;

    @NotBlank(message = "The sender name is required")
    @Size(max = 100, message = "The name must be at least 100 characters")
    @JsonProperty(value = "sender_name")
    protected String senderName;

}
