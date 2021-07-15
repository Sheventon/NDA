package ru.itis.usersservice.services;

import org.springframework.stereotype.Component;
import ru.itis.usersservice.exceptions.ConfirmMaliException;
import ru.itis.usersservice.models.ConfirmCode;
import ru.itis.usersservice.models.User;
import ru.itis.usersservice.repositories.ConfirmCodeRepository;
import ru.itis.usersservice.repositories.UserRepository;

/**
 * created: 14-07-2021 - 15:06
 * project: NDA
 *
 * @author dinar
 * @version v0.1
 */
@Component
public class ConfirmMailServiceImpl implements ConfirmMailService {

    private final UserRepository userRepository;

    private final ConfirmCodeRepository codeRepository;

    public ConfirmMailServiceImpl(UserRepository userRepository,
                                  ConfirmCodeRepository codeRepository) {
        this.userRepository = userRepository;
        this.codeRepository = codeRepository;
    }

    @Override
    public void confirmMail(String code) throws ConfirmMaliException {
        ConfirmCode confirmCode = codeRepository.getByCode(code)
                .orElseThrow(() -> new ConfirmMaliException("Email already confirmed"));

        User user = userRepository.findById(confirmCode.getUser().getId())
                .orElseThrow();

        user.setEmailState(User.EmailState.CONFIRMED);
        user.setConfirmCode(null);
        userRepository.save(user);

        codeRepository.delete(confirmCode);
    }

}
