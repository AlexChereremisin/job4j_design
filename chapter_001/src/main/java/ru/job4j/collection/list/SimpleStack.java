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
     * Размер стека.
     */
    private int size;

    /**
     * Метод возвращает последний элемент коллекции
     * и удаляет его.
     * Если элементов нет, то бросаем
     * исключение NoSuchElementException().
     * @return последний элемент колекции.
     */
    public T pop() {
        T value = linked.deleteLast();
        size--;
        return value;
    }

    /**
     * Метод добавления нового элемента.
     * Элементы добавляются в конец коллекции.
     * @param value новый элемент, для добавления в коллекцию.
     */
    public void push(final T value) {
        size++;
        linked.add(value);
    }

    /**
     * Геттер поля size.
     * @return размер стека.
     */
    public int size() {
        return size;
    }
}
