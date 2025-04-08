package inc.yowyob.service.notification.utils;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.HashMap;
import java.util.Map;

public class FirebaseJsonUtil {
    public static String createFirebaseJson(String projectId, String privateKey, String clientEmail) {
        try {
            Map<String, Object> jsonMap = new HashMap<>();
            jsonMap.put("type", "service_account");
            jsonMap.put("project_id", projectId);
            jsonMap.put("private_key", privateKey);
            jsonMap.put("client_email", clientEmail);

            // Convert Map to JSON string
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.writeValueAsString(jsonMap);
        } catch (Exception e) {
            throw new RuntimeException("Failed to create Firebase JSON", e);
        }
    }
}
