package inc.yowyob.service.notification.infrastructure.persistence.listeners;

import inc.yowyob.scylladb.utils.PrimaryKeyExtractor;
import inc.yowyob.service.notification.application.mappers.NotificationMapper;
import inc.yowyob.service.notification.infrastructure.persistence.entities.Notification;
import inc.yowyob.service.notification.infrastructure.persistence.entities.SmsNotification;
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
public class SmsNotificationEventListener extends AbstractCassandraEventListener<SmsNotification> {

    private final NotificationRepository notificationRepository;

    private final NotificationMapper notificationMapper;


    @Override
    public void onAfterSave(AfterSaveEvent<SmsNotification> event) {
        SmsNotification smsNotification = event.getSource();
        this.saveNotification(smsNotification).subscribe();
    }

    @Override
    public void onAfterDelete(AfterDeleteEvent<SmsNotification> event) {

        NotificationKey notificationKey = PrimaryKeyExtractor.extractPrimaryKey(event.getSource(), NotificationKey.class);

        if (notificationKey != null) {
            this.removeNotification(notificationKey).subscribe();
        }
    }

    private Mono<Notification> saveNotification(SmsNotification smsNotification) {
        return notificationRepository.findById(smsNotification.getKey())
                .switchIfEmpty(notificationRepository.insert(smsNotification))
                .flatMap(notification -> notificationRepository.save(notificationMapper.update(notification, smsNotification)));
    }

    private Mono<Void> removeNotification(NotificationKey notificationKey) {
        return notificationRepository.findById(notificationKey).flatMap(notificationRepository::delete);
    }

}
