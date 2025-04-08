package inc.yowyob.service.notification.infrastructure.persistence.entities;

import inc.yowyob.scylladb.entity.Entity;
import inc.yowyob.service.notification.infrastructure.config.DatabaseSchema;
import inc.yowyob.service.notification.infrastructure.persistence.entities.keys.FirebaseSettingKey;
import inc.yowyob.service.notification.infrastructure.persistence.entities.keys.SmsSettingKey;
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
@Table(DatabaseSchema.FIREBASE_SETTINGS_TABLE)
public class FirebaseSetting extends Entity {

    @PrimaryKey
    @Delegate
    protected FirebaseSettingKey key;

    @Column(value = "project_id")
    protected String projectId;

    @Column(value = "private_key")
    protected String privateKey;

    @Column(value = "client_email")
    protected String clientEmail;

    @Column(value = "is_default")
    protected Boolean isDefault;

}
