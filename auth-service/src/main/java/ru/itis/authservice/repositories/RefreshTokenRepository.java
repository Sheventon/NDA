package ru.itis.authservice.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.itis.authservice.models.RefreshToken;

/**
 * created: 16-07-2021 - 15:47
 * project: NDA
 *
 * @author dinar
 * @version v0.1
 */
public interface RefreshTokenRepository
        extends JpaRepository<RefreshToken, String> {
}
