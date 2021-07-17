package ru.itis.authservice.security;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import ru.itis.authservice.exeptions.AuthenticationException;
import ru.itis.authservice.models.RefreshToken;
import ru.itis.authservice.models.UserAuth;
import ru.itis.authservice.repositories.RefreshTokenRepository;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * created: 16-07-2021 - 15:20
 * project: NDA
 *
 * @author dinar
 * @version v0.1
 */
@Component
public class RefreshTokenProviderImpl implements RefreshTokenProvider {

    /**
     * default expiration time is 1 month
     * */
    @Value("${auth.refresh.expiration-time:#{1000 * 60 * 60 * 30}}")
    private Long EXPIRATION_TIME;

    private final RefreshTokenRepository tokenRepository;

    public RefreshTokenProviderImpl(RefreshTokenRepository tokenRepository) {
        this.tokenRepository = tokenRepository;
    }

    @Override
    public String generate(UserAuth userAuth) {
        String token = UUID.nameUUIDFromBytes(
                (userAuth.getEmail() + System.currentTimeMillis()).getBytes())
                .toString();

        RefreshToken refreshToken = RefreshToken.builder()
                .userId(userAuth.getId())
                .token(token)
                .issuedAt(LocalDateTime.now())
                .expiredAt(LocalDateTime.now().plusSeconds(EXPIRATION_TIME / 1000))
                .build();

        tokenRepository.save(refreshToken);
        return token;
    }

    @Override
    public RefreshToken validate(String token) {
        RefreshToken refreshToken = tokenRepository.findById(token)
                .orElseThrow(() -> new AuthenticationException("invalid refresh token"));

        if (refreshToken.getExpiredAt().isBefore(LocalDateTime.now())) {
            throw new AuthenticationException("refresh token expired");
        }
        return refreshToken;
    }

}
