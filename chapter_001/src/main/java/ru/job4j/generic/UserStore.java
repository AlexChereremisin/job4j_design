package ru.job4j.generic;

/**
 * Класс реализующий интерфейс Store<User>.
 */
public final class UserStore implements Store<User> {
    /**
     * Композиция на объект MemStore.
     */
    private final Store<User> store = new MemStore<>();

    /**
     * Метод добавления нового User.
     * @param model новый User.
     */
    @Override
    public void add(final User model) {
        this.store.add(model);
    }

    /**
     * Метод замены существующего User на нового.
     * @param id ID User, которого хотим заменить.
     * @param model новый User.
     * @return true, если такой ID нашелся, иначе - false.
     */
    @Override
    public boolean replace(final String id, final User model) {
        return this.store.replace(id, model);
    }

    /**
     * Метод удаления User по ID.
     * @param id ID User, которого хотим удалить.
     * @return true, если такой ID нашелся, иначе - false.
     */
    @Override
    public boolean delete(final String id) {
        return this.store.delete(id);
    }

    /**
     * Метод поиска User по его ID.
     * @param id ID User, которого хотим найти.
     * @return возвращает User если такой ID нашелся, иначе null.
     */
    @Override
    public User findById(final String id) {
        return this.store.findById(id);
    }
}
