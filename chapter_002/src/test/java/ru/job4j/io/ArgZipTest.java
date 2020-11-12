package ru.job4j.io;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class ArgZipTest {
    @Test (expected = IllegalArgumentException.class)
    public void whenAddNullThenException() {
        new ArgZip(null);
    }

    @Test (expected = IllegalArgumentException.class)
    public void whenLength2ThenException() {
        new ArgZip(new String[2]);
    }

    @Test (expected = IllegalArgumentException.class)
    public void whenLength4ThenException() {
        new ArgZip(new String[4]);
    }

    @Test (expected = IllegalArgumentException.class)
    public void whenAddWrongKeyDThenException() {
        String[] str = {"-dDIRECTORY", "e=EXCLUDE", "-out=OUTPUT"};
        new ArgZip(str);
    }

    @Test (expected = IllegalArgumentException.class)
    public void whenAddWrongKeyEThenException() {
        String[] str = {"e=EXCLUDE", "-dDIRECTORY", "-out=OUTPUT"};
        new ArgZip(str);
    }

    @Test (expected = IllegalArgumentException.class)
    public void whenAddWrongKeyOThenException() {
        String[] str = {"-out=OUTPUT", "-dDIRECTORY", "e=EXCLUDE"};
        new ArgZip(str);
    }

    @Test
    public void whenAddParametersThanGetIt() {
        String[] str = {"-d=DIRECTORY", "-e=EXCLUDE", "-o=OUTPUT"};
        ArgZip argZip = new ArgZip(str);
        assertThat(argZip.directory(), is("DIRECTORY"));
        assertThat(argZip.exclude(), is("EXCLUDE"));
        assertThat(argZip.output(), is("OUTPUT"));
    }
}