package com.bbjM.task.config.spring_security;

import com.nimbusds.jose.jwk.source.ImmutableSecret;
import lombok.extern.log4j.Log4j2;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.jose.jws.MacAlgorithm;
import org.springframework.security.oauth2.jwt.*;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;

@Configuration
@Log4j2
public class JwtConfig {
    @Bean
    SecretKey jwtKey(CredentialJwtProperties props) {
        log.info("[start] JwtConfig - jwtKey");
        byte[] bytes = props.secret().getBytes(StandardCharsets.UTF_8);
        if (bytes.length < 32) throw new IllegalArgumentException("O segredo do JWT tem que ser maior que 32 bytes");
        SecretKey key = new SecretKeySpec(bytes, "HmacSHA256");
        log.debug("[finish] JwtConfig - jwtKey");
        return key;
    }

    @Bean
    JwtDecoder jwtDecoder(SecretKey secretKey, CredentialJwtProperties props) {
        log.info("[start] JwtConfig - jwtDecoder");
        NimbusJwtDecoder decoder = NimbusJwtDecoder.withSecretKey(secretKey).macAlgorithm(MacAlgorithm.HS256).build();
        decoder.setJwtValidator(JwtValidators.createDefaultWithIssuer(props.issuer()));
        log.debug("[finish] JwtConfig - jwtDecoder");
        return decoder;
    }

    @Bean
    JwtEncoder jwtEncoder(SecretKey secretKey) {
        log.info("[start] JwtConfig - jwtEncoder");
        JwtEncoder encoder = new NimbusJwtEncoder(new ImmutableSecret<>(secretKey));
        log.debug("[finish] JwtConfig - jwtEncoder");
        return encoder;
    }
}
