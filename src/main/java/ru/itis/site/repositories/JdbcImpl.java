package ru.itis.site.repositories;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import ru.itis.site.models.Account;

import javax.sql.DataSource;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public class JdbcImpl implements AccountsRepository {
    //language = SQL
    public static final String SQL_SELECT_BY_LOGIN = "select * from users where login = ? and password = ?";
    //language=SQL
    public static final String SQL_SELECT_ALL = "select * from  users order  by id;";
    //language=SQL
    public static final String SQL_SELECT_BY_ID = "select * from users where id = ?";
    //language=SQL
    public static final String SQL_INSERT = "insert into " +
            "users( first_name, last_name,accounting_status,release_data " +
            ",login,password,id) values (? ,?, ?, ?, ?,?,?)";
    //language=SQL
    public static final String SQL_UPDATE = "update users set first_name = ?, " +
            "last_name = ?, accounting_status = ?,  release_data = ?,  login = ?, password = ? where id = ?";


    //language = SQL
    public static final String SQL_DELETE ="drop table users;";

    //language = SQL
    public static final String SQL_DELETE_BY_ID = "delete from users where id =?";


    private final JdbcTemplate jdbcTemplate;

    public JdbcImpl(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }


    private RowMapper<Account> accountRowMapper = (row, rowNumber) -> Account.builder()
            .firstName(row.getString("first_name"))
            .lastName(row.getString("last_name"))
           // .releaseData(LocalDate.parse(row.getString("release_data")))
            .login(row.getString("login"))
            .password(row.getString("password"))
            .id(row.getInt("id"))
            .build();


    @Override
    public List<Account> findAll() {
        return jdbcTemplate.query(SQL_SELECT_ALL, accountRowMapper);
    }

    @Override
    public Account findById(int id) {
        return jdbcTemplate.queryForObject(SQL_SELECT_BY_ID,accountRowMapper, id);

    }


    @Override
    public void save(Account account) {
        jdbcTemplate.update(SQL_INSERT, account.getFirstName(), account.getLastName(),
                account.getAccountingStatus(),account.getReleaseData().toString(),  account.getLogin(),
                account.getPassword(),account.getId());

    }

    @Override
    public void update(Account account) {
       jdbcTemplate.update(SQL_UPDATE, account.getFirstName(),account.getLastName(),
               account.getAccountingStatus(),account.getReleaseData(),account.getLogin(),
               account.getPassword(),account.getId());
    }

    @Override
    public void delete() {
      jdbcTemplate.update(SQL_DELETE);
    }

    @Override
    public void deleteById(Long id) {
        jdbcTemplate.update(SQL_DELETE_BY_ID, id);
    }

    @Override
    public void findByLogin(String login, String password) {
        jdbcTemplate.update(SQL_SELECT_BY_LOGIN, login, password);

    }


}
