package com.bbjM.task.config.spring_security;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.time.Duration;

@ConfigurationProperties(prefix = "security.jwt")
public record CredentialJwtProperties(String secret, String issuer, Duration expiration) {}
