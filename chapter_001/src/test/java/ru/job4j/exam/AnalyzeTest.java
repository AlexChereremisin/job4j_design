package ru.job4j.exam;

import java.util.List;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class AnalyzeTest {
    @Test
    public void whenHaveTwoArraysThenGetInfo() {
        Analyze analyze = new Analyze();
        Analyze.User user1 = new Analyze.User();
        user1.id = 1;
        user1.name = "Andrey";
        Analyze.User user10 = new Analyze.User();
        user10.id = 10;
        user10.name = "Aleksandr";
        Analyze.User user13 = new Analyze.User();
        user13.id = 13;
        user13.name = "Anton";
        Analyze.User user4 = new Analyze.User();
        user4.id = 4;
        user4.name = "Artem";
        Analyze.User user5 = new Analyze.User();
        user5.id = 5;
        user5.name = "Aleksey";
        Analyze.User user6 = new Analyze.User();
        user6.id = 6;
        user6.name = "Arseniy";
        Analyze.User userCh4 = new Analyze.User();
        userCh4.id = 4;
        userCh4.name = "Denis";
        Analyze.Info info = analyze.diff(
                List.of(user1, user10, user13, user4, user5),
                List.of(user1, user13, userCh4, user6)
        );
        assertThat(info.added, is(1));
        assertThat(info.changed, is(1));
        assertThat(info.deleted, is(2));
    }
}