package ru.itis.usersservice.controllers;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.itis.usersservice.dto.SignUpDto;
import ru.itis.usersservice.dto.UserDto;
import ru.itis.usersservice.services.SignUpService;

import javax.annotation.security.PermitAll;

/**
 * created: 12-07-2021 - 13:49
 * project: NDA
 *
 * @author dinar
 * @version v0.1
 */
@RestController
@Api(value = "Sign up Rest Controller",
        description = "Controller for creating a new User")
public class SignUpRestController {

    private final SignUpService signUpService;

    public SignUpRestController(SignUpService signUpService) {
        this.signUpService = signUpService;
    }


    @PostMapping("/signup")
    @ApiOperation(value = "Create a new User",
            response = UserDto.class)
    @PermitAll
    public ResponseEntity<UserDto> signUp(
            @Validated @RequestBody final SignUpDto userDto) {
        return ResponseEntity.ok(signUpService.signUp(userDto));
    }

}
