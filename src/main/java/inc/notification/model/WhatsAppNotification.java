package inc.notification.model;

import lombok.Data;
import org.springframework.data.cassandra.core.mapping.Table;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Column;
import java.util.UUID;

@Data
@Table("whatsapp_notifications") // Nom de la table en base de données
public class WhatsAppNotification {

    @PrimaryKey
    @Column("whatsapp_notification_id")
    private UUID whatsappNotificationId; // Identifiant unique

    @Column("recipient")
    private String to; // Numéro du destinataire

    @Column("message")
    private String message; // Contenu du message

}

