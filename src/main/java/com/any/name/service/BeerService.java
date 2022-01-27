package com.any.name.service;

import com.any.name.model.Beer;
import com.any.name.repo.BeerRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class BeerService {
    private final BeerRepo beerRepo;

    public Flux<Beer> list(){
        return beerRepo.findAll();
    }

    public Mono<Beer> save(Beer beer){
        return beerRepo.save(beer);
    }

    public Mono<Beer> findById(Long id){
        return beerRepo.findById(id);
    }
}
