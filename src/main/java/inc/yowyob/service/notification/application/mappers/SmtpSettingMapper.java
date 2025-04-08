package inc.yowyob.service.notification.application.mappers;

import inc.yowyob.service.notification.application.dto.sms.SmsNotificationDto;
import inc.yowyob.service.notification.application.dto.smtp.CreateSmtpSettingRequest;
import inc.yowyob.service.notification.application.dto.smtp.SmtpSettingDto;
import inc.yowyob.service.notification.application.dto.smtp.UpdateSmtpSettingRequest;
import inc.yowyob.service.notification.infrastructure.persistence.entities.SmsNotification;
import inc.yowyob.service.notification.infrastructure.persistence.entities.SmtpSetting;
import inc.yowyob.starter.core.application.models.BaseMapper;
import org.mapstruct.*;

import java.util.UUID;

/**
 * @author douglas
 */
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface SmtpSettingMapper extends BaseMapper<SmtpSetting, SmtpSettingDto> {

    @Override
    @Mapping(source = "key.organizationId", target = "organizationId")
    @Mapping(source = "key.id", target = "id")
    public SmtpSettingDto toDto(SmtpSetting smtpSetting);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(source = "organizationId", target = "key.organizationId")
    public SmtpSetting toEntity(UUID organizationId, CreateSmtpSettingRequest request);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    public SmtpSetting update(@MappingTarget SmtpSetting entity, UpdateSmtpSettingRequest request);

}

