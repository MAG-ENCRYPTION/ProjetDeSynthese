package inc.yowyob.service.notification.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import inc.yowyob.service.notification.model.WhatsAppRequest;
import inc.yowyob.service.notification.service.WhatsAppService;

import java.io.IOException;

@RestController
@RequestMapping("/api/whatsapp")
public class WhatsAppController {
    @Autowired
    private WhatsAppService whatsAppService;

    @PostMapping("/send-template")
    public void sendTemplateMessage(@RequestBody WhatsAppRequest request) throws IOException {
        whatsAppService.sendTemplateMessage(
                request.getPhoneNumber(),
                request.getTemplateName(),
                request.getLanguageCode(),
                request.getHeaderPlaceholders(),
                request.getBodyPlaceholders(),
                request.getButtonPlaceholders());
    }
}