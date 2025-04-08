package inc.yowyob.service.notification.application.service;

import inc.yowyob.service.notification.infrastructure.persistence.entities.DesignTemplate;
import inc.yowyob.service.notification.infrastructure.persistence.entities.Template;
import reactor.core.publisher.Mono;

import java.util.Map;

public interface TemplateMessageBuilderService {
    public Mono<String> build(String subject, Map<String, String> variables, DesignTemplate designTemplate);
}
