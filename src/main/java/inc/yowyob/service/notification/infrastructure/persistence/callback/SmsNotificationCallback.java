package inc.yowyob.service.notification.infrastructure.persistence.callback;

import com.datastax.driver.core.utils.UUIDs;
import com.datastax.oss.driver.api.core.CqlIdentifier;
import inc.yowyob.service.notification.application.enums.NotificationStatus;
import inc.yowyob.service.notification.application.service.MessageBuilderService;
import inc.yowyob.service.notification.infrastructure.persistence.entities.SmsNotification;
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
public class SmsNotificationCallback implements ReactiveBeforeConvertCallback<SmsNotification> {

    @Autowired
    @Lazy
    private MessageBuilderService messageBuilderService;

    @Override
    public Publisher<SmsNotification> onBeforeConvert(SmsNotification smsNotification, CqlIdentifier tableName) {

        TemplateKey key = TemplateKey.from(smsNotification.getOrganizationId(), smsNotification.getTemplateId());

        return messageBuilderService.build(smsNotification.getSubject(), smsNotification.getMetadata(), key).map(body -> {
            smsNotification.setBody(body);

            if (smsNotification.getId() == null) {
                smsNotification.setId(UUIDs.timeBased());
            }

            if (smsNotification.getStatus() == null) {
                smsNotification.setStatus(NotificationStatus.PENDING);
            }

            if (smsNotification.getRecipients() == null) {
                smsNotification.setRecipients(new HashSet<>());
            }

            return smsNotification;
        });
    }
}
