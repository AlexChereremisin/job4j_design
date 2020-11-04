package ru.job4j.io;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class AnalyzeTest {
    @Rule
    public TemporaryFolder folder = new TemporaryFolder();

    @Test
    public void whenDiapasonExistsThenReadTarget() throws IOException {
        File target = folder.newFile("target_test.txt");
        Analyze analyze = new Analyze();
        analyze.unavailable(
                "./data/source_test.log",
                target.getAbsolutePath()
        );
        try (
                BufferedReader reader = new BufferedReader(
                        new FileReader(target.getAbsolutePath())
                )
        ) {
            assertThat(reader.readLine(), is("10:57:01;10:59:01"));
            assertThat(reader.readLine(), is("11:01:02;11:02:02"));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}