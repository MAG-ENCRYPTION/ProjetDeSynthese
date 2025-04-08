package inc.yowyob.service.notification.application.mappers;

import inc.yowyob.service.notification.application.dto.twilio.TwilioSettingDto;
import inc.yowyob.service.notification.application.dto.ws.CreateWsSettingRequest;
import inc.yowyob.service.notification.application.dto.ws.UpdateWsSettingRequest;
import inc.yowyob.service.notification.application.dto.ws.WsSettingDto;
import inc.yowyob.service.notification.infrastructure.persistence.entities.TwilioSetting;
import inc.yowyob.service.notification.infrastructure.persistence.entities.WsSetting;
import inc.yowyob.starter.core.application.models.BaseMapper;
import org.mapstruct.*;

import java.util.UUID;

/**
 * @author douglas
 */
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface WsSettingMapper extends BaseMapper<WsSetting, WsSettingDto> {

    @Override
    @Mapping(source = "key.organizationId", target = "organizationId")
    @Mapping(source = "key.systemId", target = "systemId")
    public WsSettingDto toDto(WsSetting wsSetting);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(source = "organizationId", target = "key.organizationId")
    @Mapping(source = "request.systemId", target = "key.systemId")
    public WsSetting toEntity(UUID organizationId, CreateWsSettingRequest request);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    public WsSetting update(@MappingTarget WsSetting entity, UpdateWsSettingRequest request);

}

