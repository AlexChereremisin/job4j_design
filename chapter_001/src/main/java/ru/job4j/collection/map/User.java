package ru.job4j.collection.map;

import java.util.Calendar;

/**
 * Модель данных User.
 */
public final class User {
    /**
     * Имя User.
     */
    private String name;
    /**
     * Количество детей.
     */
    private int children;
    /**
     * Дата рождения.
     */
    private Calendar birthday;

    /**
     * Конструктор для инициализации всех полей.
     * @param userName имя нового User.
     * @param userChildren количество детей нового User.
     * @param userBirthday дата рождения нового User.
     */
    public User(
            final String userName,
            final int userChildren,
            final Calendar userBirthday
    ) {
        this.name = userName;
        this.children = userChildren;
        this.birthday = userBirthday;
    }
}
