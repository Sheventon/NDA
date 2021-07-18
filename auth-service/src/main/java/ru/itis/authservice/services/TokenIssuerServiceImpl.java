package ru.itis.authservice.services;

import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.itis.authservice.models.UserAuth;
import ru.itis.authservice.security.JwtConstants;
import ru.itis.authservice.security.JwtTokenProvider;

import java.util.Map;

/**
 * created: 17-07-2021 - 16:15
 * project: NDA
 *
 * @author dinar
 * @version v0.1
 */
@Service
public class TokenIssuerServiceImpl implements TokenIssuerService {

    @Value("${auth.jwt.iss}")
    private String iss;

    @Value("#{'${auth.jwt.aud}'.split(',')}")
    private String[] aud;

    private final JwtTokenProvider jwtTokenProvider;

    public TokenIssuerServiceImpl(JwtTokenProvider jwtTokenProvider) {
        this.jwtTokenProvider = jwtTokenProvider;
    }


    /**
    * Method authenticate return new jwt token if token is valid
    * If Jwt is not valid, will throws JWTVerificationException
    * */
    @Override
    public String authenticate(String token) {
        DecodedJWT decodedJWT = jwtTokenProvider.validate(token);

        Map<String, Claim> claims = decodedJWT.getClaims();
        return jwtTokenProvider.generate(UserAuth.builder()
                .id(Long.valueOf(decodedJWT.getSubject()))
                .roles(claims.get(JwtConstants.ROLES).asList(String.class))
                .email(claims.get(JwtConstants.EMAIL).asString())
                .build(),
                iss,
                aud);
    }

}
