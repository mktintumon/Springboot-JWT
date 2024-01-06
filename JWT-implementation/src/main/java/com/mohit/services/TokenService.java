package com.mohit.services;

import java.time.Instant;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;

@Service
public class TokenService {

    @Autowired
    private JwtEncoder jwtEncoder;

    //private static final long EXPIRATION_TIME_2_MINUTES_SECONDS = 120;
    // private static final long EXPIRATION_TIME_1_HOUR = 3600;

    private static final long EXPIRATION_TIME_7_DAYS_SECONDS = 604800; // 7 days * 24 hours * 60 minutes * 60 seconds


    public String generateJwt(Authentication auth , String name) {
        Instant now = Instant.now();
        Instant expirationTime = now.plusSeconds(EXPIRATION_TIME_7_DAYS_SECONDS);

        String subject = auth.getName() + "-" + name;

        String scope = auth.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(" "));

        JwtClaimsSet claims = JwtClaimsSet.builder()
                .issuer("self")
                .issuedAt(now)
                .subject(subject)
                .claim("roles", scope)
                .expiresAt(expirationTime)
                .build();

        return jwtEncoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();
    }
}
