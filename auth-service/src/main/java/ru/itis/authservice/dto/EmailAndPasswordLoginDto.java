package ru.itis.authservice.dto;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

/**
 * created: 16-07-2021 - 12:30
 * project: NDA
 *
 * @author dinar
 * @version v0.1
 */
@Data
public class EmailAndPasswordLoginDto {

    @Email
    @NotNull
    private String email;

    @NotNull
    private String password;

}
