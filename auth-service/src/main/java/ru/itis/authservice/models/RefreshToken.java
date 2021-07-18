package ru.itis.authservice.models;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;

/**
 * created: 16-07-2021 - 15:20
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
@Table(catalog = "nda_auth_db", name = "user_refresh_token_table")
public class RefreshToken {

    @Id
    private String token;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "issued_at_time")
    private LocalDateTime issuedAt;

    @Column(name = "expired_at_time")
    private LocalDateTime expiredAt;

}
