package ru.itis.site.app;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import ru.itis.site.models.Account;
import ru.itis.site.repositories.JdbcImpl;
import ru.itis.site.service.SiteService;
import ru.itis.site.service.SiteServiceImpl;

import javax.sql.DataSource;
import java.time.LocalDateTime;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        SiteServiceImpl siteService = new SiteServiceImpl();

        int check;
        String firstName;
        String  lastName;
        String accountStatus;
        LocalDateTime data;
        String login;
        String password;
        int id;

        System.out.println("1 - регистрация пользователя");
        System.out.println("2 - выполните вход" );
        check = scanner.nextInt();
        if (check == 1){
            System.out.print("Введите имя - ");
            firstName  = scanner.next();
            System.out.print("Введите фамилию - ");
            lastName = scanner.next();
            System.out.print("Введите статус - ");
            accountStatus  = scanner.next();
            data = LocalDateTime.now();
            System.out.print("Введите логин - ");
            login  = scanner.next();
            System.out.print("Введите пароль- ");
            password = scanner.next();
            id = 2;
            siteService.checkIn(firstName,lastName,accountStatus,data,login,password,id);
        }
        if (check == 2){
            System.out.print("Введите логин - ");
            login  = scanner.next();
            System.out.print("Введите пароль- ");
            password = scanner.next();
            if(siteService.signIn(login,password)== false){
                System.out.println("Пользователь не найден");
            }
            else{
                System.out.println("вход выполнен");
            }

        }
    }
}
