package ru.job4j.it;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Класс реализует шаблон итератор.
 * Из одномерного массива возвращаются только четные числа.
 */
public final class EvenIt implements Iterator<Integer> {
    /**
     * Одномерный массив целых чисел.
     */
    private int[] data;
    /**
     * Указатель на элемент массива.
     */
    private int point = 0;

    /**
     * Конструктор.
     * Инициализируем одномерный массив.
     * @param numbers одномерный массив чисел.
     */
    public EvenIt(final int[] numbers) {
        this.data = numbers;
    }

    /**
     * Метод проверяющий, есть ли еще четные числа в масииве.
     * @return true, если четные числа есть, иначе - false.
     */
    @Override
    public boolean hasNext() {
        boolean rsl = false;
        for (int pointer = point; pointer < data.length; pointer++) {
            if (data[pointer] % 2 == 0) {
                point = pointer;
                rsl = true;
                break;
            }
        }
        return rsl;
    }

    /**
     * Метод возращает следующий элемент.
     * @return следующий элемент содержащий четное число.
     * Иначе бросается исключение типа NoSuchElementException.
     */
    @Override
    public Integer next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        return data[point++];
    }
}
