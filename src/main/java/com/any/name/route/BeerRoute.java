package com.any.name.route;

import com.any.name.model.Beer;
import com.any.name.service.BeerService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

@Configuration
@RequiredArgsConstructor
public class BeerRoute {
    private final BeerService beerService;

    @Bean
    public RouterFunction<ServerResponse> route() {
        return RouterFunctions
                .route(RequestPredicates.GET("/beers")
                                .and(RequestPredicates.accept(MediaType.APPLICATION_JSON)),
                        request -> ServerResponse
                                .ok()
                                .body(beerService.list(), Beer.class))
                .andRoute(RequestPredicates.POST("/beers"), request ->
                        ServerResponse
                                .ok()
                                .body(request
                                        .bodyToMono(Beer.class).log()
                                        .map(beerService::save), Beer.class).log());
    }
}
