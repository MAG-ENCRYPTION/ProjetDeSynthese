package ProjetDeSyntheseNotification.notification.repository;

import ProjetDeSyntheseNotification.notification.model.SystemNotification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SystemNotificationRepository extends JpaRepository<SystemNotification, Long> {
    // Recherche par partie du système
    SystemNotification findBySystemPart(String systemPart);
}
