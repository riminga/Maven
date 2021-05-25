package ru.itis.site.repositories;

import ru.itis.site.models.Account;

import java.util.List;
import java.util.Optional;

public interface CrudRepository <T , ID> {
    List<T> findAll();
    // тут написано что возвращается T, а если он не найден, вернется что?
    // вернется ли null?
    // произойдет исключение?
    // а может вернется пустой объект у которого все поля null?
    Account findById(int id);

    void save(T account);

    void update(T account);

    void delete();

    void deleteById(ID id);

    void findByLogin(String login, String password);

}
