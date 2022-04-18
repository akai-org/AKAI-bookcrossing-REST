package pl.akai.bookcrossing.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.Map;

@Service
public class JwtBuilder {

    private static final int HOUR = 3600000;
    private final SecretKeySpec secretKey;
    private final String issuer;

    public JwtBuilder(@Value("${spring.jwt.secret}") String secret,
                      @Value("${spring.application.name}") String applicationName) {
        this.secretKey = new SecretKeySpec(secret.getBytes(StandardCharsets.UTF_8), SignatureAlgorithm.HS512.getJcaName());
        this.issuer = applicationName;
    }

    public String getToken(String subject, Map<String, Object> claims) {
        return Jwts.builder()
                   .setIssuer(issuer)
                   .setSubject(subject)
                   .setIssuedAt(new Date())
                   .setExpiration(new Date(System.currentTimeMillis() + HOUR))
                   .addClaims(claims)
                   .signWith(secretKey)
                   .compact();
    }
}
