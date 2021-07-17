package ru.itis.authservice.exeptions;

/**
 * created: 16-07-2021 - 14:08
 * project: NDA
 *
 * @author dinar
 * @version v0.1
 */
public class AuthenticationException extends RuntimeException {

    public AuthenticationException(String message) {
        super(message);
    }

}
