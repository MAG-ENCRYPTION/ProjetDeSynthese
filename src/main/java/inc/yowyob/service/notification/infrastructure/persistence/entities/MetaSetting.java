package inc.yowyob.service.notification.infrastructure.persistence.entities;

import inc.yowyob.scylladb.entity.Entity;
import inc.yowyob.service.notification.infrastructure.config.DatabaseSchema;
import inc.yowyob.service.notification.infrastructure.persistence.entities.keys.MetaSettingKey;
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
@Table(DatabaseSchema.META_SETTINGS_TABLE)
public class MetaSetting extends Entity {

    @PrimaryKey
    @Delegate
    protected MetaSettingKey key;

    @Column(value = "phone_number_id")
    protected String phoneNumberId;

    @Column(value = "access_token")
    protected String accessToken;

    @Column(value = "is_default")
    protected Boolean isDefault;

}
