package ru.sber.fellow_travelers.security.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "app.jwt")
public class JwtConfig {
    private final String secretKey;
    private final Long expiration;
    private final String headerName;

    public JwtConfig(String secretKey, Long expiration, String headerName) {
        this.secretKey = secretKey;
        this.expiration = expiration;
        this.headerName = headerName;
    }

    public String getSecretKey() {
        return secretKey;
    }

    public Long getExpiration() {
        return expiration;
    }

    public String getHeaderName() {
        return headerName;
    }
}
