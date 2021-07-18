package ru.itis.authservice.services;

import ru.itis.authservice.dto.TokenPairDto;
import ru.itis.authservice.models.UserAuth;

/**
 * created: 16-07-2021 - 14:10
 * project: NDA
 *
 * @author dinar
 * @version v0.1
 */
public interface TokenService {

    TokenPairDto generateTokenPair(UserAuth userAuth);

}
