package com.any.name.service;

import com.any.name.repo.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.ReactiveUserDetailsService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class UserService implements ReactiveUserDetailsService {
    private final UserRepo userRepo;

    @Override
    public Mono<UserDetails> findByUsername(String username) {
        return userRepo
                .findByUsername(username).log()
                .cast(UserDetails.class).log();
    }
}
