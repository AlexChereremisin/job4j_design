package ru.job4j.collection.set;

import ru.job4j.collection.list.SimpleArray;

import java.util.Iterator;
import java.util.Objects;

/**
 * Реализация структуры данных Set.
 * @param <E> любой ссылочый тип.
 */
public final class SimpleSet<E> implements Iterable<E> {
    /**
     * Композиция на реализацию универсальной обертки над массивом.
     */
    private final SimpleArray<E> container = new SimpleArray<>();

    /**
     * Метод добавления нового элемента в множество.
     * Одинаковые элементы не добавляются.
     * @param el новый элемент.
     */
    public void add(final E el) {
        if (contains(el)) {
            return;
        }
        container.add(el);
    }

    /**
     * Метод проверяет содержится ли элемент в множестве.
     * @param el элемент, который ищем в множестве.
     * @return true, если такой элемент есть, иначе - false.
     */
    private boolean contains(final E el) {
        boolean rsl = false;
        E tmp;
        for (int index = 0; index < container.size(); index++) {
            tmp = container.get(index);
            if (Objects.equals(tmp, el)) {
                rsl = true;
                break;
            }
        }
        return rsl;
    }

    /**
     * Метод возвращает итератор по данной структуре.
     * @return итератор по данной структуре.
     */
    @Override
    public Iterator<E> iterator() {
        return container.iterator();
    }
}
