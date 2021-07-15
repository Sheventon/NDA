package ru.itis.usersservice.exceptions;

/**
 * created: 15-07-2021 - 11:07
 * project: NDA
 *
 * @author dinar
 * @version v0.1
 */
public class UserNotFoundException extends RuntimeException {

    public UserNotFoundException() {
        super();
    }

    public UserNotFoundException(String message) {
        super(message);
    }

}
