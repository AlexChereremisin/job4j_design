package ru.job4j.collection.tree;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Интерфейс описывающий простое дерево.
 * @param <E> любой ссылочный тип.
 */
public interface SimpleTree<E> {

    /**
     * Метод добавления нового элементав дерево.
     * @param parent узел родитель.
     * @param child узел потомок.
     * @return true - добавление прошло успешно, false - такой потомок уже есть.
     */
    boolean add(E parent, E child);

    /**
     * Метод поиска по дереву заданного значения.
     * @param value значение, которое нужно найти.
     * @return найденное значение.
     */
    Optional<Node<E>> findBy(E value);

    /**
     * Класс описывающий узел.
     * @param <E> любой ссылочный тип.
     */
    class Node<E> {
        /**
         * Значение данного узла.
         */
        final E value;
        /**
         * Список всех узлов потомков.
         */
        final List<Node<E>> children = new ArrayList<>();

        /**
         * Конструктор иницирующий value.
         * @param value значение узла.
         */
        public Node(E value) {
            this.value = value;
        }
    }
}
