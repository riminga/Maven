package ru.itis.site.service;

import java.time.LocalDateTime;

public interface SiteService {
    void checkIn(String firstName, String lastName,
                 String accountStatus, LocalDateTime data, String login, String password,int id);

    boolean signIn(String login, String password);
}
