package ru.itis.maxcrossman.repositories;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import ru.itis.maxcrossman.models.Page;

import javax.sql.DataSource;
import java.util.List;
import java.util.Optional;

public class PagesRepositoryImpl implements PagesRepository {

    //language=SQL
    private static final String SQL_SELECT_ALL = "select * from pages";
    //language=SQL
    private static final String SQL_INSERT = "insert into pages(name, address, is_open, is_board) values (?,?,?,?)";
    //language=SQL
    private static final String SQL_SELECT_BY_ID = "select * from pages where id = ?";
    //language=SQL
    private static final String SQL_SELECT_ALL_BOARDS = "select * from pages where is_board = true";

    private RowMapper<Page> pageRowMapper = (row, rowNumber) -> new Page(
            row.getLong("id"),
            row.getString("name"),
            row.getString("address"),
            row.getBoolean("is_open"),
            row.getBoolean("is_board")
    );

    private JdbcTemplate jdbcTemplate;

    public PagesRepositoryImpl(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public void save(Page entity) {
        jdbcTemplate.update(SQL_INSERT, entity.getName(), entity.getAddress(), entity.isOpen(), entity.isBoard());
    }

    @Override
    public void update(Page entity) {

    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public Optional<Page> findById(Long id) {
        try {
            return Optional.of(jdbcTemplate.queryForObject(SQL_SELECT_BY_ID, pageRowMapper, id));
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public List<Page> findAll() {
        return jdbcTemplate.query(SQL_SELECT_ALL, pageRowMapper);
    }

    @Override
    public List<Page> findAllByIds(List<Long> ids) {
        return null;
    }

    @Override
    public List<Page> findAllBoards() {
        return jdbcTemplate.query(SQL_SELECT_ALL_BOARDS, pageRowMapper);
    }
}
