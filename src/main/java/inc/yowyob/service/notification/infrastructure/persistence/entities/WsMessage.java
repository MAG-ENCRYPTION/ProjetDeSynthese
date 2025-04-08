package inc.yowyob.service.notification.infrastructure.persistence.entities;

import inc.yowyob.scylladb.entity.Entity;
import inc.yowyob.service.notification.infrastructure.config.DatabaseSchema;
import inc.yowyob.service.notification.infrastructure.persistence.entities.keys.WsMessageKey;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Delegate;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

import java.util.Set;

@Data
@EqualsAndHashCode(callSuper = true)
@Table(DatabaseSchema.WS_MESSAGE_TABLE)
public class WsMessage extends Entity {

    @PrimaryKey
    @Delegate
    private WsMessageKey key;

    @Column("topic")
    private Set<String> topics;

    @Column("message")
    private String message;
}
