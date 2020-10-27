package ru.job4j.collection.tree;

import org.junit.Test;

import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;


public class TreeTest {
    @Test
    public void whenTreeIsBinaryThenTrue() {
        Tree<Integer> tree = new Tree<>(1);
        tree.add(1, 2);
        tree.add(1, 3);
        tree.add(2, 4);
        tree.add(3, 6);
        tree.add(3, 7);
        tree.add(4, 8);
        tree.add(4, 9);
        tree.add(6, 10);
        tree.add(6, 11);
        tree.add(7, 12);
        tree.add(7, 13);
        assertThat(tree.isBinary(), is(true));
    }

    @Test
    public void whenTreeNotBinaryThenFalse() {
        Tree<Integer> tree = new Tree<>(1);
        tree.add(1, 2);
        tree.add(1, 3);
        tree.add(2, 4);
        tree.add(3, 6);
        tree.add(3, 7);
        tree.add(4, 8);
        tree.add(4, 9);
        tree.add(6, 10);
        tree.add(6, 11);
        tree.add(7, 12);
        tree.add(7, 13);
        tree.add(7, 14);
        assertThat(tree.isBinary(), is(false));
    }

    @Test
    public void whenAddSameDuplicateThanTreeNoDuplicate() {
        Tree<Integer> tree = new Tree<>(1);
        tree.add(1, 2);
        tree.add(1, 2);
        tree.add(1, 3);
        tree.add(3, 4);
        tree.add(3, 2);
        tree.add(3, 4);
        tree.add(4, 5);
        tree.add(5, 2);
        tree.add(5,1);

        Optional<List<SimpleTree.Node<Integer>>> optional = tree.getChildren(1);
        optional.ifPresent(nodes -> {
            assertThat(nodes.size(), is(2));
            assertThat(nodes.get(0).value, is(2));
            assertThat(nodes.get(1).value, is(3));
        });

        optional = tree.getChildren(3);
        optional.ifPresent(nodes -> {
            assertThat(nodes.size(), is(1));
            assertThat(nodes.get(0).value, is(4));
        });

        optional = tree.getChildren(5);
        optional.ifPresent(nodes -> assertThat(nodes.size(), is(0)));
    }

    @Test
    public void when6ElFindLastThen6() {
        Tree<Integer> tree = new Tree<>(1);
        tree.add(1, 2);
        tree.add(1, 3);
        tree.add(1, 4);
        tree.add(4, 5);
        tree.add(5, 6);
        assertThat(
                tree.findBy(6).isPresent(),
                is(true)
        );
    }

    @Test
    public void when6ElFindNotExitThenOptionEmpty() {
        Tree<Integer> tree = new Tree<>(1);
        tree.add(1, 2);
        assertThat(
                tree.findBy(7).isPresent(),
                is(false)
        );
    }
}