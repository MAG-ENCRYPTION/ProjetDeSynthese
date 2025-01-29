package ProjetDeSyntheseNotification.notification.model;

import jakarta.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class Notification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    protected String message;

    public Notification(String message) {
        this.message = message;
    }

    public Notification() {
    }

   // public Object getMessage() {
       // return
        // throw new UnsupportedOperationException("Unimplemented method 'getMessage'");
   // }

   // public void setMessage(String message2) {
    //    this.message = message2;
      //  throw new UnsupportedOperationException("Unimplemented method 'setMessage'");
    //}
}
