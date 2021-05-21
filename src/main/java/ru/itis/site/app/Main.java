package ru.itis.site.app;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import ru.itis.site.models.Account;
import ru.itis.site.repositories.JdbcImpl;

import javax.sql.DataSource;
import java.time.LocalDateTime;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl("jdbc:postgresql://localhost:5432/javaitis13");
        config.setDriverClassName("org.postgresql.Driver");
        config.setUsername("postgres");
        config.setPassword("Ramazan7877");
        config.setMaximumPoolSize(50);

        DataSource dataSource = new HikariDataSource(config);
        JdbcImpl accountRepository = new JdbcImpl(dataSource);
        Account account = accountRepository.findById(0);
        account.setFirstName("Миха");
        accountRepository.update(account);




     /*  accountRepository.save(Account.builder()
        .firstName("Рамиль")
        .lastName("Мингазов")
        .accountingStatus("admin")
        .releaseData(LocalDateTime.now())
        .login("Rim")
        .password("8788")
        .id(0)
        .build()); */




    }
}
