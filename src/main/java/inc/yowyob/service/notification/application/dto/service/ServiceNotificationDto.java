package inc.yowyob.service.notification.application.dto.service;

import com.fasterxml.jackson.annotation.JsonProperty;
import inc.yowyob.service.notification.application.enums.NotificationType;
import inc.yowyob.service.notification.application.enums.PriorityLevel;
import inc.yowyob.service.notification.infrastructure.config.DatabaseSchema;
import inc.yowyob.utils.dto.EntityDto;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.cassandra.core.mapping.Table;

import java.util.UUID;

@Data
@EqualsAndHashCode(callSuper = false)
public class ServiceNotificationDto extends EntityDto {

    @JsonProperty("id")
    private UUID id;

    @JsonProperty("service_name")
    private String serviceName;

    @JsonProperty("name")
    private String name;

    @JsonProperty("type")
    private NotificationType type;

    @JsonProperty("template_id")
    private UUID template_id;

    @JsonProperty("priority")
    private PriorityLevel priority;

}