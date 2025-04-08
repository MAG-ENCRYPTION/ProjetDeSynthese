package inc.yowyob.service.notification.application.service;

import inc.yowyob.service.notification.application.dto.sms.SmsNotificationRequest;
import inc.yowyob.service.notification.application.dto.ws.WsMessageRequest;
import reactor.core.publisher.Mono;

import java.util.UUID;

public interface WsService {
    public Mono<Void> sendSmsNotification(UUID organizationId, String systemId, WsMessageRequest request);
}
