package inc.notification.model;

import java.util.Map;

import lombok.Data;



@Data
public class WhatsAppRequest {
    private String phoneNumber;
    private String templateName;
    private String languageCode;
    private Map<String, String> headerPlaceholders;
    private Map<String, String> bodyPlaceholders;
    private Map<String, String> buttonPlaceholders;
}