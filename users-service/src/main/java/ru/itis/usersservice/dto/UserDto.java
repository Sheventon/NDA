package ru.itis.usersservice.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;
import ru.itis.usersservice.models.User;

import java.util.List;
import java.util.stream.Collectors;

/**
 * created: 11-07-2021 - 15:47
 * project: users-service
 *
 * @author dinar
 * @version v0.1
 */
@Data
@Builder
@ApiModel(description = "Standard User data transfer object")
public class UserDto {

    @ApiModelProperty(notes = "The database generated user id")
    private Long id;

    @ApiModelProperty(notes = "The user's firstname")
    private String firstName;

    @ApiModelProperty(notes = "The user's lastname")
    private String lastName;

    @ApiModelProperty(notes = "The user's unique email")
    private String email;

    @ApiModelProperty(notes = "The user's phone number")
    private String phone;

    @ApiModelProperty(notes = "The user's list of roles")
    private List<RoleDto> roles;

    @ApiModelProperty(notes = "The user's state 'ACTIVE' / 'BANNED'")
    private User.UserState state;

    @ApiModelProperty(notes = "The user's email state 'CONFIRMED' / 'NOT CONFIRMED' ")
    private User.EmailState emailState;


    public static UserDto from(User user) {
        return user == null ? null :
                UserDto.builder()
                        .id(user.getId())
                        .firstName(user.getFirstName())
                        .lastName(user.getLastName())
                        .email(user.getEmail())
                        .phone(user.getPhone())
                        .state(user.getState())
                        .emailState(user.getEmailState())
                        .roles(RoleDto.from(user.getRoles()))
                        .build();
    }

    public static List<UserDto> from(List<User> users) {
        return users.stream().map(UserDto::from)
                .collect(Collectors.toList());
    }

}
