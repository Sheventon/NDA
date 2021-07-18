package ru.itis.security.provider;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import ru.itis.security.authentication.JwtAuthentication;
import ru.itis.security.details.UserDetailsImpl;
;

import java.util.Map;

/**
 * created: 17-07-2021 - 17:50
 * project: NDA
 *
 * @author dinar
 * @version v0.1
 */
@Component
public class JwtAuthenticationProvider implements AuthenticationProvider {

    @Value("${auth.jwt.secret-key}")
    private String secretKey;

    @Value("${auth.jwt.aud}")
    private String aud;

    @Value("${auth.jwt.iss}")
    private String iss;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String token = authentication.getName();

        JwtAuthentication jwtAuthentication = new JwtAuthentication(token);
        try {
            DecodedJWT decodedJWT = decode(token);
            if (decodedJWT.getIssuer().equals(iss) &&
                    decodedJWT.getAudience().contains(aud)) {
                Map<String, Claim> claims = decodedJWT.getClaims();

                UserDetails userDetails = UserDetailsImpl.builder()
                        .id(Long.valueOf(decodedJWT.getSubject()))
                        .email(claims.get("email").asString())
                        .authorities(claims.get("roles").asList(String.class))
                        .token(token)
                        .build();

                jwtAuthentication.setAuthenticated(true);
                jwtAuthentication.setUserDetails(userDetails);
            }
        } catch (JWTVerificationException e) {
            jwtAuthentication = null;
        }
        return jwtAuthentication;
    }

    private DecodedJWT decode(String token) {
        JWTVerifier verifier = JWT.require(Algorithm.HMAC512(secretKey))
                .build();

        return verifier.verify(token);
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return JwtAuthentication.class.isAssignableFrom(authentication);
    }

}
