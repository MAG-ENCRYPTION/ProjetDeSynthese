package ProjetDeSyntheseNotification.notification.repository;

import ProjetDeSyntheseNotification.notification.model.Simple;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SimpleRepository extends JpaRepository<Simple, Long> {
    // Pas de méthode spécifique pour le moment
}
