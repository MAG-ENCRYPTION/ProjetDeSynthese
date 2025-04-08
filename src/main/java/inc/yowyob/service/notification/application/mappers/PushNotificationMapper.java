package inc.yowyob.service.notification.application.mappers;

import inc.yowyob.service.notification.application.dto.push.PushNotificationDto;
import inc.yowyob.service.notification.application.dto.push.PushNotificationRequest;
import inc.yowyob.service.notification.infrastructure.persistence.entities.Notification;
import inc.yowyob.service.notification.infrastructure.persistence.entities.PushNotification;
import inc.yowyob.starter.core.application.models.BaseMapper;
import org.mapstruct.*;

import java.util.UUID;

/**
 * @author douglas
 */
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface PushNotificationMapper extends BaseMapper<PushNotification, PushNotificationDto> {

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(source = "organizationId", target = "key.organizationId")
    @Mapping(source = "templateId", target = "key.templateId")
    public PushNotification toEntity(UUID organizationId, UUID templateId, PushNotificationRequest request);

    public Notification toNotification(PushNotification pushNotification);

}

