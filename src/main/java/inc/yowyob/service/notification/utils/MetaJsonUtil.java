package inc.yowyob.service.notification.utils;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MetaJsonUtil {
    public static String createTextMetaJson(String toPhoneNumber, String body) {
        try {
            Map<String, Object> jsonMap = new HashMap<>();
            Map<String, Object> bodyJsonMap = new HashMap<>();

            bodyJsonMap.put("body", body);

            jsonMap.put("messaging_product", "whatsapp");
            jsonMap.put("to", toPhoneNumber);
            jsonMap.put("type", "text");
            jsonMap.put("text", bodyJsonMap);

            // Convert Map to JSON string
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.writeValueAsString(jsonMap);
        } catch (Exception e) {
            throw new RuntimeException("Failed to create Whatsapp JSON", e);
        }
    }

    public static String createTemplateMetaJson(String templateName, String toPhoneNumber, Map<String, Object> parameters) {
        try {
            List<Map<String, String>> listOfVariables = parameters.values().stream().map(o -> {
                Map<String, String> formattedMap = new HashMap<>();
                formattedMap.put("type", "text");
                formattedMap.put("text", o.toString());
                return formattedMap;
            }).toList();

            Map<String, Object> jsonMap = new HashMap<>();
            Map<String, Object> templateJsonMap = new HashMap<>();
            Map<String, Object> languageJsonMap = new HashMap<>();
            Map<String, Object> bodyJsonMap = new HashMap<>();

            languageJsonMap.put("code", "en_US");

            bodyJsonMap.put("type", "body");
            bodyJsonMap.put("parameters", listOfVariables);

            templateJsonMap.put("name", templateName);
            templateJsonMap.put("language", languageJsonMap);
            templateJsonMap.put("components", List.of(bodyJsonMap));


            jsonMap.put("messaging_product", "whatsapp");
            jsonMap.put("to", toPhoneNumber);
            jsonMap.put("type", "template");
            jsonMap.put("template", templateJsonMap);

            // Convert Map to JSON string
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.writeValueAsString(jsonMap);
        } catch (Exception e) {
            throw new RuntimeException("Failed to create Whatsapp JSON", e);
        }
    }
}
