package ru.itis.authservice.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.itis.authservice.dto.EmailAndPasswordLoginDto;
import ru.itis.authservice.services.LoginService;

import javax.validation.Valid;

/**
 * created: 16-07-2021 - 12:27
 * project: NDA
 *
 * @author dinar
 * @version v0.1
 */
@RestController
@RequestMapping("/auth")
public class LoginRestController {

    private final LoginService loginService;

    public LoginRestController(LoginService loginService) {
        this.loginService = loginService;
    }

    @PostMapping("/api/1.0/login")
    public ResponseEntity<?> login(@Valid @RequestBody EmailAndPasswordLoginDto loginDto) {
        return ResponseEntity.ok(loginService.login(loginDto));
    }

}
