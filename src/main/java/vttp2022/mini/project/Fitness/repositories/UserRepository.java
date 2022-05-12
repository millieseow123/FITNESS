package vttp2022.mini.project.Fitness.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.batch.BatchProperties.Jdbc;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;

import vttp2022.mini.project.Fitness.models.User;
import static vttp2022.mini.project.Fitness.repositories.Queries.*;
import static vttp2022.mini.project.Fitness.models.Utilities.*;

import java.util.Optional;

@Repository
public class UserRepository {
    
    @Autowired
    private JdbcTemplate template;

    public boolean signup(User user){
        int count = template.update(SQL_INSERT_NEW_USER, user.getEmail(), user.getPassword(), user.getFull_name(), user.getDob());
        return 1 == count;
    } 

    public Optional<User> findUserByEmail(String email) {
        final SqlRowSet rs = template.queryForRowSet(SQL_SELECT_USER_BY_EMAIL, email);
        if (!rs.next())
            return Optional.empty();

        return Optional.of(convertLogin(rs));
    }

    public int countUsersByEmailAndPassword(String email, String password) {
        SqlRowSet rs = template.queryForRowSet(SQL_SELECT_AND_COUNT_USERS_FROM_EMAIL, email, password);
        if (!rs.next())
            return 0;
        return rs.getInt("user_count");
    }
}
