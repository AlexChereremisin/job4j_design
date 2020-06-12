package ru.job4j.collection.list;

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
        while (in.size() > 0) {
            out.push(in.pop());
        }
        T value = out.pop();
        while (out.size() > 0) {
            in.push(out.pop());
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
