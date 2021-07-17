package ru.itis.authservice.controllers;

import com.auth0.jwt.exceptions.JWTVerificationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.itis.authservice.services.TokenIssuerService;

import javax.servlet.http.HttpServletResponse;

/**
 * created: 17-07-2021 - 13:14
 * project: NDA
 *
 * @author dinar
 * @version v0.1
 */

@RestController
@RequestMapping("/auth")
public class ValidateTokenController {

    private final TokenIssuerService tokenIssuerService;

    public ValidateTokenController(TokenIssuerService tokenIssuerService) {
        this.tokenIssuerService = tokenIssuerService;
    }

    @GetMapping("/api/1.0/authorize")
    public ResponseEntity<?> validate(
            @RequestHeader("Authorization") String token,
            HttpServletResponse response) {
        response.setHeader("Authorization", tokenIssuerService.authenticate(token));
        return ResponseEntity.ok().build();
    }

    @ExceptionHandler(JWTVerificationException.class)
    public ResponseEntity<?> handleJWTVerificationException(
            JWTVerificationException ex) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }

}
