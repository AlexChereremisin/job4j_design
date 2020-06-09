package ru.job4j.generic;

/**
 * User расширяет базовый класс Base.
 */
public final class User extends Base {
    /**
     * Конструктор.
     * Вызываем базовый конструктор.
     * @param id ID модели.
     */
    protected User(final String id) {
        super(id);
    }
}
