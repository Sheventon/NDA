package ru.itis.usersservice.services;

import ru.itis.usersservice.dto.SignUpDto;
import ru.itis.usersservice.dto.UserDto;

/**
 * created: 12-07-2021 - 13:49
 * project: NDA
 *
 * @author dinar
 * @version v0.1
 */
public interface SignUpService {

    UserDto signUp(SignUpDto userDto);

}
