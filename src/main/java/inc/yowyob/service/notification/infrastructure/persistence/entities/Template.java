package inc.yowyob.service.notification.infrastructure.persistence.entities;

import inc.yowyob.scylladb.entity.Entity;
import inc.yowyob.service.notification.application.enums.ProviderType;
import inc.yowyob.service.notification.infrastructure.config.DatabaseSchema;
import inc.yowyob.service.notification.infrastructure.persistence.entities.keys.TemplateKey;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;
import lombok.experimental.Delegate;
import org.springframework.data.cassandra.core.mapping.CassandraType;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

import java.util.Set;
import java.util.UUID;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ToString
@Table(DatabaseSchema.EMAIL_TEMPLATE_TABLE)
public class Template extends Entity {

    @PrimaryKey
    @Delegate
    protected TemplateKey key;

    @Column(value = "setting_id")
    protected UUID settingId;

    @Column(value = "provider_type")
    protected ProviderType providerType;

    @Column(value = "design_template_id")
    protected UUID designTemplateId;

    @Column(value = "title")
    protected String title;

    @Column(value = "cc")
    @CassandraType(type = CassandraType.Name.SET, typeArguments = CassandraType.Name.TEXT)
    protected Set<String> cc;

    @Column(value = "is_default")
    protected Boolean isDefault;

}
