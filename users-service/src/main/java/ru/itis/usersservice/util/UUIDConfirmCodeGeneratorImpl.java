package ru.itis.usersservice.util;

import org.springframework.stereotype.Component;

import java.util.UUID;

/**
 * created: 14-07-2021 - 12:03
 * project: NDA
 *
 * @author dinar
 * @version v0.1
 */

@Component
public class UUIDConfirmCodeGeneratorImpl implements ConfirmCodeGenerator {

    /**
     * email based UUID generator
     * */
    @Override
    public String generateConfirmCode(String email) {
        return UUID.nameUUIDFromBytes(email.getBytes()).toString();
    }

}
