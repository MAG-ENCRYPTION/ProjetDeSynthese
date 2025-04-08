package inc.yowyob.service.notification.infrastructure.persistence.callback;

import com.datastax.driver.core.utils.UUIDs;
import com.datastax.oss.driver.api.core.CqlIdentifier;
import inc.yowyob.service.notification.application.enums.NotificationStatus;
import inc.yowyob.service.notification.application.service.MessageBuilderService;
import inc.yowyob.service.notification.infrastructure.persistence.entities.PushNotification;
import inc.yowyob.service.notification.infrastructure.persistence.entities.keys.TemplateKey;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.reactivestreams.Publisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.annotation.Order;
import org.springframework.data.cassandra.core.mapping.event.ReactiveBeforeConvertCallback;
import org.springframework.stereotype.Component;

import java.util.HashSet;

@Order(1)
@Component
@Slf4j
@RequiredArgsConstructor
public class PushNotificationCallback implements ReactiveBeforeConvertCallback<PushNotification> {

    @Autowired
    @Lazy
    private MessageBuilderService messageBuilderService;

    @Override
    public Publisher<PushNotification> onBeforeConvert(PushNotification pushNotification, CqlIdentifier tableName) {

        TemplateKey key = TemplateKey.from(pushNotification.getOrganizationId(), pushNotification.getTemplateId());

        return messageBuilderService.build(pushNotification.getSubject(), pushNotification.getMetadata(), key).map(body -> {
            pushNotification.setBody(body);

            if (pushNotification.getId() == null) {
                pushNotification.setId(UUIDs.timeBased());
            }

            if (pushNotification.getStatus() == null) {
                pushNotification.setStatus(NotificationStatus.PENDING);
            }

            if (pushNotification.getRecipients() == null) {
                pushNotification.setRecipients(new HashSet<>());
            }

            return pushNotification;
        });
    }
}
