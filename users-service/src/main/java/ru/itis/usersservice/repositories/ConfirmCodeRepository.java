package ru.itis.usersservice.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itis.usersservice.models.ConfirmCode;

import java.util.Optional;

/**
 * created: 14-07-2021 - 12:23
 * project: NDA
 *
 * @author dinar
 * @version v0.1
 */
public interface ConfirmCodeRepository
        extends JpaRepository<ConfirmCode, String> {

    Optional<ConfirmCode> getByCode(String code);

    void deleteByUserId(Long userId);
}
