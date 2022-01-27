package com.any.name.repo;

import com.any.name.model.Beer;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface BeerRepo extends ReactiveCrudRepository<Beer, Long> {
    Mono<Beer> findById(Long id);
    Flux<Beer> findByName(String name);
}
