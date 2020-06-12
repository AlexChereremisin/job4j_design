package ru.job4j.collection.list;

import java.util.Objects;
import java.util.Arrays;
import java.util.Iterator;
import java.util.ConcurrentModificationException;
import java.util.NoSuchElementException;

/**
 * Класс представляет собой простой динамический массив.
 * @param <T> любой ссылочный тип
 */
public final class SimpleArray<T> implements Iterable<T> {
    /**
     * Внутренний массив - контейнер.
     */
    private Object[] container = new Object[0];
    /**
     * Указатель на следующую пустую ячейку в массиве container.
     */
    private int cursor;
    /**
     * Счетчик изменений внутреннего массива.
     */
    private int modCount;

    /**
     * Метод возвращающий элемент внутреннего массива, по индексу.
     * @param index индекс элемента.
     * @return Элемент массива взятый по индексу.
     * Если индекс не в диапазоне [0, container.length - 1],
     * то бросается исключение IndexOutOfBoundsException.
     */
    public T get(final int index) {
        int i = Objects.checkIndex(index, container.length);
        return (T) container[i];
    }

    /**
     * Метод добавления нового элемента во внутренний массив.
     * Если текущий размер массива мал для новой операции добавления,
     * то увеличиваем размер на 1.
     * @param model новый элемент для добавления в массив container.
     */
    public void add(final T model) {
        if (cursor >= container.length - 1 || container.length == 0) {
            container = Arrays.copyOf(
                    container,
                    cursor + 1
            );
        }
        container[cursor++] = model;
        modCount++;
    }

    /**
     * Геттер поля cursor.
     * @return значение поля курсор.
     */
    public int size() {
        return cursor;
    }

    /**
     * Метод создания итератора по данной структуре.
     * @return итератор по данной структуре.
     * Если итератор получен и после произошли изменения в структуре,
     * то бросается исключение ConcurrentModificationException.
     */
    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            /**
             * Указатель на следующий элемент структуры.
             */
            private int position;
            /**
             * Поле для хранения счетчика изменений структуры.
             */
            private final int expectedModCount = modCount;

            /**
             * Метод проверяет изменилась, ли структура или нет.
             * @return true, если изменений структуры нет,
             * иначе бросается исключение ConcurrentModificationException.
             */
            private boolean isContainerMod() {
                boolean rsl = expectedModCount == modCount;
                if (!rsl) {
                    throw new ConcurrentModificationException();
                }
                return rsl;
            }

            /**
             * Проверка, есть ли следующий элемент.
             * @return true, если элементы есть, иначе - false.
             */
            @Override
            public boolean hasNext() {
                isContainerMod();
                return position < cursor;
            }

            /**
             * Возвращает следующий элемент.
             * @return следующий элемент, если он есть.
             * Иначе бросается исключение типа NoSuchElementException.
             */
            @Override
            public T next() {
                isContainerMod();
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return (T) container[position++];
            }
        };
    }
}
