package ru.job4j.collection.map;

import org.junit.Test;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.ConcurrentModificationException;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class SimpleHashMapTest {
    @Test(expected = IllegalArgumentException.class)
    public void whenNegativeCapacityThenEx() {
        new SimpleHashMap<>(-1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenLoadFactorIsNanThenEx() {
        new SimpleHashMap<>(32, Float.NaN);
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenWrongLoadFactorThenEx() {
        new SimpleHashMap<>(32, 0f);
    }

    @Test
    public void whenAddNewElementsThenChangeCapacity() {
        SimpleHashMap<Integer, String> map = new SimpleHashMap<>(4);
        map.insert(1, "one");
        map.insert(2, "two");
        map.insert(3, "three");
        map.insert(4, "four");
        assertThat(map.size(), is(4));
    }

    @Test
    public void whenAddThenGet() {
        SimpleHashMap<Integer, String> map = new SimpleHashMap<>();
        map.insert(1, "one");
        assertThat(map.get(1), is("one"));
    }

    @Test
    public void whenAddTwoAndDeleteFirstThenGetNullAndValue() {
        SimpleHashMap<String, Integer> map = new SimpleHashMap<>();
        map.insert("one", 1);
        map.insert("two", 2);
        map.delete("one");
        assertNull(map.get("one"));
        assertThat(map.get("two"), is(2));
    }

    @Test
    public void whenAddNullNullThenAddToMap() {
        SimpleHashMap<Object, Object> map = new SimpleHashMap<>();
        map.insert(null, null);
        assertThat(map.size(), is(1));
        assertNull(map.get(null));
    }

    @Test
    public void whenAddTwoNullElementsThenAddFirstNull() {
        SimpleHashMap<Object, Integer> map = new SimpleHashMap<>();
        map.insert(null, null);
        map.insert(null, 1);
        assertNull(map.get(null));
    }

    @Test
    public void whenAddTwoElementsThenIterate() {
        SimpleHashMap<Integer, String> map = new SimpleHashMap<>();
        map.insert(1, "one");
        map.insert(2, "two");
        Iterator<SimpleHashMap.Node<Integer, String>> iterator = map.iterator();
        int count = 0;
        while (iterator.hasNext()) {
            ++count;
            iterator.next();
        }
        assertThat(count, is(2));
    }

    @Test(expected = NoSuchElementException.class)
    public void whenEmptyIterator() {
        SimpleHashMap<Object, Object> map = new SimpleHashMap<>();
        map.iterator().next();
    }

    @Test(expected = ConcurrentModificationException.class)
    public void  whenCorruptedIt() {
        SimpleHashMap<Integer, String> map = new SimpleHashMap<>();
        map.insert(1, "one");
        Iterator<SimpleHashMap.Node<Integer, String>> iterator = map.iterator();
        map.insert(2, "two");
        iterator.next();
    }
}