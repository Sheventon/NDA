package ru.itis.authservice.security;

import org.springframework.beans.factory.annotation.Value;

/**
 * created: 16-07-2021 - 14:52
 * project: NDA
 *
 * @author dinar
 * @version v0.1
 */
public class JwtConstants {

    /**
     * default expiration time is 1 hour
     * @author: Dinar
     * */

    public static final String ROLES = "roles";

    public static final String ID = "id";

    public static final String EMAIL = "email";

    public static Long EXPIRATION_TIME = 1000 * 60 * 60L;

    @Value("${auth.jwt.expiration-time:#{1000 * 60 * 60}}")
    public void setExpirationTime(Long expirationTime) {
        EXPIRATION_TIME = expirationTime;
    }

}
