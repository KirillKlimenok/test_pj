package com.any.name.rest.impl;

import com.any.name.config.JwtUtil;
import com.any.name.model.User;
import com.any.name.rest.UserController;
import com.any.name.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.Objects;

@Configuration
@RequiredArgsConstructor
public class UserControllerImpl implements UserController {
    public static final ResponseEntity<Object> UNAUTHORIZED
            = ResponseEntity
            .status(HttpStatus.UNAUTHORIZED)
            .build();

    private final UserService userService;
    private final JwtUtil jwtUtil;


    @Override
    @PostMapping("/login")
    public Mono<ResponseEntity> login(ServerWebExchange swe) {
        return swe.getFormData().flatMap(credentials ->
                userService.findByUsername(credentials.getFirst("username")).log()
                        .cast(User.class)
                        .map(user -> {
                            String userRequestPassword = credentials.getFirst("password");
                            if (userRequestPassword.equals(user.getPassword())) {
                                return ResponseEntity.ok(jwtUtil.generateToken(user));
                            } else {
                                return UNAUTHORIZED;
                            }
                        }).log()
                        .defaultIfEmpty(UNAUTHORIZED)
        );
    }
}
