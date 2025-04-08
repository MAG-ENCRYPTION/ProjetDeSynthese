package inc.yowyob.service.notification.application.mappers;

import inc.yowyob.service.notification.application.dto.designtemplate.DesignTemplateDto;
import inc.yowyob.service.notification.application.dto.template.CreateTemplateRequest;
import inc.yowyob.service.notification.application.dto.template.TemplateDto;
import inc.yowyob.service.notification.application.dto.template.UpdateTemplateRequest;
import inc.yowyob.service.notification.infrastructure.persistence.entities.DesignTemplate;
import inc.yowyob.service.notification.infrastructure.persistence.entities.Template;
import inc.yowyob.starter.core.application.models.BaseMapper;
import org.mapstruct.*;

import java.util.UUID;

/**
 * @author douglas
 */
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface TemplateMapper extends BaseMapper<Template, TemplateDto> {

    @Override
    @Mapping(source = "key.organizationId", target = "organizationId")
    @Mapping(source = "key.id", target = "id")
    public TemplateDto toDto(Template template);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(source = "organizationId", target = "key.organizationId")
    public Template toEntity(UUID organizationId, CreateTemplateRequest request);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    public Template update(@MappingTarget Template emailTemplate, UpdateTemplateRequest source);

}

