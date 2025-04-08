package inc.yowyob.service.notification;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.cassandra.config.EnableCassandraAuditing;
import org.springframework.web.reactive.config.EnableWebFlux;

@SpringBootApplication
@EnableDiscoveryClient
@EnableAutoConfiguration
@ComponentScan(basePackages = {
        "inc.yowyob.scylladb.*",
        "inc.yowyob.redis.*",
        "inc.yowyob.service.notification.*",
        "inc.yowyob.service.organization.*",
        "inc.yowyob.identity.*",
        "inc.yowyob.openapi.*",
        "inc.yowyob.starter.core.*",
        "inc.yowyob.service.auth.*",
        "inc.yowyob.utils.*",
})
@EnableCassandraAuditing
@RefreshScope
@EnableWebFlux
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}