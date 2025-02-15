package inc.notification.service;

import org.springframework.stereotype.Service;

import okhttp3.*;

import java.io.IOException;
import java.util.Map;

@Service
public class WhatsAppService {

    private static final String ACCESS_TOKEN = "EAAQiBV13cBcBO6Cm6oF4DMO4ILtwcAgDznLI6i5eZA7T0mQqkxvmUwBZCHoAmxVWSCMzkBZB3QQPtBUDTXFTI8X5NjpxAEokTTiIothK9X8UCdqO2fuFufaYV30cfTewGAbT08idKzDcGLN8OlhOTDGG8EZBv9nuZAW7NC7TWjqpsa4sJuoXAXKhE1b3xhHUP6fW2Kv9RVMp08T979PZBJrE9VqpsZD";
    private static final String PHONE_NUMBER_ID = "563416200188193";
    private static final String API_URL = "https://graph.facebook.com/v21.0/" + PHONE_NUMBER_ID + "/messages";

    private final OkHttpClient client = new OkHttpClient();

    /**
     * Envoie un message WhatsApp basé sur un template défini dans Meta Business
     * Manager.
     * 
     * @param phoneNumber  Numéro de téléphone du destinataire (au format
     *                     international, ex: "237658530298").
     * @param templateName Nom du template enregistré sur WhatsApp.
     * @param languageCode Code de la langue du template (ex: "fr", "en", "en_US").
     * @param parameters   Liste des valeurs à injecter dans les placeholders du
     *                     template.
     */

    public void sendTemplateMessage(String phoneNumber, String templateName, String languageCode,
            Map<String, String> headerPlaceholders, Map<String, String> bodyPlaceholders,
            Map<String, String> buttonPlaceholders) throws IOException {
        MediaType JSON = MediaType.get("application/json; charset=utf-8");

        String json = generateTemplateJson(phoneNumber, templateName, languageCode, headerPlaceholders,
                bodyPlaceholders, buttonPlaceholders);
        RequestBody body = RequestBody.create(json, JSON);

        Request request = new Request.Builder()
                .url(API_URL)
                .header("Authorization", "Bearer " + ACCESS_TOKEN)
                .post(body)
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful())
                throw new IOException("Unexpected code " + response);
            System.out.println(response.body().string());
        }
    }

    private String generateTemplateJson(String phoneNumber, String templateName, String languageCode,
            Map<String, String> headerPlaceholders, Map<String, String> bodyPlaceholders,
            Map<String, String> buttonPlaceholders) {
        // Créez le JSON selon le format requis par l'API WhatsApp
        StringBuilder json = new StringBuilder();
        json.append("{");
        json.append("\"messaging_product\":\"whatsapp\",");
        json.append("\"to\":\"").append(phoneNumber).append("\",");
        json.append("\"type\":\"template\",");
        json.append("\"template\":{");
        json.append("\"name\":\"").append(templateName).append("\",");
        json.append("\"language\":{\"code\":\"").append(languageCode).append("\"},");
        json.append("\"components\":[");

        if (!headerPlaceholders.isEmpty()) {
            json.append("{\"type\":\"header\",\"parameters\":[");
            headerPlaceholders.forEach(
                    (key, value) -> json.append("{\"type\":\"text\",\"text\":\"").append(value).append("\"},"));
            json.setLength(json.length() - 1); // Supprimer la dernière virgule
            json.append("]},");
        }

        if (!bodyPlaceholders.isEmpty()) {
            json.append("{\"type\":\"body\",\"parameters\":[");
            bodyPlaceholders.forEach(
                    (key, value) -> json.append("{\"type\":\"text\",\"text\":\"").append(value).append("\"},"));
            json.setLength(json.length() - 1); // Supprimer la dernière virgule
            json.append("]},");
        }

        if (!buttonPlaceholders.isEmpty()) {
            json.append("{\"type\":\"button\",\"parameters\":[");
            buttonPlaceholders.forEach(
                    (key, value) -> json.append("{\"type\":\"text\",\"text\":\"").append(value).append("\"},"));
            json.setLength(json.length() - 1); // Supprimer la dernière virgule
            json.append("]},");
        }

        if (json.charAt(json.length() - 1) == ',') {
            json.setLength(json.length() - 1); // Supprimer la dernière virgule si nécessaire
        }

        json.append("]");
        json.append("}");
        json.append("}");
        return json.toString();
    }
}