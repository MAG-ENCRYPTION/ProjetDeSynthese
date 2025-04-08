package inc.yowyob.service.notification.application.service.impl;

import inc.yowyob.service.notification.application.dto.ws.WsMessageRequest;
import inc.yowyob.service.notification.application.exceptions.WsMessageFoundException;
import inc.yowyob.service.notification.application.mappers.WsMessageMapper;
import inc.yowyob.service.notification.application.service.WsMessageService;
import inc.yowyob.service.notification.infrastructure.persistence.entities.WsMessage;
import inc.yowyob.service.notification.infrastructure.persistence.entities.keys.NotificationKey;
import inc.yowyob.service.notification.infrastructure.persistence.entities.keys.WsMessageKey;
import inc.yowyob.service.notification.infrastructure.persistence.repository.WsMessageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class WsMessageServiceImpl implements WsMessageService {

    private final WsMessageRepository wsMessageRepository;

    private final WsMessageMapper wsMessageMapper;

    @Override
    public Mono<WsMessage> createWsMessage(String systemId, WsMessageRequest request) {
        return wsMessageRepository.insert(wsMessageMapper.toEntity(systemId, request));
    }

    @Override
    public Flux<WsMessage> getAllWsMessages(String systemId) {
        return wsMessageRepository.findAll();
    }

    @Override
    public Mono<WsMessage> getWsMessageById(String systemId, UUID id) {
        return wsMessageRepository.findById(WsMessageKey.from(systemId, id))
                .switchIfEmpty(Mono.error(new WsMessageFoundException(systemId, id)));
    }

    public Mono<Void> deleteWsMessage(String systemId, UUID id) {
        return this.getWsMessageById(systemId, id).flatMap(wsMessageRepository::delete);
    }

}
