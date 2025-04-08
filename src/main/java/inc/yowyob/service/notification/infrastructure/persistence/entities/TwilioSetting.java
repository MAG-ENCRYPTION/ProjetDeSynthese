package inc.yowyob.service.notification.infrastructure.persistence.entities;

import inc.yowyob.scylladb.entity.Entity;
import inc.yowyob.service.notification.infrastructure.config.DatabaseSchema;
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
@Table(DatabaseSchema.TWILIO_SETTINGS_TABLE)
public class TwilioSetting extends Entity {

    @PrimaryKey
    @Delegate
    protected SmsSettingKey key;

    @Column(value = "account_id")
    protected String accountId;

    @Column(value = "auth_token")
    protected String authToken;

    @Column(value = "sender_phone_number")
    protected String senderPhoneNumber;

    @Column(value = "is_default")
    protected Boolean isDefault;

}
