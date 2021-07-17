package ru.itis.authservice.services;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.itis.authservice.dto.EmailAndPasswordLoginDto;
import ru.itis.authservice.dto.TokenPairDto;
import ru.itis.authservice.exeptions.AuthenticationException;
import ru.itis.authservice.models.UserAuth;
import ru.itis.authservice.repositories.UserAuthRepository;

/**
 * created: 16-07-2021 - 12:36
 * project: NDA
 *
 * @author dinar
 * @version v0.1
 */
@Service
public class LoginServiceImpl implements LoginService {

    private final UserAuthRepository userAuthRepository;

    private final TokenService tokenService;

    private final PasswordEncoder passwordEncoder;

    public LoginServiceImpl(UserAuthRepository userAuthRepository,
                            TokenService tokenService, PasswordEncoder passwordEncoder) {
        this.userAuthRepository = userAuthRepository;
        this.tokenService = tokenService;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public TokenPairDto login(EmailAndPasswordLoginDto loginDto) {
        UserAuth userAuth = userAuthRepository
                .getUserByEmailAndPassword(loginDto.getEmail())
                .orElseThrow(() -> new AuthenticationException("Incorrect email or password"));

        if (!passwordEncoder.matches(loginDto.getPassword(),
                userAuth.getPassword())) {
            throw new AuthenticationException("Incorrect email or password");
        }

        return tokenService.generateTokenPair(userAuth);
    }

}
