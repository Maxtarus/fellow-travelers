package ru.sber.fellow_travelers.security.service;

import io.jsonwebtoken.*;
import java.util.Date;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;
import ru.sber.fellow_travelers.entity.User;
import ru.sber.fellow_travelers.security.configuration.JwtConfig;

@Component
public class JwtProvider {
    private static final Logger LOGGER = LogManager.getLogger(JwtProvider.class);
    private final JwtConfig jwt;

    public JwtProvider(JwtConfig jwt) {
        this.jwt = jwt;
    }

    public String getJwtFromHeader(HttpServletRequest request) {
        for (Cookie cookie : request.getCookies()) {
            if (cookie.getName().equals(jwt.getHeaderName())) {
                return cookie.getValue();
            }
        }
        return null;
    }

    public String getEmailFromJwtToken(String token) {
        return Jwts.parser()
                .setSigningKey(jwt.getSecretKey())
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    public boolean validateJwtToken(String authToken) {
        try {
            Jwts.parser().setSigningKey(jwt.getSecretKey()).parseClaimsJws(authToken);
            return true;
        } catch (ExpiredJwtException expEx) {
            LOGGER.error("Token expired", expEx);
        } catch (UnsupportedJwtException unsEx) {
            LOGGER.error("Unsupported jwt", unsEx);
        } catch (MalformedJwtException mjEx) {
            LOGGER.error("Malformed jwt", mjEx);
        } catch (SignatureException sEx) {
            LOGGER.error("Invalid signature", sEx);
        } catch (Exception e) {
            LOGGER.error("Invalid token", e);
        }
        return false;

    }

    public String generateTokenFromEmail(String email) {
        return Jwts.builder()
                .setSubject(email)
                .setIssuedAt(new Date())
                .signWith(SignatureAlgorithm.HS512, jwt.getSecretKey())
                .compact();
    }

    public Cookie getAuthorizeCookie(User user) {
        String jwtToken = generateTokenFromEmail(user.getUsername());
        return getCookie(jwt.getHeaderName(), jwtToken, Math.toIntExact(jwt.getExpiration() / 1000));
    }

    public Cookie getCookie(String name, String value, int expiry) {
        Cookie cookie = new Cookie(name, value);
        cookie.setPath("/");
        cookie.setHttpOnly(true);
        cookie.setSecure(true);
        cookie.setMaxAge(expiry);
        return cookie;
    }
}