package ProjetDeSyntheseNotification.notification.repository;

import ProjetDeSyntheseNotification.notification.model.CurrentNotification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CurrentNotificationRepository extends JpaRepository<CurrentNotification, Long> {
    // Recherche par type et priorité
    CurrentNotification findByTypeAndPriority(String type, int priority);
}
