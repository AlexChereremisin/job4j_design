package ru.job4j.generic;

import java.util.ArrayList;
import java.util.List;

/**
 * Универсальный контейнер для моделей расширяющих тип Base.
 * @param <T> типы расширяющие класс Base.
 */
public final class MemStore<T extends Base> implements Store<T> {
    /**
     * Список всех хранимых моделей.
     */
    private final List<T> mem = new ArrayList<>();

    /**
     * Метод добавления новой модели.
     * @param model новая модель.
     */
    @Override
    public void add(final T model) {
        this.mem.add(model);
    }

    /**
     * Метод находения индекса модели в списке по ID.
     * @param id ID модели, индекс которой хотим найти.
     * @return индекс найденной по ID модели, иначе -1.
     */
    private int indexOf(final String id) {
        int index = -1;
        for (int i = 0; i < this.mem.size(); i++) {
            if (this.mem.get(i).getId().equals(id)) {
                index = i;
                break;
            }
        }
        return index;
    }

    /**
     * Метод замены существующей модели на новую.
     * @param id ID модели, которою хотим заменить.
     * @param model новая модель.
     * @return true, если такой ID нашелся, иначе - false.
     */
    @Override
    public boolean replace(final String id, final T model) {
        int index = this.indexOf(id);
        boolean rsl = index != -1;
        if (rsl) {
            this.mem.set(index, model);
        }
        return rsl;
    }

    /**
     * Метод удаления модели по ID.
     * @param id ID модели, которою хотим удалить.
     * @return true, если такой ID нашелся, иначе - false.
     */
    @Override
    public boolean delete(final String id) {
        int index = this.indexOf(id);
        boolean rsl = index != -1;
        if (rsl) {
            this.mem.remove(index);
        }
        return rsl;
    }

    /**
     * Метод поиска модеи по ее ID.
     * @param id ID модели, которою хотим найти.
     * @return возвращает модель если такой ID нашелся, иначе null.
     */
    @Override
    public T findById(final String id) {
        T rsl = null;
        int index = this.indexOf(id);
        if (index != -1) {
            rsl = this.mem.get(index);
        }
        return rsl;
    }
}
