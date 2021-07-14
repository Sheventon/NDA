package ru.itis.usersservice.services;

import ru.itis.usersservice.dto.UpdateUserDto;
import ru.itis.usersservice.dto.UserDto;
import ru.itis.usersservice.models.User;

import java.util.List;

/**
 * created: 11-07-2021 - 15:43
 * project: NDA
 *
 * @author dinar
 * @version v0.1
 */
public interface UserService {

    User getByEmail(String email);

    List<UserDto> getAll();

    UserDto getById(Long id);

    void deleteById(Long id);

    UserDto updateById(UpdateUserDto userDto);

    UserDto getByPhoneNumber(String phone);
}
