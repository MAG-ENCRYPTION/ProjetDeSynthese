package inc.yowyob.service.notification.infrastructure.persistence.entities;

import inc.yowyob.scylladb.entity.Entity;
import java.util.UUID;

import inc.yowyob.service.notification.application.enums.ReminderType;
import inc.yowyob.service.notification.infrastructure.config.DatabaseSchema;
import inc.yowyob.service.notification.infrastructure.persistence.entities.keys.ReminderKey;
import lombok.*;
import lombok.experimental.Delegate;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;


@NoArgsConstructor
@Data
@ToString
@EqualsAndHashCode(callSuper = false)
@Table(DatabaseSchema.REMINDER_TABLE)
public class Reminder extends Entity {

    @PrimaryKey
    @Delegate
    protected ReminderKey key;
    
    @Column(value = "design_template_id")
    protected UUID designTemplateId;
        
    @Column(value = "type")
    protected ReminderType type;
    
    @Column(value = "value")
    protected Integer value;

}
