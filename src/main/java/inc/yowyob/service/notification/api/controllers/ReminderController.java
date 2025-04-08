package inc.yowyob.service.notification.api.controllers;

import inc.yowyob.service.notification.application.dto.reminder.CreateReminderRequest;
import inc.yowyob.service.notification.application.dto.reminder.ReminderDto;
import inc.yowyob.service.notification.application.dto.reminder.UpdateReminderRequest;
import inc.yowyob.service.notification.application.mappers.ReminderMapper;
import inc.yowyob.service.notification.application.service.ReminderService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

/**
 * @author ETOUGUE
 */
@RestController
@RequestMapping("/templates/{template_id}/email-reminders")
@Validated
@RequiredArgsConstructor
public class ReminderController {

    private final ReminderService reminderService;

    private final ReminderMapper reminderMapper;

    /**
     * Creates a new EmailReminder.
     *
     * @param request the DTO containing data for the new
     *                EmailReminder
     * @return a ResponseEntity with the created EmailReminderDto
     */
    @PostMapping
    public Mono<ReminderDto> createReminder(@PathVariable("template_id") UUID templateId, @Valid @RequestBody CreateReminderRequest request) {
        return reminderService.createReminder(templateId, request).map(reminderMapper::toDto);
    }

    /**
     * Retrieves an EmailReminder by its ID.
     *
     * @param id the ID of the EmailReminder to retrieve
     * @return a ResponseEntity with the EmailReminderDto if found, or a not
     * found status
     */
    @GetMapping("/{email_reminder_id}")
    public Mono<ReminderDto> getReminderById(@PathVariable("template_id") UUID templateId, @PathVariable("email_reminder_id") UUID id) {
        return reminderService.getReminderById(templateId, id).map(reminderMapper::toDto);
    }

    /**
     * Retrieves all EmailReminders.
     *
     * @return a ResponseEntity with a list of EmailReminderDto
     */
    @GetMapping
    public Flux<ReminderDto> getAllReminders(@PathVariable("template_id") UUID templateId) {
        return reminderService.getAllReminders(templateId).map(reminderMapper::toDto);
    }

    /**
     * Updates an existing EmailReminder.
     *
     * @param id      the ID of the EmailReminder to update
     * @param request the DTO containing updated values
     * @return a ResponseEntity with the updated EmailReminderDto
     */
    @PutMapping("/{email_reminder_id}")
    public Mono<ReminderDto> updateReminder(@PathVariable("template_id") UUID templateId, @PathVariable("email_reminder_id") UUID id, @Valid @RequestBody UpdateReminderRequest request) {
        return reminderService.updateReminder(templateId, id, request).map(reminderMapper::toDto);
    }

    /**
     * Deletes an EmailReminder by its ID.
     *
     * @param id the ID of the EmailReminder to delete
     * @return a process status
     */
    @DeleteMapping("/{email_reminder_id}")
    public Mono<Void> deleteReminder(@PathVariable("template_id") UUID templateId, @PathVariable("email_reminder_id") UUID id) {
        return reminderService.deleteReminder(templateId, id);
    }
}
