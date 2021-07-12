package ru.itis.usersservice.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import ru.itis.usersservice.dto.annotations.UniqueEmail;
import ru.itis.usersservice.dto.annotations.UniquePhone;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 * created: 12-07-2021 - 14:55
 * project: NDA
 *
 * @author dinar
 * @version v0.1
 */
@Data
@ApiModel(description = "Sign up required user data")
public class SignUpDto {

    @NotNull
    @Size(max = 45)
    @ApiModelProperty(value = "The user's firstname",
            example = "Dinar")
    private String firstName;

    @NotNull
    @Size(max = 45)
    @ApiModelProperty(value = "The user's lastname",
            example = "Shagaliev")
    private String lastName;

    @Email
    @NotNull
    @UniqueEmail
    @ApiModelProperty(value = "The user's email",
            example = "nda.user.service@gmail.com")
    private String email;

    @NotNull
    @UniquePhone
    @Pattern(regexp = "[0-9]{11}")
    @ApiModelProperty(value = "The user's phone number",
            example = "89276669977")
    private String phone;


}
