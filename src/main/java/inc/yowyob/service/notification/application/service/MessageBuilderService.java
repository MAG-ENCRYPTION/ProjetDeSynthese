package inc.yowyob.service.notification.application.service;

import inc.yowyob.service.notification.infrastructure.persistence.entities.keys.TemplateKey;
import reactor.core.publisher.Mono;

import java.util.Map;

public interface MessageBuilderService {
    public Mono<String> build(String subject, Map<String, String> variables, TemplateKey templateKey);
}
