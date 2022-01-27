package com.any.name.route;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Configuration
public class AdminRoute {

    @Bean
    public RouterFunction<ServerResponse> adminAction() {
        return RouterFunctions
                .route(RequestPredicates.GET("/admin")
                                .and(RequestPredicates.accept(MediaType.TEXT_PLAIN)),
                        request -> ServerResponse.ok()
                                .body(request
                                        .bodyToMono(String.class)
                                        .map(x -> "admin actions"), String.class)
                );
    }
}
