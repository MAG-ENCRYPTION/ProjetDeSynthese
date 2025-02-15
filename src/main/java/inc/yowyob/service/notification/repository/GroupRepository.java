package inc.yowyob.service.notification.repository;


import java.util.UUID;

import org.springframework.data.cassandra.repository.CassandraRepository;

import inc.yowyob.service.notification.model.Group;

public interface GroupRepository extends CassandraRepository<Group, UUID> {
    // Recherche par type et priorité
    Group findByGroupName(String groupName);
}
