package inc.yowyob.service.notification.application.service.impl;

import inc.yowyob.service.notification.application.dto.ws.WsMessageRequest;
import inc.yowyob.service.notification.application.service.WsMessageService;
import inc.yowyob.service.notification.application.service.WsService;
import inc.yowyob.service.notification.application.service.WsSettingService;
import inc.yowyob.service.notification.infrastructure.persistence.entities.WsMessage;
import inc.yowyob.service.notification.infrastructure.persistence.entities.WsSetting;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.io.UnsupportedEncodingException;
import java.util.Set;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class WsServiceImpl implements WsService {

    private final WsSettingService wsSettingService;

    private final WsMessageService wsMessageService;

    private final SimpMessagingTemplate messagingTemplate;

    @Override
    public Mono<Void> sendSmsNotification(UUID organizationId, String systemId, WsMessageRequest request) {
        return wsMessageService.createWsMessage(systemId, request).flatMap(wsMessage -> {
            return sendByWsSetting(organizationId, wsMessage);
        }).then();
    }

    private Mono<Object> sendByWsSetting(UUID organizationId, WsMessage wsMessage) {
        return wsSettingService.getWsSettingByIdOrDefault(organizationId, wsMessage.getSystemId()).flatMap(wsSetting -> {
            return this.sendQuickly(wsMessage, wsSetting);
        });
    }

    private Mono<Object> sendQuickly(WsMessage wsMessage, WsSetting wsSetting) {
        return Mono.fromRunnable(() -> {
            try {
                this.send(wsMessage, wsSetting);
            } catch (MessagingException | UnsupportedEncodingException e) {
                throw new RuntimeException(e);
            }
        });
    }

    private void send(WsMessage wsMessage, WsSetting wsSetting) throws MessagingException, UnsupportedEncodingException {
        Set<String> topics = wsMessage.getTopics();
        topics.add(wsSetting.getTopic());
        topics.forEach(topic -> {
            messagingTemplate.convertAndSend(topic, wsMessage.getMessage());
        });
    }

}
