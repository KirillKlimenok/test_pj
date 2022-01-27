package com.any.name.repo;

import com.any.name.model.User;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Mono;

import java.util.UUID;

public interface UserRepo extends ReactiveCrudRepository<User, UUID> {
    Mono<User> findByUsername(String Username);
}
