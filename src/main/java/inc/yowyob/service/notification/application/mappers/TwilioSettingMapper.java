package inc.yowyob.service.notification.application.mappers;

import inc.yowyob.service.notification.application.dto.smtp.SmtpSettingDto;
import inc.yowyob.service.notification.application.dto.twilio.CreateTwilioSettingRequest;
import inc.yowyob.service.notification.application.dto.twilio.TwilioSettingDto;
import inc.yowyob.service.notification.application.dto.twilio.UpdateTwilioSettingRequest;
import inc.yowyob.service.notification.infrastructure.persistence.entities.SmtpSetting;
import inc.yowyob.service.notification.infrastructure.persistence.entities.TwilioSetting;
import inc.yowyob.starter.core.application.models.BaseMapper;
import org.mapstruct.*;

import java.util.UUID;

/**
 * @author douglas
 */
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface TwilioSettingMapper extends BaseMapper<TwilioSetting, TwilioSettingDto> {

    @Override
    @Mapping(source = "key.organizationId", target = "organizationId")
    @Mapping(source = "key.id", target = "id")
    public TwilioSettingDto toDto(TwilioSetting twilioSetting);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(source = "organizationId", target = "key.organizationId")
    public TwilioSetting toEntity(UUID organizationId, CreateTwilioSettingRequest request);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    public TwilioSetting update(@MappingTarget TwilioSetting entity, UpdateTwilioSettingRequest request);

}

