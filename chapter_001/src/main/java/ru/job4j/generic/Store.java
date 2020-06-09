package ru.job4j.generic;

/**
 * Контейнер для моделей расширяющих класс Base.
 * @param <T> типы расширяющие класс Base.
 */
public interface Store<T extends Base> {
    /**
     * Метод добавления новой модели.
     * @param model новая модель.
     */
    void add(final T model);

    /**
     * Метод замены существующей модели на новую.
     * @param id ID модели, которою хотим заменить.
     * @param model новая модель.
     * @return true, если такой ID нашелся, иначе - false.
     */
    boolean replace(final String id, final T model);

    /**
     * Метод удаления модели по ID.
     * @param id ID модели, которою хотим удалить.
     * @return true, если такой ID нашелся, иначе - false.
     */
    boolean delete(final String id);

    /**
     * Метод поиска модеи по ее ID.
     * @param id ID модели, которою хотим найти.
     * @return возвращает модель если такой ID нашелся, иначе null.
     */
    T findById(final String id);
}
