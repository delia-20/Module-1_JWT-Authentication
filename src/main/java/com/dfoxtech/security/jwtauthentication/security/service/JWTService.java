package com.dfoxtech.security.jwtauthentication.security.service;

import com.dfoxtech.security.jwtauthentication.users.entity.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.security.SecureRandom;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JWTService {
    private static final String SECRET_KEY="a-string-secret-at-least-256-bits-long";
//    static {
//        byte[] randomBytes = new byte[32];
//        new SecureRandom().nextBytes(randomBytes);
//        SECRET_KEY= Base64.getEncoder().encodeToString(randomBytes);
//    }
    public  JWTService() {

    }
    public String generateToken(User user) {
        Map<String, Object> claims = new HashMap<String, Object>();
        claims.put("email", user.getEmail());
        claims.put("name", user.getFirstName());

        return Jwts.builder()
                .setClaims(claims)
                .setSubject(user.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis()+1000*60*10))
                .signWith(getSecretKey())
                .compact();
    }
    public Key getSecretKey() {
        return Keys.hmacShaKeyFor(SECRET_KEY.getBytes(StandardCharsets.UTF_8));
    }
    public String extractUsername(String token) {
        return extractClaims(token, Claims::getSubject);
        
    }
    public boolean isTokenValid(String token, UserDetails userDetails) {
        String username = extractUsername(token);
        return username.equals(userDetails.getUsername())
        && !isTokenExpired(token);
    }
    public boolean isTokenExpired(String token) {
        return extractClaims(token, Claims::getExpiration)
                .before(new Date());
    }

    private <T> T extractClaims(String token, Function<Claims, T> claimsResolver) {
        Claims claims=
                Jwts.parserBuilder()
                        .setSigningKey(getSecretKey())
                        .build()
                        .parseClaimsJws(token)
                        .getBody();
        return claimsResolver.apply(claims);
    }
}
