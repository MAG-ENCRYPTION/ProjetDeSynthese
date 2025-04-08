package inc.yowyob.service.notification.application.dto.whatsapp;

import inc.yowyob.service.notification.application.dto.notification.NotificationDto;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ToString
public class WhatsappNotificationDto extends NotificationDto {

}

