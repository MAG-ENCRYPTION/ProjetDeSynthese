package inc.yowyob.service.notification.application.service;

import inc.yowyob.service.notification.application.dto.notification.NotificationRequest;
import inc.yowyob.service.notification.infrastructure.persistence.entities.Notification;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

public interface NotificationService {

    /**
     * Creates a new Notification.
     *
     * @param templateId the ID of the template
     * @param request    the Notification object to be created
     * @return the created Notification object
     */
    public Mono<Notification> createNotification(UUID organizationId, UUID templateId, NotificationRequest request);

    /**
     * Retrieves a Notification by its ID.
     *
     * @param templateId the ID of the template
     * @param id         the ID of the Notification to retrieve
     * @return the found Notification object, or null if not found
     */
    public Mono<Notification> getNotificationById(UUID organizationId, UUID templateId, UUID id);

    /**
     * Retrieves all Notifications.
     *
     * @param templateId the ID of the template
     * @return a list of all Notification objects
     */
    public Flux<Notification> getAllNotifications(UUID organizationId, UUID templateId);


    /**
     * Retrieves all Notifications.
     *
     * @return a list of all Notification objects
     */
    public Flux<Notification> getAllNotifications(UUID organizationId);

    /**
     * Deletes a Notification by its ID.
     *
     * @param templateId the ID of the template
     * @param id         the ID of the Notification to delete
     */
    public Mono<Void> deleteNotification(UUID organizationId, UUID templateId, UUID id);
}
