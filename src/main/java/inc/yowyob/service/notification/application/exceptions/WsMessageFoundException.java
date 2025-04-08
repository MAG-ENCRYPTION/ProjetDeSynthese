package inc.yowyob.service.notification.application.exceptions;

import java.util.UUID;

public class WsMessageFoundException extends RuntimeException {

    public WsMessageFoundException(String systemId, UUID id) {
        super(String.format("Websocket message not found for template %s and id %s", systemId, id));
    }
}
