package inc.notification.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.data.cassandra.repository.Query;

import inc.notification.model.SmsNotification;



public interface SmsNotificationRepository extends CassandraRepository<SmsNotification, UUID> {
    // Recherche par numéro de téléphone
    SmsNotification findByNumber(String number);

     @Query("select * from agency where type = ?0 ALLOW FILTRING")
    List<SmsNotification> findByDomainKey(Integer type);

    List findAll();

    // SauvegarSmsNotificationcation à partir d'un DTO
    SmsNotification save(SmsNotification smsNotification);
}


