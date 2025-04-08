
package inc.yowyob.service.notification.application.service;

import inc.yowyob.service.notification.application.dto.reminder.CreateReminderRequest;
import inc.yowyob.service.notification.application.dto.reminder.ReminderRequest;
import inc.yowyob.service.notification.application.dto.reminder.UpdateReminderRequest;
import inc.yowyob.service.notification.infrastructure.persistence.entities.Reminder;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.UUID;

/**
 * @author ETOUGUE
 */
public interface ReminderService {
    /**
     * Creates a new EmailReminder.
     *
     * @param templateId The ID of the template
     * @param request the DTO containing data for the new EmailReminder
     * @return the created EmailReminder entity
     */
    public Mono<Reminder> createReminder(UUID templateId, CreateReminderRequest request);

    /**
     * Creates a new EmailReminder.
     *
     * @param templateId The ID of the template
     * @param reminders the DTO containing data for the new EmailReminder
     * @return the created EmailReminder entity
     */
    public Flux<Reminder> saveReminders(UUID templateId, List<ReminderRequest> reminders);

    /**
     * Retrieves an EmailReminder by its ID.
     *
     * @param templateId The ID of the template
     * @param id the ID of the EmailReminder to retrieve
     * @return the found EmailReminder entity, or null if not found
     */
    public Mono<Reminder> getReminderById(UUID templateId, UUID id);

    /**
     * Retrieves all EmailReminders.
     *
     * @param templateId The ID of the template
     * @return a list of all EmailReminder entities
     */
    public Flux<Reminder> getAllReminders(UUID templateId);

    /**
     * Updates an existing EmailReminder.
     *
     * @param templateId The ID of the template
     * @param id      the ID of the EmailReminder to update
     * @param request the DTO containing updated data
     * @return the updated EmailReminder entity
     */
    public Mono<Reminder> updateReminder(UUID templateId, UUID id, UpdateReminderRequest request);

    /**
     * Deletes an EmailReminder by its ID.
     *
     * @param templateId The ID of the template
     * @param id the ID of the EmailReminder to delete
     */
    public Mono<Void> deleteReminder(UUID templateId, UUID id);
}
