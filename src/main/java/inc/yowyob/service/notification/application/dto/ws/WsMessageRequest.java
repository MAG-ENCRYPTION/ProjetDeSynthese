package inc.yowyob.service.notification.application.dto.ws;

import com.fasterxml.jackson.annotation.JsonProperty;
import inc.yowyob.service.notification.infrastructure.config.DatabaseSchema;
import inc.yowyob.utils.dto.EntityDto;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.cassandra.core.mapping.Table;

import java.util.Set;

@Data
@EqualsAndHashCode(callSuper = true)
@Table(DatabaseSchema.WS_MESSAGE_TABLE)
public class WsMessageRequest extends EntityDto {

    @NotEmpty(message = "At least one topic is required")
    @JsonProperty("topics")
    private Set<String> topics;

    @JsonProperty("message")
    private String message;
}
