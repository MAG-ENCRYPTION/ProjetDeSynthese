package inc.yowyob.service.notification.infrastructure.persistence.listeners;

import inc.yowyob.scylladb.utils.PrimaryKeyExtractor;
import inc.yowyob.service.notification.infrastructure.persistence.entities.Notification;
import inc.yowyob.service.notification.infrastructure.persistence.entities.keys.NotificationKey;
import inc.yowyob.service.notification.infrastructure.persistence.repository.EmailNotificationRepository;
import inc.yowyob.service.notification.infrastructure.persistence.repository.PushNotificationRepository;
import inc.yowyob.service.notification.infrastructure.persistence.repository.SmsNotificationRepository;
import inc.yowyob.service.notification.infrastructure.persistence.repository.WhatsappNotificationRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.cassandra.core.mapping.event.AbstractCassandraEventListener;
import org.springframework.data.cassandra.core.mapping.event.AfterDeleteEvent;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

/**
 * @author ETOUGUE
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class NotificationEventListener extends AbstractCassandraEventListener<Notification> {

    private final EmailNotificationRepository emailNotificationRepository;

    private final PushNotificationRepository pushNotificationRepository;

    private final WhatsappNotificationRepository whatsappNotificationRepository;

    private final SmsNotificationRepository smsNotificationRepository;

    @Override
    public void onAfterDelete(AfterDeleteEvent<Notification> event) {

        NotificationKey notificationKey = PrimaryKeyExtractor.extractPrimaryKey(event.getSource(), NotificationKey.class);

        if (notificationKey != null) {
            this.removeEmailNotification(notificationKey).subscribe();
            this.removePushNotification(notificationKey).subscribe();
            this.removeSmsNotification(notificationKey).subscribe();
            this.removeWhatsappNotification(notificationKey).subscribe();
        }
    }

    private Mono<Void> removeEmailNotification(NotificationKey notificationKey) {
        return emailNotificationRepository.findById(notificationKey).flatMap(emailNotificationRepository::delete);
    }

    private Mono<Void> removeSmsNotification(NotificationKey notificationKey) {
        return smsNotificationRepository.findById(notificationKey).flatMap(smsNotificationRepository::delete);
    }

    private Mono<Void> removePushNotification(NotificationKey notificationKey) {
        return pushNotificationRepository.findById(notificationKey).flatMap(pushNotificationRepository::delete);
    }

    private Mono<Void> removeWhatsappNotification(NotificationKey notificationKey) {
        return whatsappNotificationRepository.findById(notificationKey).flatMap(whatsappNotificationRepository::delete);
    }

}
