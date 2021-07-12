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
 * UpdateUserDto
 * created: 12-07-2021 - 15:42
 * project: NDA
 *
 * @author dinar
 * @version v0.1
 */
@Data
@ApiModel(description = "Update User data transfer object")
public class UpdateUserDto {

    @NotNull
    @ApiModelProperty(value = "The user's id")
    private Long id;

    @Size(max = 45)
    @ApiModelProperty(value = "The user's firstname",
            example = "Dinar")
    private String firstName;

    @Size(max = 45)
    @ApiModelProperty(value = "The user's lastname",
            example = "Shagaliev")
    private String lastName;

    @Email
    @UniqueEmail
    @ApiModelProperty(value = "The user's email",
            example = "nda.user.service@gmail.com")
    private String email;


    @UniquePhone
    @Pattern(regexp = "[0-9]{11}")
    @ApiModelProperty(value = "The user's phone number",
            example = "89276669977")
    private String phone;


}