package ru.job4j.collection.list;

import static org.junit.Assert.assertThat;
import static org.hamcrest.Matchers.is;

import org.junit.Test;

import java.util.NoSuchElementException;

public class SimpleStackTest {

    @Test
    public void whenPushThenPop() {
        SimpleStack<Integer> stack = new SimpleStack<>();
        stack.push(1);
        assertThat(stack.pop(), is(1));
    }

    @Test
    public void whenPushPopThenPushPop() {
        SimpleStack<Integer> stack = new SimpleStack<>();
        stack.push(1);
        stack.pop();
        stack.push(2);
        assertThat(stack.pop(), is(2));
    }

    @Test
    public void whenPushPushThenPopPop() {
        SimpleStack<Integer> stack = new SimpleStack<>();
        stack.push(1);
        stack.push(2);
        stack.pop();
        assertThat(stack.pop(), is(1));
    }

    @Test(expected = NoSuchElementException.class)
    public void whenPopEmptyStackThenException() {
        SimpleStack<String> stack = new SimpleStack<>();
        stack.pop();
    }

    @Test(expected = NoSuchElementException.class)
    public void whenPushPopPopThenException() {
        SimpleStack<String> stack = new SimpleStack<>();
        stack.push("test1");
        stack.pop();
        stack.pop();
    }

    @Test(expected = NoSuchElementException.class)
    public void whenMultiPushMultiPopThenException() {
        SimpleStack<String> stack = new SimpleStack<>();
        stack.push("test1");
        stack.push("test2");
        stack.pop();
        stack.pop();
        stack.pop();
    }

    @Test
    public void whenPushThanSize1() {
        SimpleStack<Integer> stack = new SimpleStack<>();
        stack.push(1);
        assertThat(stack.size(), is(1));
    }

    @Test
    public void whenPushPopThanSize0() {
        SimpleStack<Integer> stack = new SimpleStack<>();
        stack.push(1);
        stack.pop();
        assertThat(stack.size(), is(0));
    }

    @Test
    public void whenEmptyStackThanSize0() {
        SimpleStack<Integer> stack = new SimpleStack<>();
        assertThat(stack.size(), is(0));
    }
}
