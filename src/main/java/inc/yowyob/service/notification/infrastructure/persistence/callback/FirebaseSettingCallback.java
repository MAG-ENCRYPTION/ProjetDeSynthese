package inc.yowyob.service.notification.infrastructure.persistence.callback;

import com.datastax.driver.core.utils.UUIDs;
import com.datastax.oss.driver.api.core.CqlIdentifier;
import inc.yowyob.service.notification.infrastructure.persistence.entities.FirebaseSetting;
import lombok.extern.slf4j.Slf4j;
import org.reactivestreams.Publisher;
import org.springframework.core.annotation.Order;
import org.springframework.data.cassandra.core.mapping.event.ReactiveBeforeConvertCallback;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Order(1)
@Component
@Slf4j
public class FirebaseSettingCallback implements ReactiveBeforeConvertCallback<FirebaseSetting> {

    @Override
    public Publisher<FirebaseSetting> onBeforeConvert(FirebaseSetting firebaseSetting, CqlIdentifier tableName) {

        if (firebaseSetting.getId() == null) {
            firebaseSetting.setId(UUIDs.timeBased());
        }

        return Mono.just(firebaseSetting);
    }
}
