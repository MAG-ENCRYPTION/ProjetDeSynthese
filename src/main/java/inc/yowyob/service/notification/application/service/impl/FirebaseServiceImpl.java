package inc.yowyob.service.notification.application.service.impl;

import com.google.api.core.ApiFuture;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.messaging.BatchResponse;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.Message;
import com.google.firebase.messaging.Notification;
import inc.yowyob.service.notification.application.service.FirebaseService;
import inc.yowyob.service.notification.application.service.FirebaseSettingService;
import inc.yowyob.service.notification.infrastructure.persistence.entities.FirebaseSetting;
import inc.yowyob.service.notification.infrastructure.persistence.entities.PushNotification;
import inc.yowyob.service.notification.infrastructure.persistence.entities.Template;
import inc.yowyob.service.notification.utils.FirebaseJsonUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Service
@Slf4j
public class FirebaseServiceImpl implements FirebaseService {

    private final FirebaseSettingService firebaseSettingService;

    @Override
    public Mono<ApiFuture<BatchResponse>> sendPushNotification(Template template, PushNotification pushNotification) {
        return firebaseSettingService.getFirebaseSettingByIdOrDefault(template.getOrganizationId(), template.getSettingId()).flatMap(firebaseSetting -> {
            return this.sendQuickly(pushNotification, firebaseSetting, pushNotification.getBody());
        });
    }

    private Mono<ApiFuture<BatchResponse>> sendQuickly(PushNotification pushNotification, FirebaseSetting firebaseSetting, String body) {
        return this.send(pushNotification, firebaseSetting, body);
    }

    public Mono<ApiFuture<BatchResponse>> send(PushNotification pushNotification, FirebaseSetting firebaseSetting, String body) {
        return this.initializeFirebase(firebaseSetting).then(Mono.fromCallable(() -> {
            List<Message> messages = pushNotification.getRecipients().stream().map(recipient -> {
                return Message.builder()
                        .setToken(recipient)
                        .setNotification(Notification.builder()
                                .setTitle(pushNotification.getSubject())
                                .setBody(body)
                                .build())
                        .build();
            }).toList();

            return FirebaseMessaging.getInstance().sendEachAsync(messages);
        }));
    }

    /**
     * Dynamically initializes Firebase using the given config ID.
     */
    private Mono<Void> initializeFirebase(FirebaseSetting firebaseSetting) {
        return Mono.just(firebaseSetting).flatMap(firebaseSetting1 -> {
            try {
                String jsonKey = FirebaseJsonUtil.createFirebaseJson(
                        firebaseSetting.getProjectId(),
                        firebaseSetting.getPrivateKey().replace("\\n", "\n"),
                        firebaseSetting.getClientEmail()
                );

                FirebaseOptions options = FirebaseOptions.builder()
                        .setCredentials(GoogleCredentials.fromStream(new ByteArrayInputStream(jsonKey.getBytes())))
                        .build();

                // Check if Firebase is already initialized
                if (!isFirebaseInitialized(firebaseSetting1.getId())) {
                    FirebaseApp.initializeApp(options, "app-" + firebaseSetting1.getId());
                }

                return Mono.empty();
            } catch (IOException e) {
                return Mono.error(new RuntimeException("Failed to initialize Firebase", e));
            }
        });
    }

    /**
     * Checks if Firebase has already been initialized for a given config ID.
     */
    private boolean isFirebaseInitialized(UUID settingId) {
        return FirebaseApp.getApps().stream()
                .anyMatch(app -> app.getName().equals("app-" + settingId));
    }
}
