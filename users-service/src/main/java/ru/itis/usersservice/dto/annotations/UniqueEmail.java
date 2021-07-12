package ru.itis.usersservice.dto.annotations;

import ru.itis.usersservice.dto.validatiors.UniqueEmailValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

/**
 * UniqueEmail
 * created: 11-07-2021 - 16:13
 * project: users-service
 *
 * @author dinar
 * @version v0.1
 */
@Documented
@Constraint(validatedBy = UniqueEmailValidator.class)
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface UniqueEmail {

    String message() default "email already are used";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
