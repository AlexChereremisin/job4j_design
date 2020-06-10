package ru.job4j.generic;

import java.lang.reflect.ParameterizedType;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

/**
 * SimpleArray<T> универсальная обертка над массивом.
 * @param <T> любой ссылочный тип.
 */
public final class SimpleArray<T> implements Iterable<T> {
    /**
     * Массив обьектов.
     */
    private Object[] data;
    /**
     * Указатель на первый пустой элемент массива.
     */
    private int cursor;

    /**
     * Конструктор устанавливающий размер массива data.
     * @param size размер массива.
     */
    public SimpleArray(final int size) {
        this.data = new Object[size];
    }

    /**
     * Метод добавляет в массив data новый элемент.
     * Если нет пустых элементов то бросается
     * исключение IllegalStateException.
     * @param model новый элемент.
     */
    public void add(final T model) {
        boolean condition = this.cursor < this.data.length
                && this.data[cursor] == null;
        if (!condition) {
            throw new IllegalStateException("Все ячейки заполнены!");
        }
        this.data[cursor++] = model;
    }

    /**
     * Метод изменяет значение элемента по указанному индексу.
     * Если индекс вне [0, data.length-1], то бросается
     * исключение IndexOutOfBoundsException.
     * @param index индекс элемента массива data, который будем редактировать.
     * @param model новое значение для элемента массива data.
     */
    public void set(final int index, final T model) {
        this.data[Objects.checkIndex(index, this.data.length)] = model;
    }

    /**
     * Метод удаления элемента по индексу.
     * Все находящиеся справа элементы при этом сдвигаются на единицу влево.
     * Если индекс вне [0, data.length-1], то бросается
     * исключение IndexOutOfBoundsException.
     * @param index индекс удаляемого элемента.
     */
    public void remove(final int index) {
        int ind = Objects.checkIndex(index, this.data.length);
        if (this.data.length - 1 - ind >= 0) {
            System.arraycopy(
                    this.data,
                    ind + 1,
                    this.data,
                    ind,
                    this.data.length - 1 - ind
            );
        }
        this.data[this.data.length - 1] = null;
        this.initCursor();
    }

    /**
     * Метод проверяет есть ли null элементы в массиве.
     * Если есть, то устанавливаем cursor на первый из них.
     */
    private void initCursor() {
        for (int i = 0; i < this.data.length; i++) {
            if (this.data[i] == null) {
                cursor = i;
                break;
            }
        }
    }

    /**
     * Метод возвращает элемент по указанному индексу.
     * Если индекс вне [0, data.length-1], то бросается
     * исключение IndexOutOfBoundsException.
     * @param index индекс элемента.
     * @return элемент взятый по данному индексу.
     */
    public T get(final int index) {
        T t = null;
        Object obj = this.data[Objects.checkIndex(index, this.data.length)];
        try {
            t = (T) obj;
        } catch (ClassCastException ex) {
            ex.printStackTrace();
        }
        return t;
    }

    /**
     * Метод возвращает итератор.
     * @return возвращает итератор,
     * предназначенный для обхода данной структуры.
     */
    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            /**
             * Указатель на ненулевой элемент.
             */
            private int position;

            /**
             * Проверка, есть ли следующий элемент.
             * @return true, если элементы есть, иначе - false.
             */
            @Override
            public boolean hasNext() {
                while (position < data.length) {
                    if (data[position] != null) {
                        break;
                    }
                    position++;
                }
                return position < data.length;
            }

            /**
             * Возвращает следующий элемент.
             * @return следующий элемент, если он есть.
             * Иначе бросается исключение типа NoSuchElementException.
             */
            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                T t = null;
                try {
                    t = (T) data[position++];
                } catch (ClassCastException ex) {
                    ex.printStackTrace();
                }
                return t;
            }
        };
    }
}
