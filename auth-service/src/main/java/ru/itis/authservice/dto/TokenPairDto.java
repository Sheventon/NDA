package ru.itis.authservice.dto;

import lombok.Builder;
import lombok.Data;

/**
 * created: 16-07-2021 - 12:33
 * project: NDA
 *
 * @author dinar
 * @version v0.1
 */
@Data
@Builder
public class TokenPairDto {

    private String access;

    private String refresh;

    private Long expiration;

}
