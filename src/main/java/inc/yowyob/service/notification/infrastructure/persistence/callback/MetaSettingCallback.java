package inc.yowyob.service.notification.infrastructure.persistence.callback;

import com.datastax.driver.core.utils.UUIDs;
import com.datastax.oss.driver.api.core.CqlIdentifier;
import inc.yowyob.service.notification.infrastructure.persistence.entities.MetaSetting;
import lombok.extern.slf4j.Slf4j;
import org.reactivestreams.Publisher;
import org.springframework.core.annotation.Order;
import org.springframework.data.cassandra.core.mapping.event.ReactiveBeforeConvertCallback;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Order(1)
@Component
@Slf4j
public class MetaSettingCallback implements ReactiveBeforeConvertCallback<MetaSetting> {

    @Override
    public Publisher<MetaSetting> onBeforeConvert(MetaSetting metaSetting, CqlIdentifier tableName) {

        if (metaSetting.getId() == null) {
            metaSetting.setId(UUIDs.timeBased());
        }

        return Mono.just(metaSetting);
    }
}
