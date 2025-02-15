package inc.yowyob.service.notification.repository;



import java.util.UUID;

import org.springframework.data.cassandra.repository.CassandraRepository;

import inc.yowyob.service.notification.model.Simple;

public interface SimpleRepository extends CassandraRepository<Simple, UUID> {
    // Pas de méthode spécifique pour le moment
}



