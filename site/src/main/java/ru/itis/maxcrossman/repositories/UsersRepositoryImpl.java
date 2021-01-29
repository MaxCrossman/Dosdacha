package ru.itis.maxcrossman.repositories;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import ru.itis.maxcrossman.models.User;

import javax.sql.DataSource;
import java.util.List;
import java.util.Optional;

public class UsersRepositoryImpl implements UsersRepository {

    //language=SQL
    private static final String SQL_SELECT_BY_EMAIL = "select * from users where email = ?";
    //language=SQL
    private static final String SQL_SELECT_BY_ID = "select * from users where id = ?";
    //language=SQL
    private static final String SQL_SELECT_ALL = "select * from users";
    //language=SQL
    private static final String SQL_INSERT = "insert into users(email, hash_password, firstName, lastName) " +
            "values (?, ?, ?, ?)";

    private JdbcTemplate jdbcTemplate;

    public UsersRepositoryImpl(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    private RowMapper<User> userRowMapper = (row, rowNumber) -> new User(
            row.getLong("id"),
            row.getString("email"),
            row.getString("hash_password"),
            row.getString("firstName"),
            row.getString("lastName"));

    @Override
    public void save(User entity) {
        jdbcTemplate.update(SQL_INSERT,
                entity.getEmail(), entity.getHashPassword(), entity.getFirstName(), entity.getLastName());
    }

    @Override
    public void update(User entity) {

    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public Optional<User> findById(Long id) {
        try {
            return Optional.of(jdbcTemplate.queryForObject(SQL_SELECT_BY_ID, userRowMapper, id));
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public List<User> findAll() {
        return null;
    }

    @Override
    public List<User> findAllByIds(List<Long> ids) {
        return null;
    }

    @Override
    public Optional<User> findByEmail(String email) {
        try {
            return Optional.of(jdbcTemplate.queryForObject(SQL_SELECT_BY_EMAIL, userRowMapper, email));
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }
}
