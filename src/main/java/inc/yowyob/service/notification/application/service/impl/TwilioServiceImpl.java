package inc.yowyob.service.notification.application.service.impl;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import inc.yowyob.service.notification.application.service.TwilioService;
import inc.yowyob.service.notification.application.service.TwilioSettingService;
import inc.yowyob.service.notification.infrastructure.persistence.entities.SmsNotification;
import inc.yowyob.service.notification.infrastructure.persistence.entities.Template;
import inc.yowyob.service.notification.infrastructure.persistence.entities.TwilioSetting;
import inc.yowyob.service.notification.infrastructure.persistence.entities.WhatsappNotification;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
@Service
@Slf4j
public class TwilioServiceImpl implements TwilioService {

    private final TwilioSettingService twilioSettingService;

    @Override
    public Mono<Void> sendSmsNotification(SmsNotification smsNotification, Template template) {
        return twilioSettingService.getTwilioSettingByIdOrDefault(template.getOrganizationId(), template.getSettingId()).flatMapMany(twilioSetting -> {
            return Flux.fromIterable(smsNotification.getRecipients()).flatMap(toPhoneNumber -> {
                this.initTwilio(twilioSetting);
                Message message = sendSms(twilioSetting, smsNotification.getSenderPhoneNumber(), toPhoneNumber, smsNotification.getBody());
                log.info("Message sent with SID: {}", message.getSid());
                return Mono.just(message);
            });
        }).then();
    }

    @Override
    public Mono<Void> sendWhatsappNotification(WhatsappNotification whatsappNotification, Template template) {
        return twilioSettingService.getTwilioSettingByIdOrDefault(template.getOrganizationId(), template.getSettingId()).flatMapMany(twilioSetting -> {
            return Flux.fromIterable(whatsappNotification.getRecipients()).flatMap(toPhoneNumber -> {
                this.initTwilio(twilioSetting);
                Message message = sendWhatsapp(twilioSetting, whatsappNotification.getSenderPhoneNumber(), toPhoneNumber, whatsappNotification.getBody());
                log.info("Message sent with SID: {}", message.getSid());
                return Mono.just(message);
            });
        }).then();
    }

    private void initTwilio(TwilioSetting twilioSetting){
        Twilio.init(twilioSetting.getAccountId(), twilioSetting.getAuthToken());
    }

    private Message sendSms(TwilioSetting twilioSetting, String fromPhoneNumber, String toPhoneNumber, String body) {
        return Message.creator(
                twilioSetting.getAccountId(),
                new PhoneNumber(fromPhoneNumber),
                new PhoneNumber(toPhoneNumber),
                body
        ).create();
    }

    private Message sendWhatsapp(TwilioSetting twilioSetting, String fromPhoneNumber, String toPhoneNumber, String body) {
        return Message.creator(
                twilioSetting.getAccountId(),
                new PhoneNumber("whatsapp:" + fromPhoneNumber),
                new PhoneNumber("whatsapp:" + toPhoneNumber),
                body
        ).create();
    }
}
