package inc.yowyob.service.notification.infrastructure.persistence.entities;

import inc.yowyob.scylladb.entity.Entity;
import inc.yowyob.service.notification.infrastructure.config.DatabaseSchema;
import inc.yowyob.service.notification.infrastructure.persistence.entities.keys.DesignTemplateKey;
import lombok.*;
import lombok.experimental.Accessors;
import lombok.experimental.Delegate;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

import java.util.UUID;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ToString
@Table(DatabaseSchema.DESIGN_TEMPLATE_TABLE)
public class DesignTemplate extends Entity {

    @PrimaryKey
    @Delegate
    protected DesignTemplateKey key;

    @Column(value = "title")
    protected String title;

    @Column(value = "html")
    protected String html;

    @Column(value = "is_default")
    protected Boolean isDefault;

    @Column(value = "subject")
    protected String subject;

}
