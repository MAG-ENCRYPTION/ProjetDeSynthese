package inc.yowyob.service.notification.application.dto.designtemplate;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@ToString
public class CreateDesignTemplateRequest {

    @NotBlank(message = "The template title is required")
    @Size(max = 150, message = "The template title must be at least 150 characters")
    @JsonProperty(value = "title")
    protected String title;

    @NotBlank(message = "The template body is required")
    @Size(max = 5000, message = "The template body must be at least 5000 characters")
    @JsonProperty(value = "html")
    protected String html;

    @NotBlank(message = "The template subject is required")
    @Size(max = 150, message = "The template subject must be at least 150 characters")
    @JsonProperty(value = "subject")
    protected String subject;

}
