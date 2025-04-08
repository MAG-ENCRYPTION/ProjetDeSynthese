package inc.yowyob.service.notification.infrastructure.persistence.callback;

import com.datastax.driver.core.utils.UUIDs;
import com.datastax.oss.driver.api.core.CqlIdentifier;
import inc.yowyob.service.notification.infrastructure.persistence.entities.Reminder;
import lombok.extern.slf4j.Slf4j;
import org.reactivestreams.Publisher;
import org.springframework.core.annotation.Order;
import org.springframework.data.cassandra.core.mapping.event.ReactiveBeforeConvertCallback;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Order(1)
@Component
@Slf4j
public class ReminderCallback implements ReactiveBeforeConvertCallback<Reminder> {

    @Override
    public Publisher<Reminder> onBeforeConvert(Reminder reminder, CqlIdentifier tableName) {

        if (reminder.getId() == null) {
            reminder.setId(UUIDs.timeBased());
        }

        return Mono.just(reminder);
    }
}
