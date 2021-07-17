package ru.itis.authservice.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import ru.itis.authservice.models.UserAuth;

import java.util.ArrayList;
import java.util.Date;

/**
 * created: 16-07-2021 - 14:26
 * project: NDA
 *
 * @author dinar
 * @version v0.1
 */
@Component
public class JwtTokenProviderImpl implements JwtTokenProvider {

    @Value("${auth.jwt.secret-key}")
    private String secretKey;

    @Override
    public String generate(UserAuth user) {
        return JWT.create()
                .withSubject(String.valueOf(user.getId()))
                .withClaim(JwtConstants.ROLES, new ArrayList<>(user.getRoles()))
                .withClaim(JwtConstants.EMAIL, user.getEmail())
                .withIssuedAt(new Date())
                .withExpiresAt(new Date(System.currentTimeMillis()
                        + JwtConstants.EXPIRATION_TIME))
                .sign(Algorithm.HMAC512(secretKey));
    }

    @Override
    public String generate(UserAuth user, String iss, String[] aud) {
        return JWT.create()
                .withSubject(String.valueOf(user.getId()))
                .withClaim(JwtConstants.ROLES, new ArrayList<>(user.getRoles()))
                .withClaim(JwtConstants.EMAIL, user.getEmail())
                .withIssuer(iss)
                .withAudience(aud)
                .withIssuedAt(new Date())
                .withExpiresAt(new Date(System.currentTimeMillis()
                        + JwtConstants.EXPIRATION_TIME))
                .sign(Algorithm.HMAC512(secretKey));
    }

    @Override
    public DecodedJWT validate(String token) {
        JWTVerifier verifier = JWT.require(Algorithm.HMAC512(secretKey))
                .build();
        return verifier.verify(token);
    }

}
