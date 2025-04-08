package inc.yowyob.service.notification.application.service.impl;

import inc.yowyob.service.notification.application.service.MetaService;
import inc.yowyob.service.notification.application.service.MetaSettingService;
import inc.yowyob.service.notification.infrastructure.persistence.entities.MetaSetting;
import inc.yowyob.service.notification.infrastructure.persistence.entities.Template;
import inc.yowyob.service.notification.infrastructure.persistence.entities.WhatsappNotification;
import inc.yowyob.service.notification.utils.MetaJsonUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import okhttp3.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.io.IOException;

@RequiredArgsConstructor
@Service
@Slf4j
public class MetaServiceImpl implements MetaService {

    private final MetaSettingService metaSettingService;

    private final OkHttpClient client = new OkHttpClient();

    @Value("${api.facebook.url}")
    private String API_URL;


    @Override
    public Mono<Void> sendWhatsappNotification(WhatsappNotification whatsappNotification, Template template) {
        return metaSettingService.getMetaSettingByIdOrDefault(template.getOrganizationId(), template.getSettingId()).flatMapMany(metaSetting -> {
            return Flux.fromIterable(whatsappNotification.getRecipients()).flatMap(toPhoneNumber -> {
                try {
                    Response response = sendWhatsapp(metaSetting, toPhoneNumber, whatsappNotification.getBody());
                    log.info("Message sent with message: {}", response.message());
                    return Mono.just(response);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });
        }).then();
    }

    private String getUrl(String phoneNumberId) {
        return String.format("%s/%s/messages", API_URL, phoneNumberId);
    }

    private Response sendWhatsapp(MetaSetting metaSetting, String toPhoneNumber, String message) throws IOException {
        MediaType JSON = MediaType.get("application/json; charset=utf-8");

        String json = MetaJsonUtil.createTextMetaJson(toPhoneNumber, message);

        RequestBody body = RequestBody.create(json, JSON);

        Request request = new Request.Builder()
                .url(getUrl(metaSetting.getPhoneNumberId()))
                .header("Authorization", "Bearer " + metaSetting.getAccessToken())
                .post(body)
                .build();

        return client.newCall(request).execute();
    }
}
