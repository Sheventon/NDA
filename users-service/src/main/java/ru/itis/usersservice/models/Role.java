package ru.itis.usersservice.models;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * created: 12-07-2021 - 11:28
 * project: users-service
 *
 * @author dinar
 * @version v0.1
 */
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "user_role")
@Setter
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Enumerated(EnumType.STRING)
    private RoleValue role;

    @ManyToMany
    @JoinTable(
            name = "user_has_role",
            joinColumns = @JoinColumn(name = "role_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private List<User> users = new ArrayList<>();

    public enum RoleValue {
        USER, ADMIN
    }

}
