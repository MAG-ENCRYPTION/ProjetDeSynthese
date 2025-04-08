package inc.yowyob.service.notification.application.service;

import inc.yowyob.service.notification.application.dto.ws.WsMessageRequest;
import inc.yowyob.service.notification.infrastructure.persistence.entities.WsMessage;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;


public interface WsMessageService {

    public Mono<WsMessage> createWsMessage(String systemId, WsMessageRequest request);

    public Flux<WsMessage> getAllWsMessages(String systemId);

    public Mono<WsMessage> getWsMessageById(String systemId, UUID id);

    public Mono<Void> deleteWsMessage(String systemId, UUID id);

}
