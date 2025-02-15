package inc.notification.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.data.cassandra.repository.Query;

import inc.notification.model.EmailNotification;



public interface EmailNotificationRepository extends CassandraRepository<EmailNotification, UUID> {
    
     @Query("select * from email_notification where type = ?0 ALLOW FILTRING")
    List<EmailNotification> findByDomainKey(Integer type);
    
}


