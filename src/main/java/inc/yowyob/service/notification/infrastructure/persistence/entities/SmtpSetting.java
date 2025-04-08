package inc.yowyob.service.notification.infrastructure.persistence.entities;

import inc.yowyob.scylladb.entity.Entity;

import inc.yowyob.service.notification.application.enums.Encryption;
import inc.yowyob.service.notification.infrastructure.config.DatabaseSchema;
import inc.yowyob.service.notification.infrastructure.persistence.entities.keys.SmtpSettingKey;
import lombok.*;
import lombok.experimental.Accessors;
import lombok.experimental.Delegate;
import org.springframework.data.cassandra.core.mapping.CassandraType;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ToString
@Table(DatabaseSchema.SMTP_SETTINGS_TABLE)
public class SmtpSetting extends Entity {

    @PrimaryKey
    @Delegate
    protected SmtpSettingKey key;
    
    @Column(value = "host")
    protected String host;
    
    @Column(value = "port")
    protected Integer port;
    
    @Column(value = "encryption")
    @CassandraType(type = CassandraType.Name.TEXT)
    protected Encryption encryption;
    
    @Column(value = "username")
    protected String username;
    
    @Column(value = "password")
    protected String password;
    
    @Column(value = "sender_email")
    protected String senderEmail;
    
    @Column(value = "sender_name")
    protected String senderName;
    
    @Column(value = "is_default")
    protected Boolean isDefault;
    
}
