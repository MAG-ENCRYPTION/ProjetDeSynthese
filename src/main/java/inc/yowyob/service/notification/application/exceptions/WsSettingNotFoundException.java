package inc.yowyob.service.notification.application.exceptions;

import java.util.UUID;

public class WsSettingNotFoundException extends RuntimeException {

    public WsSettingNotFoundException() {
        super("Default websocket settings not found");
    }

    public WsSettingNotFoundException(String systemId) {
        super(String.format("Websocket setting %s not found", systemId));
    }
}
