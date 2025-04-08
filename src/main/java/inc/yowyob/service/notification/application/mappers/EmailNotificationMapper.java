package inc.yowyob.service.notification.application.mappers;

import inc.yowyob.service.notification.application.dto.email.EmailNotificationDto;
import inc.yowyob.service.notification.application.dto.email.EmailNotificationRequest;
import inc.yowyob.service.notification.infrastructure.persistence.entities.EmailNotification;
import inc.yowyob.starter.core.application.models.BaseMapper;
import org.mapstruct.*;

import java.util.UUID;

/**
 * @author douglas
 */
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface EmailNotificationMapper extends BaseMapper<EmailNotification, EmailNotificationDto> {

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(source = "organizationId", target = "key.organizationId")
    @Mapping(source = "templateId", target = "key.templateId")
    public EmailNotification toEntity(UUID organizationId, UUID templateId, EmailNotificationRequest request);

}

