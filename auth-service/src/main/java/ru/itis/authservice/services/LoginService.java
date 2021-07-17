package ru.itis.authservice.services;

import ru.itis.authservice.dto.EmailAndPasswordLoginDto;
import ru.itis.authservice.dto.TokenPairDto;

/**
 * created: 16-07-2021 - 12:32
 * project: NDA
 *
 * @author dinar
 * @version v0.1
 */
public interface LoginService {

    TokenPairDto login(EmailAndPasswordLoginDto loginDto);

}
