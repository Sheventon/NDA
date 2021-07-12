package ru.itis.usersservice.dto;

import lombok.Builder;
import lombok.Data;
import ru.itis.usersservice.models.Role;

import java.util.List;
import java.util.stream.Collectors;

/**
 * created: 12-07-2021 - 15:08
 * project: NDA
 *
 * @author dinar
 * @version v0.1
 */
@Data
@Builder
public class RoleDto {

    private String name;

    public static RoleDto from(Role role) {
        return RoleDto.builder()
                .name(role.getRole().name())
                .build();
    }

    public static List<RoleDto> from(List<Role> roles){
        return roles.stream().map(RoleDto::from)
                .collect(Collectors.toList());
    }

}
