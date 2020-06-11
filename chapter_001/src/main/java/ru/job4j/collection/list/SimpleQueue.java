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
        T value = out.pop();
        popAndPushFirst(in, value, true);
        return value;
    }

    /**
     * Метод добавления нового элемента в очередь.
     * @param value новый элемент, для добавления
     *              в очередь.
     */
    public void push(final T value) {
        in.push(value);
        popAndPushFirst(out, value, false);
    }

    /**
     * Метод удаляет или добавляет элемент в начало
     * указанного стека.
     * @param stack стек в котором надо удалить или
     *              добавить элемент в начало.
     * @param value элемент для добавления или удаления.
     * @param isPop true если хотим удалить первый элемент стека,
     *              false - для добавления элемента в начало стека.
     */
    private void popAndPushFirst(
            final SimpleStack<T> stack,
            final T value,
            final boolean isPop
    ) {
        SimpleStack<T> tmp = new SimpleStack<>();
        while (stack.size() > 0) {
            tmp.push(stack.pop());
        }
        if (isPop) {
            tmp.pop();
        } else {
            tmp.push(value);
        }
        while (tmp.size() > 0) {
            stack.push(tmp.pop());
        }
    }
}
