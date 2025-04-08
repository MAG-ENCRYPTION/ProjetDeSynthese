package inc.yowyob.service.notification.application.service.impl;

import inc.yowyob.service.notification.application.service.TemplateMessageBuilderService;
import inc.yowyob.service.notification.infrastructure.persistence.entities.DesignTemplate;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.StringTemplateResolver;
import reactor.core.publisher.Mono;

import java.util.Map;

@Service
@RequiredArgsConstructor
@Slf4j
public class TemplateMessageBuilderServiceImpl implements TemplateMessageBuilderService {

    @Override
    public Mono<String> build(String subject, Map<String, String> variables, DesignTemplate designTemplate) {
        Context context = this.createContext(subject, variables);
        return Mono.just(buildBody(designTemplate, context));
    }

    private String buildBody(DesignTemplate designTemplate, Context context) {
        SpringTemplateEngine templateEngine = new SpringTemplateEngine();

        StringTemplateResolver resolver = new StringTemplateResolver();
        resolver.setTemplateMode(TemplateMode.HTML);
        resolver.setCacheable(false);
        templateEngine.setTemplateResolver(resolver);

        return templateEngine.process(designTemplate.getHtml(), context);
    }

    private Context createContext(String subject, Map<String, String> variables) {
        Context context = new Context();

        for (Map.Entry<String, String> entry : variables.entrySet()) {
            context.setVariable(entry.getKey().toUpperCase(), entry.getValue());
        }

        context.setVariable("SUBJECT", subject);

        return context;
    }
}
