package ru.job4j.io;

import org.junit.Test;

import java.io.BufferedReader;
import java.io.FileReader;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class AnalyzeTest {
    @Test
    public void whenDiapasonExistsThenReadTarget() {
        Analyze analyze = new Analyze();
        analyze.unavailable(
                "./data/source_test.log",
                "./data/target_test.txt"
        );
        try (
                BufferedReader reader = new BufferedReader(
                        new FileReader(
                                "./data/target_test.txt")
                )
        ) {
            assertThat(reader.readLine(), is("10:57:01;10:59:01"));
            assertThat(reader.readLine(), is("11:01:02;11:02:02"));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}