package ru.itis.usersservice.services;

import org.springframework.stereotype.Service;
import ru.itis.usersservice.dto.SignUpDto;
import ru.itis.usersservice.dto.UserDto;
import ru.itis.usersservice.models.Role;
import ru.itis.usersservice.models.User;
import ru.itis.usersservice.repositories.RoleRepository;
import ru.itis.usersservice.repositories.UserRepository;

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

    public SignUpServiceImpl(UserRepository userRepository,
                             RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    @Override
    public UserDto signUp(SignUpDto userDto) {
        Role role = roleRepository.findByRole(Role.RoleValue.USER)
                .orElseThrow();
        User newUser = User.builder()
                .firstName(userDto.getFirstName())
                .lastName(userDto.getLastName())
                .email(userDto.getEmail())
                .phone(userDto.getPhone())
                .roles(Collections.singletonList(role))
                .state(User.UserState.ACTIVE)
                .emailState(User.EmailState.NOT_CONFIRMED)
                .build();
        role.getUsers().add(newUser);

        return UserDto.from(userRepository.save(newUser));
    }

}

