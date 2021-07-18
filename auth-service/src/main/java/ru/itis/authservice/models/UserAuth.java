package ru.itis.authservice.models;

import lombok.*;

import java.util.List;

/**
 * created: 16-07-2021 - 13:36
 * project: NDA
 *
 * @author dinar
 * @version v0.1
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserAuth {

    private Long id;

    private String email;

    private String password;

    private List<String> roles;

}
