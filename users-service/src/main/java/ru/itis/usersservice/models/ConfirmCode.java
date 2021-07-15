package ru.itis.usersservice.models;

import lombok.*;

import javax.persistence.*;

/**
 * created: 14-07-2021 - 11:20
 * project: NDA
 *
 * @author dinar
 * @version v0.1
 */
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "mail_confirm_code")
public class ConfirmCode {

    @Id
    private String code;

    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "user_id")
    private User user;

}
