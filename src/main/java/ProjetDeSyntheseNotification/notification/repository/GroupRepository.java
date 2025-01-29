package ProjetDeSyntheseNotification.notification.repository;

import ProjetDeSyntheseNotification.notification.model.Group;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GroupRepository extends JpaRepository<Group, Long> {
    // Recherche par nom du groupe
    Group findByGroupName(String groupName);
}
