package ru.itis.usersservice.dto.annotations;

import ru.itis.usersservice.dto.validatiors.UniquePhoneValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

/**
 * UniquePhone
 * created: 12-07-2021 - 14:37
 * project: NDA
 *
 * @author dinar
 * @version v0.1
 */
@Documented
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = UniquePhoneValidator.class)
public @interface UniquePhone {

    String message() default "phone number already are used";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
