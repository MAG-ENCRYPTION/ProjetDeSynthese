package inc.yowyob.service.notification.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class EmailNotificationDTO {

    private String Message;

    private String Subject;

    private String Type;

    private String Email;

    private Float Priority;

}
