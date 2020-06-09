package ru.job4j.generic;

/**
 * Base базовый класс для всех моделей.
 */
public abstract class Base {
    /**
     * Строковое поле для хранения ID.
     */
    private final String id;

    /**
     * Конструктор.
     * Инициализируем поле id.
     * @param newId ID модели.
     */
    protected Base(final String newId) {
        this.id = newId;
    }

    /**
     * Геттер поля ID.
     * @return значение поля ID.
     */
    public final String getId() {
        return id;
    }
}
