package ProjetDeSyntheseNotification.notification.repository;

import ProjetDeSyntheseNotification.notification.model.Notification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NotificationRepository extends JpaRepository<Notification, Long> {
    // Ajout d'une méthode pour rechercher par message
    Notification findByMessage(String message);
}
