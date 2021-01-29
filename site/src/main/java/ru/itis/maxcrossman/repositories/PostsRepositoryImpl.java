package ru.itis.maxcrossman.repositories;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import ru.itis.maxcrossman.models.Post;

import javax.sql.DataSource;
import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;
import java.util.TimeZone;

public class PostsRepositoryImpl implements PostsRepository {

    //language=SQL
    private static final String SQL_SELECT_ALL = "select * from posts";
    //language=SQL
    private static final String SQL_SELECT_MAX_ID = "select max(id) from posts";
    //language=SQL
    private static final String SQL_INSERT = "insert into posts(boardid, authorid, name, value, time_stamp, isthread, threadid) " +
            "values (?,?,?,?,?,?,?)";
    //language=SQL
    private static final String SQL_SELECT_BY_BOARD = "select * from posts where isthread = true and boardid = ? order by time_stamp desc";
    //language=SQL
    private static final String SQL_SELECT_BY_THREAD = "select * from posts where threadid = ? order by time_stamp";
    //language=SQL
    private static final String SQL_SELECT_BY_AUTHOR = "select * from posts where authorid = ? order by time_stamp desc";

    private RowMapper<Post> postRowMapper = (row, rowNumber) -> new Post(
            row.getLong("id"),
            row.getLong("boardid"),
            row.getLong("authorid"),
            row.getString("name"),
            row.getString("value"),
            LocalDateTime.ofInstant(
                    Instant.ofEpochMilli(
                            row.getTimestamp("time_stamp",
                                    Calendar.getInstance(TimeZone.getTimeZone("UTC"))).getTime()),
                    ZoneOffset.UTC),
            row.getBoolean("isthread"),
            row.getLong("threadid")
    );

    private JdbcTemplate jdbcTemplate;

    public PostsRepositoryImpl(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public Long getNewId() {
        try {
            return jdbcTemplate.queryForObject(SQL_SELECT_MAX_ID, Long.TYPE) + 1;
        } catch (NullPointerException exception) {
            return Long.valueOf(1);
        }
    }

    @Override
    public List<Post> findByBoardId(Long id) {
        return jdbcTemplate.query(SQL_SELECT_BY_BOARD, postRowMapper, id);
    }

    @Override
    public List<Post> findByThreadId(Long id) {
        return jdbcTemplate.query(SQL_SELECT_BY_THREAD, postRowMapper, id);
    }

    @Override
    public List<Post> findByAuthorId(Long id) {
        return jdbcTemplate.query(SQL_SELECT_BY_AUTHOR, postRowMapper, id);
    }

    @Override
    public void save(Post entity) {
        jdbcTemplate.update(SQL_INSERT,
                entity.getBoardId(),
                entity.getAuthorId(),
                entity.getName(),
                entity.getValue(),
                new Timestamp(entity.getTimestamp().toInstant(ZoneOffset.UTC).toEpochMilli()),
                entity.isThread(),
                entity.getThreadId()
        );
    }

    @Override
    public void update(Post entity) {

    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public Optional<Post> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public List<Post> findAll() {
        return jdbcTemplate.query(SQL_SELECT_ALL, postRowMapper);
    }

    @Override
    public List<Post> findAllByIds(List<Long> ids) {
        return null;
    }
}
