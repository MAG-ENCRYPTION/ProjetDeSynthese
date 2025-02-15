package inc.yowyob.service.notification.repository;

import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.stereotype.Repository;

import inc.yowyob.service.notification.model.WhatsAppNotification;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface WhatsAppNotificationRepository extends CassandraRepository<WhatsAppNotification, UUID> {

    // Trouver toutes les notifications envoyées à un destinataire spécifique
    List<WhatsAppNotification> findByTo(String to);

    // Rechercher une notification spécifique par son message
    Optional<WhatsAppNotification> findByMessage(String message);

    // Supprimer une notification par son identifiant
    void deleteByWhatsappNotificationId(UUID whatsappNotificationId);

    // Supprimer toutes les notifications d'un destinataire
    void deleteByTo(String to);
}
