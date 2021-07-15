package ru.itis.usersservice.errorapi;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * created: 15-07-2021 - 10:42
 * project: NDA
 *
 * @author dinar
 * @version v0.1
 */
@Data
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
public class ApiValidationError extends ApiSubError {

    private String object;

    private String field;

    private Object rejectedValue;

    private String message;

    ApiValidationError(String object, String message) {
        this.object = object;
        this.message = message;
    }
}