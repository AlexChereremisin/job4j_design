package ru.job4j.it;

import java.util.Collections;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Класс конвертации итератора итераторов целых чисел в итератор чисел.
 */
public final class Converter {
    /**
     * Метод конвертирует итератор итераторов целых чисел в итератор чисел.
     * @param it итератор итераторов целых.
     * @return итератор чисел.
     */
    public Iterator<Integer> convert(
            final Iterator<Iterator<Integer>> it
    ) {
        return new Iterator<Integer>() {
            /**
             * Указатель на текущий итератор чисел.
             */
            private Iterator<Integer> cursor = Collections.emptyIterator();

            /**
             * Метод нахождения непустого итератора чисел.
             */
            private void initCursor() {
                while (it.hasNext()) {
                    cursor = it.next();
                    if (cursor.hasNext()) {
                        break;
                    }
                }
            }

            /**
             * Проверка, есть ли следующий непустой итератор.
             * @return true, если непустой итератор есть, иначе - false.
             */
            @Override
            public boolean hasNext() {
                if (!cursor.hasNext() && it.hasNext()) {
                    initCursor();
                }
                return cursor.hasNext();
            }

            /**
             * Возвращает следующий элемент в текущем итераторе чисел.
             * @return следующий элемент, если он есть итераторе.
             * Иначе бросается исключение типа NoSuchElementException.
             */
            @Override
            public Integer next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return cursor.next();
            }
        };
    }
}
