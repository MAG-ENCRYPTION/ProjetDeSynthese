package inc.yowyob.service.notification.infrastructure.config;


import inc.yowyob.starter.core.infrastructure.config.DefaultResourceServerConfig;
import inc.yowyob.starter.core.infrastructure.security.JwtAuthenticationManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.oauth2.jwt.ReactiveJwtDecoder;
import org.springframework.security.oauth2.server.resource.authentication.ReactiveJwtAuthenticationConverter;
import org.springframework.security.web.server.SecurityWebFilterChain;

/**
 * @author douglas
 */
@Configuration
@EnableWebFluxSecurity
public class SecurityConfig extends DefaultResourceServerConfig {

    @Autowired
    private ReactiveJwtAuthenticationConverter jwtAuthenticationConverter;

    @Autowired
    private ReactiveJwtDecoder jwtDecoder;

    @Autowired
    private JwtAuthenticationManager jwtAuthenticationManager;

    @Bean
    @Order(3)
    public SecurityWebFilterChain defaultSecurityFilterChain(ServerHttpSecurity http) throws Exception {

        http
                .csrf(ServerHttpSecurity.CsrfSpec::disable)
                .formLogin(ServerHttpSecurity.FormLoginSpec::disable)
                .authorizeExchange(exchanges -> exchanges
                        .pathMatchers(getDefaultWhitelistedRoutes()).permitAll()
                        .pathMatchers("/**").permitAll()
                ).oauth2ResourceServer(oauth2 -> oauth2
                        .jwt(jwt -> jwt.jwtDecoder(jwtDecoder)
                                .authenticationManager(jwtAuthenticationManager)
                                .jwtAuthenticationConverter(jwtAuthenticationConverter))
                ).exceptionHandling(exceptions -> exceptions
                        .authenticationEntryPoint((exchange, ex) -> {
                            exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
                            return exchange.getResponse().setComplete();
                        })
                        .accessDeniedHandler((exchange, denied) -> {
                            exchange.getResponse().setStatusCode(HttpStatus.FORBIDDEN);
                            return exchange.getResponse().setComplete();
                        })
                );

        return http.build();
    }

}
