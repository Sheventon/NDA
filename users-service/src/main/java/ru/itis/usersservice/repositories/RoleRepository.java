package ru.itis.usersservice.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itis.usersservice.models.Role;

import java.util.Optional;

/**
 * created: 12-07-2021 - 12:03
 * project: users-service
 *
 * @author dinar
 * @version v0.1
 */
public interface RoleRepository extends JpaRepository<Role, Integer> {

    Optional<Role> findByRole(Role.RoleValue role);

}
