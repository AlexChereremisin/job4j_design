package ru.job4j.collection.list;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

/**
 * Простая реализация связного списка.
 * @param <E> элемент списка, ссылочного типа.
 */
public final class SimpleLinkedList<E> implements Iterable<E> {
    /**
     * Сссылка на первый элемент списка.
     */
    private Node<E> first;
    /**
     * Ссылка на последний элемент списка.
     */
    private Node<E> last;
    /**
     * Размер списка.
     */
    private int size;
    /**
     * Счетчик изменений внутреннего массива.
     */
    private int modCount;

    /**
     * Метод добавления нового элемента в список.
     * @param value новый элемент, для добавления списка.
     */
    public void add(final E value) {
        Node<E> node = last;
        Node<E> newNode = new Node<>(node, value, null);
        last = newNode;
        if (node == null) {
            first = newNode;
        } else {
            node.next = newNode;
        }
        size++;
        modCount++;
    }

    /**
     * Метод возвращает элемент по индексу.
     * @param index индекс, по которому ищем элемент.
     * @return элемент найденный по индексу, иначе null.
     */
    public E get(final int index) {
        Objects.checkIndex(index, size);
        Node<E> node;
        if (index < (size >> 1)) {
            node = first;
            for (int i = 0; i < index; i++) {
                node = node.next;
            }
        } else {
            node = last;
            for (int i = size - 1; i > index; i--) {
                node = node.prev;
            }
        }
        return node.item;
    }

    /**
     * Метод возвращает итератор по структуре.
     * @return итератор по структуре.
     */
    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            /**
             * Указатель на следующий элемент.
             */
            private int pointer;
            /**Поле для хранения счетчика изменений структуры.
             *
             */
            private final int expectedModCount = modCount;

            /**
             * Метод проверяет изменилась, ли структура или нет.
             * @return true, если изменений структуры нет,
             * иначе бросается исключение ConcurrentModificationException.
             */
            private boolean isContainerMod() {
                boolean rsl = expectedModCount == modCount;
                if (!rsl) {
                    throw new ConcurrentModificationException();
                }
                return rsl;
            }

            /**
             * Проверка, есть ли следующий элемент.
             * @return true, если элементы есть, иначе - false.
             */
            @Override
            public boolean hasNext() {
                isContainerMod();
                return pointer < size;
            }

            /**
             * Возвращает следующий элемент.
             * @return следующий элемент, если он есть.
             * Иначе бросается исключение типа NoSuchElementException.
             */
            @Override
            public E next() {
                isContainerMod();
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return get(pointer);
            }
        };
    }

    /**
     * Связанный список.
     * @param <E> элемент связного списка.
     */
    private static class Node<E> {
        /**
         * Элемент списка.
         */
        private E item;
        /**
         * Ссылка на следующий элемент.
         */
        private Node<E> next;
        /**
         * Ссылка на предыдущий элемент.
         */
        private Node<E> prev;

        /**
         * Конструктор.
         * @param newPrev предыдущий элемент.
         * @param element элемент списка.
         * @param newNext следующий элемент.
         */
        Node(final Node<E> newPrev, final E element, final Node<E> newNext) {
        this.item = element;
        this.next = newNext;
        this.prev = newPrev;
        }
    }
}
