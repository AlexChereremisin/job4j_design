package ru.job4j.collection.set;

import org.junit.Test;

import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class SimpleSetTest {
    @Test
    public void whenAddThanIterate() {
        SimpleSet<Integer> set = new SimpleSet<>();
        set.add(1);
        set.add(2);
        Iterator<Integer> it = set.iterator();
        assertThat(it.next(), is(1));
        assertThat(it.next(), is(2));
    }

    @Test
    public void whenAddDuplicateNullThanIterate() {
        SimpleSet<Integer> set = new SimpleSet<>();
        set.add(1);
        set.add(null);
        set.add(null);
        set.add(2);
        Iterator<Integer> it = set.iterator();
        assertThat(it.next(), is(1));
        assertNull(it.next());
        assertThat(it.next(), is(2));
        assertThat(it.hasNext(), is(false));
    }

    @Test
    public void whenAddDuplicateThanIterateNextUniqueElement() {
        SimpleSet<Integer> set = new SimpleSet<>();
        set.add(5);
        set.add(null);
        set.add(7);
        set.add(7);
        set.add(7);
        set.add(null);
        set.add(5);
        Iterator<Integer> it = set.iterator();
        assertThat(it.next(), is(5));
        assertNull(it.next());
        assertThat(it.next(), is(7));
        assertThat(it.hasNext(), is(false));
    }

    @Test(expected = NoSuchElementException.class)
    public void whenEmptySetAndIterateNextThanException() {
        SimpleSet<Integer> set = new SimpleSet<>();
        set.iterator().next();
    }
}
