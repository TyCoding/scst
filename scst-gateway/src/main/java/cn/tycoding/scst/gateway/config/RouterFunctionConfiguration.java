package cn.tycoding.scst.gateway.config;

import cn.tycoding.scst.gateway.handler.SwaggerResourceHandler;
import cn.tycoding.scst.gateway.handler.SwaggerSecurityHandler;
import cn.tycoding.scst.gateway.handler.SwaggerUiHandler;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;

/**
 * @author tycoding
 * @date 2020/7/19
 */
@Slf4j
@Configuration
@RequiredArgsConstructor
public class RouterFunctionConfiguration {

    private final SwaggerResourceHandler swaggerResourceHandler;

    private final SwaggerSecurityHandler swaggerSecurityHandler;

    private final SwaggerUiHandler swaggerUiHandler;

    @Bean
    public RouterFunction routerFunction() {
        return RouterFunctions
                .route(RequestPredicates.GET("/swagger-resources").and(RequestPredicates.accept(MediaType.ALL)),
                        swaggerResourceHandler)
                .andRoute(RequestPredicates.GET("/swagger-resources/configuration/ui")
                        .and(RequestPredicates.accept(MediaType.ALL)), swaggerUiHandler)
                .andRoute(RequestPredicates.GET("/swagger-resources/configuration/security")
                        .and(RequestPredicates.accept(MediaType.ALL)), swaggerSecurityHandler);

    }

}
