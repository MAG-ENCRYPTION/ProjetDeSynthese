package inc.yowyob.service.notification.application.service.impl;

import inc.yowyob.service.notification.application.dto.reminder.CreateReminderRequest;
import inc.yowyob.service.notification.application.dto.reminder.ReminderRequest;
import inc.yowyob.service.notification.application.dto.reminder.UpdateReminderRequest;
import inc.yowyob.service.notification.application.exceptions.ReminderNotFoundException;
import inc.yowyob.service.notification.application.mappers.ReminderMapper;
import inc.yowyob.service.notification.application.service.ReminderService;
import inc.yowyob.service.notification.infrastructure.persistence.entities.Reminder;
import inc.yowyob.service.notification.infrastructure.persistence.entities.keys.ReminderKey;
import inc.yowyob.service.notification.infrastructure.persistence.repository.ReminderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.UUID;

/**
 * @author ETOUGUE
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class ReminderServiceImpl implements ReminderService {

    private final ReminderRepository reminderRepository;

    private final ReminderMapper reminderMapper;

    @Override
    public Mono<Reminder> createReminder(UUID templateId, CreateReminderRequest request) {
        log.info("Creating reminder: {}", request);
        return reminderRepository.insert(reminderMapper.toEntity(templateId, request));
    }

    @Override
    public Flux<Reminder> saveReminders(UUID templateId, List<ReminderRequest> reminders) {
        log.info("Creating reminders for template: {}", templateId);
        return Flux.fromIterable(reminders).flatMap(reminderRequest -> {
            if (reminderRequest.getId() != null) {
                return this.updateReminder(templateId, reminderRequest.getId(), reminderMapper.toUpdateReminderRequest(reminderRequest));
            } else {
                return this.createReminder(templateId, reminderMapper.toCreateReminderRequest(reminderRequest));
            }
        }).collectList().flatMapMany(reminders1 -> {
            return syncReminders(templateId, Flux.fromIterable(reminders1));
        });

    }

    private Flux<Reminder> syncReminders(UUID templateId, Flux<Reminder> reminders) {
        return this.getAllReminders(templateId).filterWhen(
                        reminder -> reminders.any(existing -> existing.getId() != reminder.getId())
                ).flatMap(reminder -> deleteReminder(templateId, reminder.getId()))
                .thenMany(reminders);
    }

    @Override
    public Mono<Reminder> getReminderById(UUID templateId, UUID id) {
        log.info("Fetching EmailReminder with ID: {}", id);
        return reminderRepository.findById(ReminderKey.from(templateId, id))
                .switchIfEmpty(Mono.error(new ReminderNotFoundException(id)));
    }

    @Override
    public Flux<Reminder> getAllReminders(UUID templateId) {
        log.info("Fetching all EmailReminders");
        return reminderRepository.findByKeyTemplateId(templateId);
    }

    @Override
    public Mono<Reminder> updateReminder(UUID templateId, UUID id, UpdateReminderRequest request) {
        log.info("Updating EmailReminder with ID: {}", id);
        return this.getReminderById(templateId, id).flatMap(emailReminder -> {
            return reminderRepository.save(reminderMapper.update(emailReminder, request));
        });
    }

    @Override
    public Mono<Void> deleteReminder(UUID templateId, UUID id) {
        log.info("Deleting EmailReminder with ID: {}", id);
        return this.getReminderById(templateId, id).flatMap(reminderRepository::delete);
    }
}
