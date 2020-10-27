package ru.job4j.collection.tree;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.Queue;

/**
 * Реализация простого интерфейса простого дерева.
 * @param <E> любой ссылочный тип.
 */
public class Tree<E> implements SimpleTree<E> {
    /**
     * Кореневой узел.
     */
    private final Node<E> root;

    /**
     * Конструктор инициализирующий root.
     * @param root корневой узел.
     */
    Tree(final E root) {
        this.root = new Node<>(root);
    }

    /**
     * Метод получения списка потомков у родительского узла.
     * @param parent родительский узел.
     * @return Optional список всех потомков данного родительского узла.
     */
    public Optional<List<Node<E>>> getChildren(E parent) {
        Optional<List<Node<E>>> rsl = Optional.empty();
        Optional<Node<E>> optional = this.findBy(parent);
        if (optional.isPresent()) {
            rsl = Optional.of(optional.get().children);
        }
        return rsl;
    }

    /**
     * Метод добавления нового узла в дерево.
     * @param parent узел родитель.
     * @param child узел потомок.
     * @return true, если узел добавлен в дерево,
     * false, если добавляемый узел дубликат или нет такого родительского узла.
     */
    @Override
    public boolean add(E parent, E child) {
        Optional<Node<E>> optional = this.findBy(parent);
        boolean rsl = optional.isPresent();
        if (rsl) {
            Node<E> node = optional.get();
            if (!node.children.contains(child) && this.findBy(child).isEmpty()) {
                node.children.add(new Node(child));
            }
        }
        return rsl;
    }

    /**
     * Метод поиска по дереву в ширину.
     * @param value значение, которое ужно найти.
     * @return Optional узел если такой есть, иначе Optional.empty().
     */
    @Override
    public Optional<Node<E>> findBy(E value) {
        Optional<Node<E>> rsl = Optional.empty();
        Queue<Node<E>> data = new LinkedList<>();
        data.offer(this.root);
        while (!data.isEmpty()) {
            Node<E> el = data.poll();
            if (el.value.equals(value)) {
                rsl = Optional.of(el);
                break;
            }
            data.addAll(el.children);
        }
        return rsl;
    }
}
