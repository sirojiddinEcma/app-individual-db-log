package uz.pdp.applog.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;
import uz.pdp.applog.entity.User;

import java.util.Date;
import java.util.UUID;

/**
 * BY SIROJIDDIN on 28.12.2020
 */


@Component
public class JwtProvider {
    public String generateToken(User user) {
        String token = Jwts
                .builder()
                .setExpiration(new Date(new Date().getTime() + 86400000))
                .setIssuedAt(new Date())
                .setSubject(user.getId().toString())
                .signWith(SignatureAlgorithm.HS512, "KalitSuz")
                .compact();
        return token;
    }

    public UUID getUserIdFromToken(String token) {
        try {
            String userId = Jwts
                    .parser()
                    .setSigningKey("KalitSuz")
                    .parseClaimsJws(token)
                    .getBody().getSubject();
            return UUID.fromString(userId);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
