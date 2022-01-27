package com.any.name.config;

import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Component
@RequiredArgsConstructor
public class AuthenticationManager implements ReactiveAuthenticationManager {
    private final JwtUtil jwtUtil;

    @Override
    public Mono<Authentication> authenticate(Authentication authentication) {
        String authToken = authentication.getCredentials().toString();
        String userName;
        try {
            userName = jwtUtil.extractUserName(authToken);
        } catch (Exception e) {
            userName = null;
            log.info(e.getMessage());
        }

        if (userName == null && jwtUtil.validateToken(authToken)) {
            return Mono.empty();
        }

        Claims claims = jwtUtil.getClaims(authToken);
        List<String> role = claims.get("role", List.class);
        List<SimpleGrantedAuthority> authorities = role
                .stream()
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());

        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                userName,
                null,
                authorities
        );
        return Mono.just(authenticationToken);
    }
}
