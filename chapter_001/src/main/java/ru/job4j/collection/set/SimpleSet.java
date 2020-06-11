package ru.job4j.collection.set;

import ru.job4j.collection.list.SimpleArray;

import java.util.Iterator;

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
     * @param e новый элемент.
     */
    public void add(final E e) {
        E tmp;
        for (int index = 0; index < container.size(); index++) {
            tmp = container.get(index);
            if (tmp == null) {
                return;
            } else if (tmp.equals(e)) {
                return;
            }
        }
        container.add(e);
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
