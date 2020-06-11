package ru.job4j.collection.list;

/**
 * Реализация структуры данных Steak.
 * @param <T> любой ссылочный тип.
 */
public final class SimpleStack<T> {
    /**
     * Контейнер на базе односвязного списка.
     */
    private final ForwardLinked<T> linked = new ForwardLinked<T>();

    /**
     * Метод возвращает последний элемент коллекции
     * и удаляет его.
     * Если элементов нет, то бросаем
     * исключение NoSuchElementException().
     * @return последний элемент колекции.
     */
    public T pop() {
        return linked.deleteLast();
    }

    /**
     * Метод добавления нового элемента.
     * Элементы добавляются в конец коллекции.
     * @param value новый элемент, для добавления в коллекцию.
     */
    public void push(final T value) {
        linked.add(value);
    }
}
