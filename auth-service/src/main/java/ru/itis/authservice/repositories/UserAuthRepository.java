package ru.itis.authservice.repositories;

import ru.itis.authservice.models.UserAuth;

import java.util.Optional;

/**
 * created: 16-07-2021 - 13:26
 * project: NDA
 *
 * @author dinar
 * @version v0.1
 */
public interface UserAuthRepository {     // extends CRUD repository

    Optional<UserAuth> getUserByEmailAndPassword(String email);

}
