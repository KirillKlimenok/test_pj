package com.any.name.config;

import com.any.name.ConfigurationJwt;
import com.any.name.model.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Base64;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class JwtUtil {
    private final ConfigurationJwt conf;

    public String extractUserName(String authToken) {
        return getClaims(authToken)
                .getSubject();
    }

    public boolean validateToken(String authToken) {
        return getClaims(authToken)
                .getExpiration()
                .before(new Date());
    }


    public Claims getClaims(String authToken) {
        String key = Base64
                .getEncoder()
                .encodeToString(conf.getSecret().getBytes());

        return Jwts
                .parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(authToken)
                .getBody();
    }

    public String generateToken(User user) {
        Map<String, Object> claims = Map
                .of("role", List.of(user.getRole()));

        Date currentDate = new Date();
        Date expDate = new Date(currentDate.getTime() + conf.getExp() * 1000);

        return Jwts.builder()
                .setClaims(claims)
                .setSubject(user.getUsername())
                .setIssuedAt(currentDate)
                .setExpiration(expDate)
                .signWith(Keys.hmacShaKeyFor(conf.getSecret().getBytes()))
                .compact();
    }
}
