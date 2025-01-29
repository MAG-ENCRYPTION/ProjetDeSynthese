package ProjetDeSyntheseNotification.notification.model;

import jakarta.persistence.*;

@Entity
public class PushNotification extends CurrentNotification {
    protected String senderName;
    protected String receiverName;
    protected String subject;

    public PushNotification(String message, String type, int priority, String senderName, String receiverName, String subject) {
        super(message, type, priority);
        this.senderName = senderName;
        this.receiverName = receiverName;
        this.subject = subject;
    }

    public PushNotification() {
    }

    public Object getMessage() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getMessage'");
    }

    public Object getSubject() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getSubject'");
    }

    public void setMessage(Object message) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setMessage'");
    }

    public void setSubject(Object subject2) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setSubject'");
    }

    public Object getReceiverName() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getReceiverName'");
    }

    public void setSenderName(Object receiverName2) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setSenderName'");
    }
}
