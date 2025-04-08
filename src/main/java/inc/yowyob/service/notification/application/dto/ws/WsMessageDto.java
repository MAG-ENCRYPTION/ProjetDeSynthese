package inc.yowyob.service.notification.application.dto.ws;

import com.fasterxml.jackson.annotation.JsonProperty;
import inc.yowyob.service.notification.infrastructure.config.DatabaseSchema;
import inc.yowyob.utils.dto.EntityDto;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.cassandra.core.mapping.Table;

import java.util.UUID;

@Data
@EqualsAndHashCode(callSuper = true)
@Table(DatabaseSchema.WS_MESSAGE_TABLE)
public class WsMessageDto extends EntityDto {

    @JsonProperty("system_id")
    private String systemId;

    @JsonProperty("id")
    private UUID id;

    @JsonProperty("topic")
    private String topic;

    @JsonProperty("message")
    private String message;
}
