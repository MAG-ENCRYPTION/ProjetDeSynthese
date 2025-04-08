package inc.yowyob.service.notification.infrastructure.persistence.callback;

import com.datastax.driver.core.utils.UUIDs;
import com.datastax.oss.driver.api.core.CqlIdentifier;
import inc.yowyob.service.notification.application.enums.NotificationStatus;
import inc.yowyob.service.notification.application.service.MessageBuilderService;
import inc.yowyob.service.notification.infrastructure.persistence.entities.WhatsappNotification;
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
public class WhatsappNotificationCallback implements ReactiveBeforeConvertCallback<WhatsappNotification> {

    @Autowired
    @Lazy
    private MessageBuilderService messageBuilderService;

    @Override
    public Publisher<WhatsappNotification> onBeforeConvert(WhatsappNotification whatsappNotification, CqlIdentifier tableName) {

        TemplateKey key = TemplateKey.from(whatsappNotification.getOrganizationId(), whatsappNotification.getTemplateId());

        return messageBuilderService.build(whatsappNotification.getSubject(), whatsappNotification.getMetadata(), key).map(body -> {
            whatsappNotification.setBody(body);

            if (whatsappNotification.getId() == null) {
                whatsappNotification.setId(UUIDs.timeBased());
            }

            if (whatsappNotification.getStatus() == null) {
                whatsappNotification.setStatus(NotificationStatus.PENDING);
            }

            if (whatsappNotification.getRecipients() == null) {
                whatsappNotification.setRecipients(new HashSet<>());
            }

            return whatsappNotification;
        });
    }
}
