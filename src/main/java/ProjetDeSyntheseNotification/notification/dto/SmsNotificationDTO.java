package ProjetDeSyntheseNotification.notification.dto;

public class SmsNotificationDTO {

    private String number;
    private String message;
    private String type;
    private int priority;

    // Constructeurs
    public SmsNotificationDTO() {
    }

    public SmsNotificationDTO(String number, String message, String type, int priority) {
        this.number = number;
        this.message = message;
        this.type = type;
        this.priority = priority;
    }

    // Getters et Setters
    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }
}
