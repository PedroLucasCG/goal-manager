package com.bbjM.task.config.spring_security;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.jose.jws.MacAlgorithm;
import org.springframework.security.oauth2.jwt.JwsHeader;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
@Log4j2
public class TokenService {
    private final JwtEncoder jwtEncoder;
    private final CredentialJwtProperties credentialJwtProperties;

    public String generateToken(Authentication authentication) {
        log.info("[start] TokenService - generateToken");
        Instant now = Instant.now();
        List<String> roles = authentication.getAuthorities().stream()
                .map(a ->
                        Objects.requireNonNull(a.getAuthority()).replaceFirst("^ROLE_", ""))
                .toList();
        JwtClaimsSet jwtClaimsSet = JwtClaimsSet.builder()
                    .issuer(credentialJwtProperties.issuer())
                    .issuedAt(now)
                    .expiresAt(now.plus(credentialJwtProperties.expiration()))
                    .subject(authentication.getName())
                    .claim("roles", roles)
                    .build();

        JwsHeader jwsHeader = JwsHeader.with(MacAlgorithm.HS256).build();
        String token = jwtEncoder.encode(JwtEncoderParameters.from(jwsHeader, jwtClaimsSet)).getTokenValue();
        log.debug("[finish] TokenService - generateToken");
        return token;
    }
}
