package ru.itis.authservice.services;

import org.springframework.stereotype.Service;
import ru.itis.authservice.dto.TokenPairDto;
import ru.itis.authservice.models.UserAuth;
import ru.itis.authservice.security.JwtConstants;
import ru.itis.authservice.security.JwtTokenProvider;
import ru.itis.authservice.security.RefreshTokenProvider;

/**
 * created: 16-07-2021 - 14:10
 * project: NDA
 *
 * @author dinar
 * @version v0.1
 */
@Service
public class TokenServiceImpl implements TokenService {

    private final JwtTokenProvider jwtTokenProvider;

    private final RefreshTokenProvider refreshTokenProvider;

    public TokenServiceImpl(JwtTokenProvider jwtTokenProvider,
                            RefreshTokenProvider refreshTokenProvider) {
        this.jwtTokenProvider = jwtTokenProvider;
        this.refreshTokenProvider = refreshTokenProvider;
    }

    @Override
    public TokenPairDto generateTokenPair(UserAuth userAuth) {
        Long millis = System.currentTimeMillis();

        return TokenPairDto.builder()
                .access(jwtTokenProvider.generate(userAuth))
                .refresh(refreshTokenProvider.generate(userAuth))
                .expiration(millis + JwtConstants.EXPIRATION_TIME)
                .build();
    }

}
