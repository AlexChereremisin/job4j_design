package ru.job4j.io.consolechat;

import org.junit.*;
import org.junit.rules.TemporaryFolder;

import java.io.*;
import java.nio.file.Path;
import static org.junit.Assert.*;

public class ConsoleChatTest {
    @Rule
    public final TemporaryFolder folder = new TemporaryFolder();

    @After
    public void deleteFolder() {
        this.folder.delete();
    }

    @Test
    public void whenWordAndOUTAndOneAnswerThanGiveOneAnswerBetweenTwoWords() {
        String userWords = "Привет" + System.lineSeparator() + "закончить";
        try (BufferedInputStream bis = new BufferedInputStream(
                new ByteArrayInputStream(userWords.getBytes()));
             PrintStream ps = new PrintStream(new ByteArrayOutputStream())
        ) {
            Path answer = Path.of(this.folder.newFile("botAnswer_1.txt").getAbsolutePath());
            FileHelper.writeFile(answer, "БОТ");
            Path log = Path.of(this.folder.newFile("log_1.txt").getAbsolutePath());
            System.setIn(bis);
            System.setOut(ps);
            ConsoleChat chat = new ConsoleChat(log.toString(), answer.toString());
            chat.run();
            FileHelper.readFile(log);
            String[] result = FileHelper.readFile(log).split(System.lineSeparator());
            String[] expected = {"Привет", "БОТ", "закончить"};
            assertArrayEquals(expected, result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void whenOneAnswerAndOUTWhenWordAnswerOUT() {
        String userWords = "Привет" + System.lineSeparator() + "стоп" + System.lineSeparator() + "закончить";
        try (BufferedInputStream bis = new BufferedInputStream(
                new ByteArrayInputStream(userWords.getBytes()));
             PrintStream ps = new PrintStream(new ByteArrayOutputStream())
        ) {
            Path answer = Path.of(this.folder.newFile("botAnswer_1.txt").getAbsolutePath());
            FileHelper.writeFile(answer, "БОТ");
            Path log = Path.of(this.folder.newFile("log_1.txt").getAbsolutePath());
            System.setIn(bis);
            System.setOut(ps);
            ConsoleChat chat = new ConsoleChat(log.toString(), answer.toString());
            chat.run();
            String[] result = FileHelper.readFile(log).split(System.lineSeparator());
            String[] expected = {"Привет", "БОТ", "стоп", "закончить"};
            assertArrayEquals(expected, result);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @Test
    public void whenOneAnswerAndOUTAndCONTINUEWhenWordAnswerOUT() {
        String userWords =
                "Привет" + System.lineSeparator() +
                "стоп" + System.lineSeparator() +
                "ау" + System.lineSeparator() +
                "" + System.lineSeparator() +
                "продолжить" + System.lineSeparator() +
                "закончить";
        try (BufferedInputStream bis = new BufferedInputStream(
                new ByteArrayInputStream(userWords.getBytes()));
             PrintStream ps = new PrintStream(new ByteArrayOutputStream())
        ) {
            Path answer = Path.of(this.folder.newFile("botAnswer_1.txt").getAbsolutePath());
            FileHelper.writeFile(answer, "БОТ");
            Path log = Path.of(this.folder.newFile("log_1.txt").getAbsolutePath());
            System.setIn(bis);
            System.setOut(ps);
            ConsoleChat chat = new ConsoleChat(log.toString(), answer.toString());
            chat.run();
            String[] result = FileHelper.readFile(log).split(System.lineSeparator());
            String[] expected = {"Привет", "БОТ", "стоп", "ау", "", "продолжить", "БОТ", "закончить"};
            assertArrayEquals(expected, result);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}