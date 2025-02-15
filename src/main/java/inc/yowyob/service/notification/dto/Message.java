package inc.yowyob.service.notification.dto;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;

import com.twilio.rest.api.v2010.account.availablephonenumbercountry.Local;

public class Message {
    private String subject;
    private String message;
    private String property;
    private LocalDateTime timestamp;

    public Message() {}

    public Message(String subject, String message, String property, LocalDateTime timestamp) {
        this.subject = subject;
        this.message = message;
        this.property = property;
        this.timestamp = timestamp;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getProperty() {
        return property;
    }

    public void setProperty(String property) {
        this.property = property;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }
}
