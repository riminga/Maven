package ru.itis.site.models;

import lombok.*;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class Account {
    private String firstName;
    private String lastName;
    private String accountingStatus;
    private LocalDateTime releaseData;
    private String login;
    private String password;
    private int id;

    public void setId(long longValue) {
    }
}
