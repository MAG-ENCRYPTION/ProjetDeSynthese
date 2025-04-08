package inc.yowyob.service.notification.infrastructure.persistence.entities;

import inc.yowyob.scylladb.entity.Entity;
import inc.yowyob.service.notification.infrastructure.config.DatabaseSchema;
import inc.yowyob.service.notification.infrastructure.persistence.entities.keys.WsSettingKey;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;
import lombok.experimental.Delegate;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ToString
@Table(DatabaseSchema.WS_SETTINGS_TABLE)
public class WsSetting extends Entity {

    @PrimaryKey
    @Delegate
    protected WsSettingKey key;

    @Column(value = "topic")
    protected String topic;

    @Column(value = "webhook_url")
    protected String webhookUrl;

    @Column(value = "is_default")
    protected Boolean isDefault;

}
