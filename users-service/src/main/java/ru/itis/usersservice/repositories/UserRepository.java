package ru.itis.usersservice.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.itis.usersservice.models.User;

import java.util.Optional;

/**
 * created: 11-07-2021 - 15:55
 * project: users-service
 *
 * @author dinar
 * @version v0.1
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);

    Optional<User> findByPhone(String phone);

}
