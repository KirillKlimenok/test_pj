package com.any.name.route;

import com.any.name.hendler.GreetingHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

@Configuration
public class GreetingRouter {

    @Bean
    public RouterFunction<ServerResponse> greetingRoute(GreetingHandler greetingHandler) {
        return RouterFunctions
                .route(
                        RequestPredicates.GET("/"),
                        greetingHandler::index
                );
    }
}
