package ru.job4j.collection.map;

import org.junit.Test;

import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

public class UserTest {
    @Test
    public void whenTwoObjMapHasTwoObj() {
        User first = new User("Alexey", 1, new GregorianCalendar(1990, 3, 15));
        User second = new User("Alexey", 1, new GregorianCalendar(1990, 3, 15));

        System.out.println(first.equals(second));
        System.out.println(first.hashCode());
        System.out.println(second.hashCode());
        System.out.println((float) (32 >>> 16));
        Map<User, Object> map = new HashMap<>();
        map.put(first, new Object());
        map.put(second, new Object());
        System.out.println(map);
    }
}