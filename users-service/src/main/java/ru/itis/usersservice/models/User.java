package ru.itis.usersservice.models;

import lombok.*;

import javax.persistence.*;
import java.util.List;

/**
 * created: 11-07-2021 - 15:22
 * project: NDA
 *
 * @author dinar
 * @version v0.1
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "user_table")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    private String email;

    /**
     * phone number should starts without "+" symbol
     */
    @Column(length = 11)
    private String phone;

    private String password;

    @Enumerated(value = EnumType.STRING)
    private UserState state;

    @Enumerated(value = EnumType.STRING)
    private EmailState emailState;

    @ManyToMany(mappedBy = "users")
    private List<Role> roles;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private ConfirmCode confirmCode;


    /**
     * email states for account verification
     * after registration process
     */
    public enum EmailState {
        CONFIRMED, NOT_CONFIRMED
    }

    public enum UserState {
        ACTIVE, BANNED
    }


    public boolean isActive() {
        return this.state == UserState.ACTIVE;
    }

    public boolean isBanned() {
        return this.state == UserState.BANNED;
    }

    public boolean confirmed() {
        return this.emailState == EmailState.CONFIRMED;
    }

}
