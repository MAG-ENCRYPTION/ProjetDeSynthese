package inc.yowyob.service.notification.application.mappers;

import inc.yowyob.service.notification.application.dto.reminder.CreateReminderRequest;
import inc.yowyob.service.notification.application.dto.reminder.ReminderDto;
import inc.yowyob.service.notification.application.dto.reminder.ReminderRequest;
import inc.yowyob.service.notification.application.dto.reminder.UpdateReminderRequest;
import inc.yowyob.service.notification.infrastructure.persistence.entities.Reminder;
import inc.yowyob.starter.core.application.models.BaseMapper;
import org.mapstruct.*;

import java.util.UUID;

/**
 * @author douglas
 */
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ReminderMapper extends BaseMapper<Reminder, ReminderDto> {

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(source = "templateId", target = "key.templateId")
    public Reminder toEntity(UUID templateId, CreateReminderRequest request);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    public UpdateReminderRequest toUpdateReminderRequest(ReminderRequest request);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    public CreateReminderRequest toCreateReminderRequest(ReminderRequest request);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    public Reminder update(@MappingTarget Reminder emailReminder, UpdateReminderRequest request);

}

