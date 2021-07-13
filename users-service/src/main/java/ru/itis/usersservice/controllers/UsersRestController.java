package ru.itis.usersservice.controllers;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.itis.usersservice.dto.UpdateUserDto;
import ru.itis.usersservice.dto.UserDto;
import ru.itis.usersservice.services.UserService;

import java.util.List;

/**
 * UsersRestController
 * created: 11-07-2021 - 15:41
 * project: NDA
 *
 * @author dinar
 * @version v0.1
 */
@RestController
@Api(value = "User Rest Controller")
public class UsersRestController {

    private final UserService userService;

    public UsersRestController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping("/users/all/get")
    @ApiOperation(value = "Get all users")
    public ResponseEntity<List<UserDto>> getAllUsers() {
        return ResponseEntity.ok(userService.getAll());
    }

    @GetMapping("/users/{id}/get")
    @ApiOperation(value = "Get user by id",
            response = UserDto.class)
    public ResponseEntity<UserDto> getUserById(@PathVariable Long id) {
        return ResponseEntity.ok(userService.getById(id));
    }

    @PutMapping("/users/update")
    @ApiOperation(value = "Update user info",
            response = UserDto.class)
    public ResponseEntity<UserDto> updateUserById(@RequestBody final UpdateUserDto userDto) {
        return ResponseEntity.ok(userService.updateById(userDto));
    }

    @DeleteMapping("/users/{id}/delete")
    @ApiOperation(value = "Delete user by id")
    public ResponseEntity<?> deleteUserById(@PathVariable Long id) {
        userService.deleteById(id);
        return ResponseEntity.ok().build();
    }

}