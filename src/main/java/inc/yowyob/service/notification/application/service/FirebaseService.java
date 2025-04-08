package inc.yowyob.service.notification.application.service;

import com.google.api.core.ApiFuture;
import com.google.firebase.messaging.BatchResponse;
import inc.yowyob.service.notification.infrastructure.persistence.entities.PushNotification;
import inc.yowyob.service.notification.infrastructure.persistence.entities.Template;
import reactor.core.publisher.Mono;

public interface FirebaseService {
    public Mono<ApiFuture<BatchResponse>> sendPushNotification(Template template, PushNotification pushNotification);

}
