package ru.itis.usersservice.services;

import org.springframework.stereotype.Service;
import ru.itis.usersservice.dto.UpdateUserDto;
import ru.itis.usersservice.dto.UserDto;
import ru.itis.usersservice.exceptions.UserNotFoundException;
import ru.itis.usersservice.models.User;
import ru.itis.usersservice.repositories.UserRepository;

import java.util.List;

/**
 * created: 11-07-2021 - 15:56
 * project: users-service
 *
 * @author dinar
 * @version v0.1
 */
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User getByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new UserNotFoundException(
                        String.format("User not found. Incorrect email {%s}", email)));
    }

    @Override
    public List<UserDto> getAll() {
        return UserDto.from(userRepository.findAll());
    }

    @Override
    public UserDto getById(Long id) {
        return UserDto.from(userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(
                        String.format("User not found. Incorrect phone number {%d}", id))));
    }

    @Override
    public void deleteById(Long id) {
        /* TODO: Implement correct Many To Many Delete operation
            @author: Dinar */
        userRepository.deleteById(id);
    }

    @Override
    public UserDto updateById(UpdateUserDto userDto) {
        User persistUser = userRepository.findById(userDto.getId())
                .orElseThrow(() -> new UserNotFoundException(
                        String.format("User not found. Incorrect phone number {%d}", userDto.getId())));

        persistUser.setFirstName(userDto.getFirstName());
        persistUser.setLastName(userDto.getLastName());
        persistUser.setEmail(userDto.getEmail());
        persistUser.setPhone(userDto.getPhone());

        return UserDto.from(userRepository.save(persistUser));
    }

    @Override
    public UserDto getByPhoneNumber(String phone) {
        return UserDto.from(userRepository.findByPhone(phone)
                .orElseThrow(() -> new UserNotFoundException(
                        String.format("User not found. Incorrect phone number {%s}", phone))));
    }

    @Override
    public boolean emailIsExists(String email) {
        return userRepository.findByEmail(email).orElse(null) != null;
    }

    @Override
    public boolean phoneIsExists(String phone) {
        return userRepository.findByPhone(phone).orElse(null) != null;
    }

}
