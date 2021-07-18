package ru.itis.authservice.security;

import ru.itis.authservice.models.RefreshToken;
import ru.itis.authservice.models.UserAuth;

/**
 * created: 16-07-2021 - 15:19
 * project: NDA
 *
 * @author dinar
 * @version v0.1
 */
public interface RefreshTokenProvider {

    String generate(UserAuth userAuth);

    RefreshToken validate(String token);

}
