package ru.itis.usersservice.dto.validatiors;

import ru.itis.usersservice.dto.annotations.UniquePhone;
import ru.itis.usersservice.services.UserService;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * created: 12-07-2021 - 14:39
 * project: NDA
 *
 * @author dinar
 * @version v0.1
 */
public class UniquePhoneValidator implements
        ConstraintValidator<UniquePhone, String> {

    private final UserService userService;

    public UniquePhoneValidator(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void initialize(UniquePhone constraintAnnotation) {
    }

    @Override
    public boolean isValid(String phone,
                           ConstraintValidatorContext constraintValidatorContext) {
        return !userService.phoneIsExists(phone);
    }
}
