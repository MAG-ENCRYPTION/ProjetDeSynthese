package inc.yowyob.service.notification.application.dto.sms;

import com.fasterxml.jackson.annotation.JsonProperty;
import inc.yowyob.service.notification.application.dto.notification.NotificationDto;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Data
public class SmsNotificationDto extends NotificationDto {

    @JsonProperty("sender_phone_number")
    private String senderPhoneNumber;

}
