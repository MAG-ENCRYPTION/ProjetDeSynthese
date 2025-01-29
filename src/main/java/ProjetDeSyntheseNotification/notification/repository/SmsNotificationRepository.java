package ProjetDeSyntheseNotification.notification.repository;

import java.util.List;

import ProjetDeSyntheseNotification.notification.model.SmsNotification;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ProjetDeSyntheseNotification.notification.dto.SmsNotificationDTO;

@Repository
public interface SmsNotificationRepository extends JpaRepository<SmsNotification, Long> {
    // Recherche par numéro de téléphone
    SmsNotification findByNumber(String number);

    List<SmsNotification> findAll();

    // SauvegarSmsNotificationcation à partir d'un DTO
    SmsNotification save(SmsNotificationDTO smsDTO);
}