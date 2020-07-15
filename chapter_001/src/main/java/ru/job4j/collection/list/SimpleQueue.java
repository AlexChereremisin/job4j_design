package ru.job4j.collection.list;

import java.util.Optional;

/**
 * Реализация структуры данных Queue.
 * @param <T> любой ссылочный тип.
 */
public final class SimpleQueue<T> {
    /**
     * Стек добавления элементов очереди.
     */
    private final SimpleStack<T> in = new SimpleStack<>();
    /**
     * Стек удаления элементов очереди.
     */
    private final SimpleStack<T> out = new SimpleStack<>();

    /**
     * Метод возвращает первый элемент очереди и
     * удаляет его.
     * Если элементов нет, то бросается
     * исключение NoSuchElementException().
     * @return первый элемент очереди.
     */
    public T poll() {
        T value = (T) Optional.empty();
        int len = in.size() * 2;
        boolean flag;
        boolean outFlag = false;
        boolean fKey = true;
        for (int i = 0; i <= len; i ++) {
            flag = in.size() > 0 && fKey;
            if (flag) {
                out.push(in.pop());
            } else {
                if (fKey) {
                    value = out.pop();
                    fKey = false;
                    outFlag = true;
                }
            }
            if (outFlag && out.size() > 0) {
                in.push(out.pop());
            }
        }
        return value;
    }

    /**
     * Метод добавления нового элемента в очередь.
     * @param value новый элемент, для добавления
     *              в очередь.
     */
    public void push(final T value) {
        in.push(value);
    }
}
