package ru.itis.authservice.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.interfaces.DecodedJWT;
import ru.itis.authservice.models.UserAuth;

/**
 * created: 16-07-2021 - 14:21
 * project: NDA
 *
 * @author dinar
 * @version v0.1
 */
public interface JwtTokenProvider {

    String generate(UserAuth user);

    String generate(UserAuth user, String iss, String[] aud);

    DecodedJWT validate(String token);

}
