package inc.yowyob.service.notification.infrastructure.persistence.listeners;

import inc.yowyob.scylladb.utils.PrimaryKeyExtractor;
import inc.yowyob.service.notification.application.mappers.NotificationMapper;
import inc.yowyob.service.notification.infrastructure.persistence.entities.EmailNotification;
import inc.yowyob.service.notification.infrastructure.persistence.entities.Notification;
import inc.yowyob.service.notification.infrastructure.persistence.entities.keys.NotificationKey;
import inc.yowyob.service.notification.infrastructure.persistence.repository.NotificationRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.cassandra.core.mapping.event.AbstractCassandraEventListener;
import org.springframework.data.cassandra.core.mapping.event.AfterDeleteEvent;
import org.springframework.data.cassandra.core.mapping.event.AfterSaveEvent;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

/**
 * @author ETOUGUE
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class EmailNotificationEventListener extends AbstractCassandraEventListener<EmailNotification> {

    private final NotificationRepository notificationRepository;

    private final NotificationMapper notificationMapper;


    @Override
    public void onAfterSave(AfterSaveEvent<EmailNotification> event) {
        EmailNotification emailNotification = event.getSource();
        this.saveNotification(emailNotification).subscribe();
    }

    @Override
    public void onAfterDelete(AfterDeleteEvent<EmailNotification> event) {

        NotificationKey notificationKey = PrimaryKeyExtractor.extractPrimaryKey(event.getSource(), NotificationKey.class);

        if (notificationKey != null) {
            this.removeNotification(notificationKey).subscribe();
        }
    }

    private Mono<Notification> saveNotification(EmailNotification emailNotification) {
        return notificationRepository.findById(emailNotification.getKey())
                .switchIfEmpty(notificationRepository.insert(emailNotification))
                .flatMap(notification -> notificationRepository.save(notificationMapper.update(notification, emailNotification)));
    }

    private Mono<Void> removeNotification(NotificationKey notificationKey) {
        return notificationRepository.findById(notificationKey).flatMap(notificationRepository::delete);
    }

}
