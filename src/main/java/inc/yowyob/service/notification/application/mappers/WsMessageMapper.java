package inc.yowyob.service.notification.application.mappers;

import inc.yowyob.service.notification.application.dto.ws.WsMessageDto;
import inc.yowyob.service.notification.application.dto.ws.WsMessageRequest;
import inc.yowyob.service.notification.infrastructure.persistence.entities.WsMessage;
import inc.yowyob.starter.core.application.models.BaseMapper;
import org.mapstruct.*;

/**
 * @author douglas
 */
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface WsMessageMapper extends BaseMapper<WsMessage, WsMessageDto> {

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(source = "systemId", target = "key.systemId")
    public WsMessage toEntity(String systemId, WsMessageRequest request);

}

