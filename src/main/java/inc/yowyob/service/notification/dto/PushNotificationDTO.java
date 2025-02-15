package inc.yowyob.service.notification.dto;

import java.util.List;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class PushNotificationDTO {
    private String senderName;
    private String recieverName;
    private List<UUID> receiverIds; // Liste de destinataires au lieu d'un seul UUID
    private String subject;
    private String message;
    private String groupName;
    private String priority;
    private String Type;
    private String property;
}
