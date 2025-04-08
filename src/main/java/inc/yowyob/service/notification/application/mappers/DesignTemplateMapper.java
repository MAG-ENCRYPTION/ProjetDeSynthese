package inc.yowyob.service.notification.application.mappers;

import inc.yowyob.service.notification.application.dto.designtemplate.CreateDesignTemplateRequest;
import inc.yowyob.service.notification.application.dto.designtemplate.DesignTemplateDto;
import inc.yowyob.service.notification.application.dto.designtemplate.UpdateDesignTemplateRequest;
import inc.yowyob.service.notification.infrastructure.persistence.entities.DesignTemplate;
import inc.yowyob.starter.core.application.models.BaseMapper;
import org.mapstruct.*;

import java.util.UUID;

/**
 * @author douglas
 */
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface DesignTemplateMapper extends BaseMapper<DesignTemplate, DesignTemplateDto> {

    @Override
    @Mapping(source = "key.organizationId", target = "organizationId")
    @Mapping(source = "key.id", target = "id")
    public DesignTemplateDto toDto(DesignTemplate designTemplate);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(source = "organizationId", target = "key.organizationId")
    public DesignTemplate toEntity(UUID organizationId, CreateDesignTemplateRequest request);


    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    public DesignTemplate update(@MappingTarget DesignTemplate designTemplate, UpdateDesignTemplateRequest request);

}

