package inc.yowyob.service.notification.application.mappers;

import inc.yowyob.service.notification.application.dto.sms.SmsNotificationDto;
import inc.yowyob.service.notification.application.dto.sms.SmsNotificationRequest;
import inc.yowyob.service.notification.infrastructure.persistence.entities.SmsNotification;
import inc.yowyob.starter.core.application.models.BaseMapper;
import org.mapstruct.*;

import java.util.UUID;

/**
 * @author douglas
 */
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface SmsNotificationMapper extends BaseMapper<SmsNotification, SmsNotificationDto> {

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(source = "organizationId", target = "key.organizationId")
    @Mapping(source = "templateId", target = "key.templateId")
    public SmsNotification toEntity(UUID organizationId, UUID templateId, SmsNotificationRequest request);

}

