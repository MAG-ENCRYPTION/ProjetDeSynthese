package inc.yowyob.service.notification.infrastructure.persistence.callback;

import com.datastax.driver.core.utils.UUIDs;
import com.datastax.oss.driver.api.core.CqlIdentifier;
import inc.yowyob.service.notification.infrastructure.persistence.entities.WsMessage;
import lombok.extern.slf4j.Slf4j;
import org.reactivestreams.Publisher;
import org.springframework.core.annotation.Order;
import org.springframework.data.cassandra.core.mapping.event.ReactiveBeforeConvertCallback;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Order(1)
@Component
@Slf4j
public class WsMessageCallback implements ReactiveBeforeConvertCallback<WsMessage> {

    @Override
    public Publisher<WsMessage> onBeforeConvert(WsMessage wsMessage, CqlIdentifier tableName) {

        if (wsMessage.getId() == null) {
            wsMessage.setId(UUIDs.timeBased());
        }

        return Mono.just(wsMessage);
    }
}
