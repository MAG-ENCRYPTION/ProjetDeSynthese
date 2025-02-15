package inc.yowyob.service.notification.repository;

import java.util.UUID;

import org.springframework.data.repository.CrudRepository;

import inc.yowyob.service.notification.model.Log;

public interface LogRepository extends CrudRepository<Log, UUID> {
    // Méthodes pour gérer les logs
}
