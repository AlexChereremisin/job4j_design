package ru.job4j.it;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Класс реализует шаблон итератор для двухмерного массива int[][].
 */
public final class MatrixIt implements Iterator<Integer> {
    /**
     * Двумерный массив.
     */
    private int[][] data;
    /**
     * Указатель на строку.
     */
    private int row = 0;
    /**
     * Указатель на элемент в строке.
     */
    private int column = 0;

    /**
     * Конструктор.
     * Инициализируем двумерный массив.
     * @param newData двумерный массив.
     */
    public MatrixIt(final int[][] newData) {
        this.data = newData;
    }

    /**
     * Проверка, есть ли следующий элемент.
     * @return true, если элементы есть, иначе - false.
     */
    @Override
    public boolean hasNext() {
        if (this.column >= this.data[this.row].length) {
            for (int delta = 1; row + delta < this.data.length; delta++) {
                if (this.data[row + delta].length > 0) {
                    row += row + delta;
                    column = 0;
                }
            }
        }
        return this.column < this.data[this.row].length;
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
        return data[row][column++];
    }
}
