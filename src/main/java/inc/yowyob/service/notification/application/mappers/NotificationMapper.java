package inc.yowyob.service.notification.application.mappers;

import inc.yowyob.service.notification.application.dto.notification.NotificationDto;
import inc.yowyob.service.notification.application.dto.notification.NotificationRequest;
import inc.yowyob.service.notification.infrastructure.persistence.entities.*;
import inc.yowyob.starter.core.application.models.BaseMapper;
import org.mapstruct.*;

import java.util.UUID;

/**
 * @author douglas
 */
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface NotificationMapper extends BaseMapper<Notification, NotificationDto> {

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(source = "organizationId", target = "key.organizationId")
    @Mapping(source = "templateId", target = "key.templateId")
    public Notification toEntity(UUID organizationId, UUID templateId, NotificationRequest request);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    public Notification update(@MappingTarget Notification entity, PushNotification pushNotification);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    public Notification update(@MappingTarget Notification entity, EmailNotification emailNotification);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    public Notification update(@MappingTarget Notification entity, SmsNotification smsNotification);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    public Notification update(@MappingTarget Notification entity, WhatsappNotification whatsappNotification);

}

