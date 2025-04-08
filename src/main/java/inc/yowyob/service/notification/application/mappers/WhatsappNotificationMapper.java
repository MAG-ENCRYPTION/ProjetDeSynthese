package inc.yowyob.service.notification.application.mappers;

import inc.yowyob.service.notification.application.dto.whatsapp.WhatsappNotificationDto;
import inc.yowyob.service.notification.application.dto.whatsapp.WhatsappNotificationRequest;
import inc.yowyob.service.notification.infrastructure.persistence.entities.WhatsappNotification;
import inc.yowyob.starter.core.application.models.BaseMapper;
import org.mapstruct.*;

import java.util.UUID;

/**
 * @author douglas
 */
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface WhatsappNotificationMapper extends BaseMapper<WhatsappNotification, WhatsappNotificationDto> {

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(source = "organizationId", target = "key.organizationId")
    @Mapping(source = "templateId", target = "key.templateId")
    public WhatsappNotification toEntity(UUID organizationId, UUID templateId, WhatsappNotificationRequest request);

}

