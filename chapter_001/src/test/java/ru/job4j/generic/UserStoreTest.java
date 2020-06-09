package ru.job4j.generic;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class UserStoreTest {
    @Test
    public void whenAddTwoUsersThenFindById() {
        UserStore userStore = new UserStore();
        User user1 = new User("123");
        User user2 = new User("456");
        userStore.add(user1);
        userStore.add(user2);
        assertThat(userStore.findById("123"), is(user1));
        assertThat(userStore.findById("456"), is(user2));
    }

    @Test
    public void whenAddTwoUsersAndReplaceFirstThenFindById() {
        UserStore userStore = new UserStore();
        User user1 = new User("123");
        User user2 = new User("456");
        User user3 = new User("789");
        userStore.add(user1);
        userStore.add(user2);
        userStore.replace("123", user3);
        assertThat(userStore.findById("789"), is(user3));
        assertThat(userStore.findById("456"), is(user2));
        assertNull(userStore.findById("123"));
    }

    @Test
    public void whenAddUserAndDeleteThenNullResultOfFindById() {
        UserStore userStore = new UserStore();
        userStore.add(new User("123"));
        userStore.delete("123");
        assertNull(userStore.findById("123"));
    }
}
