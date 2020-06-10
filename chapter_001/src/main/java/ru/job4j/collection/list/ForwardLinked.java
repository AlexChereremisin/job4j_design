package ru.job4j.collection.list;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Реализация односвязного списка.
 * @param <T> любой ссылочный тип.
 */
public final class ForwardLinked<T> implements Iterable<T> {
    /**
     * Ссылка на первый элемент списка.
     */
    private Node<T> head;

    /**
     * Метод добавления нового элемента в список.
     * @param value новый элемент списка.
     */
    public void add(final T value) {
        Node<T> node = new Node<T>(value, null);
        if (head == null) {
            head = node;
            return;
        }
        Node<T> tail = head;
        while (tail.next != null) {
            tail = tail.next;
        }
        tail.next = node;
    }

    /**
     * Метод удаления первого элемента в списке.
     * Если элементов в списке нет, то бросаем
     * исключение NoSuchElementException().
     */
    public void deleteFirst() {
        if (head == null) {
            throw new NoSuchElementException();
        }
        Node<T> newHead = head.next;
        head.next = null;
        head = newHead;
    }

    /**
     * Метод возвращает итератор по структуре.
     * @return итератор для данной структуры.
     */
    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            /**
             * Ссылка на первый элемент структуры.
             */
            private Node<T> node = head;

            /**
             * Метод проверяет, есть ли еще элементы.
             * @return true, если элементы есть,
             * иначе false.
             */
            @Override
            public boolean hasNext() {
                return node != null;
            }

            /**
             * Метод возвращает следующий элемент структуры.
             * @return следующий элемент структуры.
             */
            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                T value = node.value;
                node = node.next;
                return value;
            }
        };
    }

    /**
     * Класс односвязного списка.
     * @param <T> любой ссылочный тип.
     */
    private static class Node<T> {
        /**
         * Ссылка на элемент.
         */
        private final T value;
        /**
         * Ссылка на следующий элемент.
         */
        private Node<T> next;

        /**
         * Конструктор.
         * @param newValue ссылка на элемент.
         * @param newNext ссылка на следующий элемент.
         */
        Node(final T newValue, final Node<T> newNext) {
            this.value = newValue;
            this.next = newNext;
        }
    }
}