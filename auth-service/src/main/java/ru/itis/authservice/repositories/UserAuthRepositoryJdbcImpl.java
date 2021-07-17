package ru.itis.authservice.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import ru.itis.authservice.models.UserAuth;

import java.util.List;
import java.util.Optional;

/**
 * created: 16-07-2021 - 13:27
 * project: NDA
 *
 * @author dinar
 * @version v0.1
 */
@Repository
public class UserAuthRepositoryJdbcImpl implements UserAuthRepository {

    //language=SQL
    private final static String SELECT_USER_AUTH_DATA =
            "select id, email, password from user_table " +
                    "where email = ?;";

    //language=SQL
    private final static String SELECT_USER_ROLES =
            "select role from user_role " +
                    "inner join user_has_role uhr on user_role.id = uhr.role_id " +
                    "inner join user_table ut on ut.id = uhr.user_id " +
                    "where email = ?";

    private final RowMapper<UserAuth> userAuthRowMapper = (row, i) -> UserAuth.builder()
            .id(row.getLong("id"))
            .email(row.getString("email"))
            .password(row.getString("password"))
            .build();

    private final JdbcTemplate jdbcTemplate;

    public UserAuthRepositoryJdbcImpl(
            @Qualifier("remoteJdbcTemplate") JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    @Override
    public Optional<UserAuth> getUserByEmailAndPassword(String email) {
        UserAuth userAuth;

        try {
            userAuth = jdbcTemplate.queryForObject(SELECT_USER_AUTH_DATA,
                    userAuthRowMapper, email);
            if (userAuth != null) {
                userAuth.setRoles(getUserRoles(email));
            }
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }

        return Optional.ofNullable(userAuth);
    }

    private List<String> getUserRoles(String email) {
        return jdbcTemplate.queryForList(SELECT_USER_ROLES, String.class, email);
    }

}
