package ru.itis.usersservice.services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.itis.usersservice.dto.SignUpDto;
import ru.itis.usersservice.dto.UserDto;
import ru.itis.usersservice.models.ConfirmCode;
import ru.itis.usersservice.models.Role;
import ru.itis.usersservice.models.User;
import ru.itis.usersservice.repositories.ConfirmCodeRepository;
import ru.itis.usersservice.repositories.RoleRepository;
import ru.itis.usersservice.repositories.UserRepository;
import ru.itis.usersservice.util.ConfirmCodeGenerator;
import ru.itis.usersservice.util.MailMessageGenerator;
import ru.itis.usersservice.util.MailUtil;

import java.util.Collections;

/**
 * created: 12-07-2021 - 13:50
 * project: NDA
 *
 * @author dinar
 * @version v0.1
 */
@Service
public class SignUpServiceImpl implements SignUpService {

    private final UserRepository userRepository;

    private final RoleRepository roleRepository;

    private final ConfirmCodeRepository codeRepository;

    private final PasswordEncoder passwordEncoder;

    private final MailUtil mailUtil;

    private final MailMessageGenerator mailMessageGenerator;

    private final ConfirmCodeGenerator codeGenerator;

    @Value("${mail.confirm-mail.template}")
    private String templatePath;

    @Value("${spring.mail.username}")
    private String senderMail;

    @Value("${mail.confirm-mail.subject}")
    private String mailSubject;

    public SignUpServiceImpl(UserRepository userRepository,
                             RoleRepository roleRepository,
                             ConfirmCodeRepository codeRepository,
                             PasswordEncoder passwordEncoder,
                             MailUtil mailUtil,
                             MailMessageGenerator mailMessageGenerator,
                             ConfirmCodeGenerator codeGenerator) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.codeRepository = codeRepository;
        this.passwordEncoder = passwordEncoder;
        this.mailUtil = mailUtil;
        this.mailMessageGenerator = mailMessageGenerator;
        this.codeGenerator = codeGenerator;
    }

    @Override
    public UserDto signUp(SignUpDto userDto) {
        Role role = roleRepository.findByRole(Role.RoleValue.USER)
                .orElseThrow();

        ConfirmCode code = ConfirmCode.builder()
                .code(codeGenerator.generateConfirmCode(userDto.getEmail()))
                .build();

        User newUser = User.builder()
                .firstName(userDto.getFirstName())
                .lastName(userDto.getLastName())
                .email(userDto.getEmail())
                .phone(userDto.getPhone())
                .roles(Collections.singletonList(role))
                .state(User.UserState.ACTIVE)
                .emailState(User.EmailState.NOT_CONFIRMED)
                .password(passwordEncoder
                        .encode(userDto.getPassword()))
                .confirmCode(code)
                .build();

        role.getUsers().add(newUser);
        code.setUser(newUser);

        sendConfirmMessage(userDto.getEmail(), code.getCode());

        return UserDto.from(userRepository.save(newUser));
    }

    private void sendConfirmMessage(String email, String confirmCode) {
        String message = mailMessageGenerator
                .getMailForConfirm(confirmCode, templatePath);
        mailUtil.sendHtmlMail(email, senderMail, mailSubject, message);
    }

}

