package inc.yowyob.service.notification.application.service.impl;

import inc.yowyob.service.notification.application.service.DesignTemplateService;
import inc.yowyob.service.notification.application.service.MessageBuilderService;
import inc.yowyob.service.notification.application.service.TemplateMessageBuilderService;
import inc.yowyob.service.notification.application.service.TemplateService;
import inc.yowyob.service.notification.infrastructure.persistence.entities.Template;
import inc.yowyob.service.notification.infrastructure.persistence.entities.keys.TemplateKey;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import reactor.core.publisher.Mono;

import java.util.Map;

@Service
@RequiredArgsConstructor
@Slf4j
public class MessageBuilderServiceImpl implements MessageBuilderService {

    private final TemplateEngine templateEngine;

    private final TemplateService templateService;

    private final DesignTemplateService designTemplateService;

    private final TemplateMessageBuilderService templateMessageBuilderService;

    @Override
    public Mono<String> build(String subject, Map<String, String> variables, TemplateKey templateKey) {
        return templateService.getTemplateById(templateKey.getOrganizationId(), templateKey.getId()).flatMap(template -> {
            return designTemplateService.getDesignTemplateByIdOrDefault(template.getOrganizationId(), template.getDesignTemplateId()).flatMap(designTemplate -> {
                return this.templateMessageBuilderService.build(subject, variables, designTemplate);
            });
        });
    }
}
