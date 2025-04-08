package inc.yowyob.service.notification.application.mappers;

import inc.yowyob.service.notification.application.dto.meta.CreateMetaSettingRequest;
import inc.yowyob.service.notification.application.dto.meta.MetaSettingDto;
import inc.yowyob.service.notification.application.dto.meta.UpdateMetaSettingRequest;
import inc.yowyob.service.notification.application.dto.smtp.SmtpSettingDto;
import inc.yowyob.service.notification.infrastructure.persistence.entities.MetaSetting;
import inc.yowyob.service.notification.infrastructure.persistence.entities.SmtpSetting;
import inc.yowyob.starter.core.application.models.BaseMapper;
import org.mapstruct.*;

import java.util.UUID;

/**
 * @author douglas
 */
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface MetaSettingMapper extends BaseMapper<MetaSetting, MetaSettingDto> {

    @Override
    @Mapping(source = "key.organizationId", target = "organizationId")
    @Mapping(source = "key.id", target = "id")
    public MetaSettingDto toDto(MetaSetting metaSetting);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(source = "organizationId", target = "key.organizationId")
    public MetaSetting toEntity(UUID organizationId, CreateMetaSettingRequest request);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    public MetaSetting update(@MappingTarget MetaSetting entity, UpdateMetaSettingRequest request);

}

