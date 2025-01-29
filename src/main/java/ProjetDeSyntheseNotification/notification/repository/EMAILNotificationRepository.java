package ProjetDeSyntheseNotification.notification.repository;

import ProjetDeSyntheseNotification.notification.model.EmailNotification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EMAILNotificationRepository extends JpaRepository<EmailNotification, Long> {
    // Recherche par objet ou email
    EmailNotification findBySubject(String subject);
    EmailNotification findByEmail(String email);
}


