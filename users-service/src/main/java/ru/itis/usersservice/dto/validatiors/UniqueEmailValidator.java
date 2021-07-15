package ru.itis.usersservice.dto.validatiors;

import ru.itis.usersservice.dto.annotations.UniqueEmail;
import ru.itis.usersservice.services.UserService;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * created: 11-07-2021 - 16:15
 * project: users-service
 *
 * @author dinar
 * @version v0.1
 */
public class UniqueEmailValidator implements
        ConstraintValidator<UniqueEmail, String> {

    private final UserService userService;

    public UniqueEmailValidator(UserService userService) {
        this.userService = userService;
    }


    @Override
    public void initialize(UniqueEmail constraintAnnotation) {
    }

    @Override
    public boolean isValid(final String email,
                           ConstraintValidatorContext constraintValidatorContext) {
        return !userService.emailIsExists(email);
    }
}
