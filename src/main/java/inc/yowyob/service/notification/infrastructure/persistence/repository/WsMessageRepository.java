package inc.yowyob.service.notification.infrastructure.persistence.repository;

import inc.yowyob.service.notification.infrastructure.persistence.entities.WsMessage;
import inc.yowyob.service.notification.infrastructure.persistence.entities.keys.WsMessageKey;
import org.springframework.data.cassandra.repository.ReactiveCassandraRepository;
import reactor.core.publisher.Flux;

import java.util.UUID;


public interface WsMessageRepository extends ReactiveCassandraRepository<WsMessage, WsMessageKey> {

    public Flux<WsMessage> findByKeySystemId(UUID systemId);
}


