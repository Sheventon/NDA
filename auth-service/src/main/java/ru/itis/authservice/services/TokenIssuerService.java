package ru.itis.authservice.services;

import ru.itis.authservice.models.UserAuth;

/**
 * created: 17-07-2021 - 16:13
 * project: NDA
 *
 * @author dinar
 * @version v0.1
 */
public interface TokenIssuerService {

    String authenticate(String token);

}
