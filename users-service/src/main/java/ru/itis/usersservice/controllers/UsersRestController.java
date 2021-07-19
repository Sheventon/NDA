package ru.itis.usersservice.controllers;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import ru.itis.security.details.UserDetailsImpl;
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
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<List<UserDto>> getAllUsers(@AuthenticationPrincipal UserDetailsImpl userDetails) {
        System.out.println(userDetails);
        return ResponseEntity.ok(userService.getAll());
    }

    @GetMapping("/users/{id}/get")
    @ApiOperation(value = "Get user by id",
            response = UserDto.class)
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<UserDto> getUserById(@PathVariable Long id) {
        return ResponseEntity.ok(userService.getById(id));
    }

    @PutMapping("/users/update")
    @ApiOperation(value = "Update user info",
            response = UserDto.class)
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<UserDto> updateUserById(@RequestBody final UpdateUserDto userDto) {
        return ResponseEntity.ok(userService.updateById(userDto));
    }

    @DeleteMapping("/users/delete")
    @ApiOperation(value = "Delete user by id")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<?> deleteUserById(@PathVariable Long id) {
        userService.deleteById(id);
        return ResponseEntity.ok().build();
    }

}
