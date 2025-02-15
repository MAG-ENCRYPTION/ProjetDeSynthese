package inc.yowyob.service.notification.dto;

public class WhatsAppNotificationDTO {
    private String to;       // Numéro de téléphone du destinataire
    private String message;  // Message à envoyer

    // Getters and Setters
    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}