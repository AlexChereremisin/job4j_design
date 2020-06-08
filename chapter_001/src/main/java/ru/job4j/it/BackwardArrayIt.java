package ru.job4j.it;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 *  BackwardArrayIt реализация шаблона итератор.
 *  Класс отдает элемнты массива в обратном порядке.
 */
public final class BackwardArrayIt implements Iterator<Integer> {
    /**
     * Одномерный массив.
     */
    private final int[] data;
    /**
     * Указатель на элемент массива.
     */
    private int point;

    /**
     * Конструктор.
     * Внутри указателю присваиваем индекс последнего элемента.
     * @param newData входящий массив.
     */
    public BackwardArrayIt(final int[] newData) {
        this.data = newData;
        this.point = data.length - 1;
    }

    /**
     * Проверяем есть ли еще элементы.
     * @return true, если элементы есть, иначе - false.
     */
    @Override
    public boolean hasNext() {
        return point >= 0;
    }

    /**
     * Возвращает следующий элемент.
     * @return следующий элемент, если он есть.
     * Иначе бросается исключение типа NoSuchElementException.
     */
    @Override
    public Integer next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        return data[point--];
    }
}
