package inc.yowyob.service.notification.infrastructure.persistence.callback;

import com.datastax.driver.core.utils.UUIDs;
import com.datastax.oss.driver.api.core.CqlIdentifier;
import inc.yowyob.service.notification.application.enums.NotificationStatus;
import inc.yowyob.service.notification.application.service.TemplateMessageBuilderService;
import inc.yowyob.service.notification.infrastructure.persistence.entities.EmailNotification;
import inc.yowyob.service.notification.infrastructure.persistence.entities.keys.DesignTemplateKey;
import inc.yowyob.service.notification.infrastructure.persistence.entities.keys.TemplateKey;
import inc.yowyob.service.notification.infrastructure.persistence.repository.DesignTemplateRepository;
import inc.yowyob.service.notification.infrastructure.persistence.repository.TemplateRepository;
import lombok.extern.slf4j.Slf4j;
import org.reactivestreams.Publisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.annotation.Order;
import org.springframework.data.cassandra.core.mapping.event.ReactiveBeforeConvertCallback;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Map;

@Order(1)
@Component
@Slf4j
public class EmailNotificationCallback implements ReactiveBeforeConvertCallback<EmailNotification> {

    @Autowired
    private TemplateMessageBuilderService templateMessageBuilderService;

    @Autowired
    @Lazy
    private TemplateRepository templateRepository;

    @Autowired
    @Lazy
    private DesignTemplateRepository designTemplateRepository;

    @Override
    public Publisher<EmailNotification> onBeforeConvert(EmailNotification emailNotification, CqlIdentifier tableName) {

        TemplateKey key = TemplateKey.from(emailNotification.getOrganizationId(), emailNotification.getTemplateId());

        Map<String, String> variables = emailNotification.getMetadata();
        variables.put("sender_email", emailNotification.getSenderEmail());
        variables.put("sender_name", emailNotification.getSenderName());

        return templateRepository.findById(key).flatMap(template -> {
            return designTemplateRepository.findById(DesignTemplateKey.from(template.getOrganizationId(), template.getDesignTemplateId())).flatMap(designTemplate -> {
                return templateMessageBuilderService.build(emailNotification.getSubject(), variables, designTemplate).map(body -> {
                    emailNotification.setBody(body);

                    if (emailNotification.getId() == null) {
                        emailNotification.setId(UUIDs.timeBased());
                    }

                    if (emailNotification.getStatus() == null) {
                        emailNotification.setStatus(NotificationStatus.PENDING);
                    }

                    if (emailNotification.getRecipients() == null) {
                        emailNotification.setRecipients(new HashSet<>());
                    }

                    if (emailNotification.getCc() == null) {
                        emailNotification.setCc(new HashSet<>());
                    }

                    if (emailNotification.getBcc() == null) {
                        emailNotification.setBcc(new HashSet<>());
                    }

                    return emailNotification;
                });

            });
        });
    }
}
