package inc.yowyob.service.notification.application.dto.designtemplate;

import inc.yowyob.utils.dto.EntityDto;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;

import java.util.UUID;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@ToString
public class DesignTemplateDto extends EntityDto {

    @Column(value = "organization_id")
    protected String organizationId;

    @Column(value = "id")
    protected UUID id;

    @Column(value = "title")
    protected String title;

    @Column(value = "html")
    protected String html;

    @Column(value = "subject")
    protected String subject;

}
