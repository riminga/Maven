package ru.itis.site.repositories;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import ru.itis.site.models.Account;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static com.sun.org.apache.xerces.internal.util.PropertyState.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static com.github.npathai.hamcrestopt.OptionalMatchers.isPresentAndIs;
import static org.junit.jupiter.api.Assertions.*;

class JdbcImplTest {
    private JdbcImpl jdbc;
    private EmbeddedDatabase database;

    @BeforeEach
    void setUp() {
        database = new EmbeddedDatabaseBuilder()
                .setType(EmbeddedDatabaseType.H2)
                .addScripts("sql\\schema.sql", "sql\\data.sql")
                .build();
        jdbc = new JdbcImpl(database);
    }

    @AfterEach
    void shutdown() {
        database.shutdown();
    }

    @Test
    @DisplayName(value = "findAll return not empty list")
    void findAll() {
        List<Account> accounts = jdbc.findAll();
        assertThat(accounts, Matchers.is(not(empty())));
    }

    @Test
    void find_all_returns_correct_objects() {
        List<Account> accounts = jdbc.findAll();
        assertThat(accounts, contains(expectedAccounts().toArray()));
    }
    private static Account userForSave(){
        return Account.builder()
                .firstName("User_first_name6")
                .lastName("USER_LAST_NAME6")
                .accountingStatus("gggg")
                .releaseData(LocalDateTime.now())
                .password("rrr")
                .login("fff")
                .id(6)
                .build();
    }

    private static Account expectedUser(){
      return Account.builder()
              .firstName("User_first_name")
              .lastName("USER_LAST_NAME")
              .id(1)
              .build();
    }

    private static List<Account> expectedAccounts() {
        return Arrays.asList(
                Account.builder()
                        .firstName("User_first_name")
                        .lastName("USER_LAST_NAME")
                        .id(1)
                        .build(),
                Account.builder()
                        .firstName("User_first_name2")
                        .lastName("USER_LAST_NAME2")
                        .id(2)
                        .build(),
                Account.builder()
                        .firstName("User_first_name3")
                        .lastName("USER_LAST_NAME3")
                        .id(3)
                        .build());
    }

    @Test
    void find_By_Id_return_correct_object() {
       Optional<Account> accountOptional = Optional.ofNullable(jdbc.findById(1));
       assertThat(accountOptional, isPresentAndIs(expectedUser()));
    }

    @Test
    void save_work_correct(){
        jdbc.save(userForSave());
        Optional<Account> accountOptional = Optional.ofNullable(jdbc.findById(6));
       assertThat(accountOptional, isPresentAndIs(userForSave()));
    }
}