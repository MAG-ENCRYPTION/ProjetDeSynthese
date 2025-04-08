package inc.yowyob.service.notification.application.mappers;

import inc.yowyob.service.notification.application.dto.firebase.CreateFirebaseSettingRequest;
import inc.yowyob.service.notification.application.dto.firebase.FirebaseSettingDto;
import inc.yowyob.service.notification.application.dto.firebase.UpdateFirebaseSettingRequest;
import inc.yowyob.service.notification.application.dto.smtp.SmtpSettingDto;
import inc.yowyob.service.notification.infrastructure.persistence.entities.FirebaseSetting;
import inc.yowyob.service.notification.infrastructure.persistence.entities.SmtpSetting;
import inc.yowyob.starter.core.application.models.BaseMapper;
import org.mapstruct.*;

import java.util.UUID;

/**
 * @author douglas
 */
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface FirebaseSettingMapper extends BaseMapper<FirebaseSetting, FirebaseSettingDto> {

    @Override
    @Mapping(source = "key.organizationId", target = "organizationId")
    @Mapping(source = "key.id", target = "id")
    public FirebaseSettingDto toDto(FirebaseSetting firebaseSetting);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    public FirebaseSetting toEntity(UUID organizationId, CreateFirebaseSettingRequest request);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    public FirebaseSetting update(@MappingTarget FirebaseSetting entity, UpdateFirebaseSettingRequest request);

}

