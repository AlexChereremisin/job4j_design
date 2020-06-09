package ru.job4j.generic;

/**
 * Класс реализующий интерфейс Store<Role>.
 */
public final class RoleStore implements Store<Role> {
    /**
     * Композиция на объект MemStore.
     */
    private final Store<Role> store = new MemStore<>();

    /**
     * Метод добавления нового Role.
     * @param model новый Role.
     */
    @Override
    public void add(final Role model) {
        this.store.add(model);
    }

    /**
     * Метод замены существующего Role на нового.
     * @param id ID Role, которого хотим заменить.
     * @param model новый Role.
     * @return true, если такой ID нашелся, иначе - false.
     */
    @Override
    public boolean replace(final String id, final Role model) {
        return this.store.replace(id, model);
    }

    /**
     * Метод удаления Role по ID.
     * @param id ID Role, которого хотим удалить.
     * @return true, если такой ID нашелся, иначе - false.
     */
    @Override
    public boolean delete(final String id) {
        return this.store.delete(id);
    }

    /**
     * Метод поиска Role по его ID.
     * @param id ID Role, которого хотим найти.
     * @return возвращает Role если такой ID нашелся, иначе null.
     */
    @Override
    public Role findById(final String id) {
        return this.store.findById(id);
    }
}
