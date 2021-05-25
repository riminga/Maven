package ru.itis.site.service;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import ru.itis.site.models.Account;
import ru.itis.site.repositories.JdbcImpl;

import javax.sql.DataSource;
import java.time.LocalDateTime;

import javax.sql.DataSource;
import java.time.LocalDateTime;
import java.util.List;

public class SiteServiceImpl implements SiteService {


    private HikariConfig conf() {
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl("jdbc:postgresql://localhost:5432/javaitis13");
        config.setDriverClassName("org.postgresql.Driver");
        config.setUsername("postgres");
        config.setPassword("Ramazan7877");
        config.setMaximumPoolSize(50);
        return config;
    }

    private JdbcImpl dataSource() {
        DataSource dataSource = new HikariDataSource(conf());
        JdbcImpl accountRepository = new JdbcImpl(dataSource);
        JdbcImpl impl = new JdbcImpl(dataSource);
        return impl;
    }

    @Override
    public void checkIn(String firstName, String lastName, String accountStatus, LocalDateTime data, String login, String password, int id) {
        dataSource().save(Account.builder()
                .firstName(firstName)
                .lastName(lastName)
                .accountingStatus(accountStatus)
                .releaseData(data)
                .login(login)
                .password(password)
                .id(id)
                .build());
    }

    @Override
    public boolean signIn(String login, String password) {
        List<Account> search = dataSource().findAll();
        boolean flag = false;
        for (Account account : search) {
            if (account.getLogin().equals(login) && account.getPassword().equals(password)) {
                flag = true;
                break;
            }
            else {
                flag = false;
            }

        }
        return flag;
    }
}
