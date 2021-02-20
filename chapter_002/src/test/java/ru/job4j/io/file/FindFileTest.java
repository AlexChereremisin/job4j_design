package ru.job4j.io.file;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;
import org.apache.commons.io.FileUtils;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.util.StringJoiner;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class FindFileTest {
    public File startDir = null;
    public File outputFile = null;

    @Rule
    public final TemporaryFolder folder = new TemporaryFolder();

    /*
    Для тестов создаем следующую структуру:

    temporary folder
        |
        -startDir
        |   |
        |   -inner1
        |   |   |
        |   |   -inner11
        |   |   |   |
        |   |   |   -test.txt
        |   |   |   -test1.txt
        |   |   -test.txt
        |   -inner2
        |   |   |
        |   |   -test1.txt
        |   -test.txt
        -output.txt
     */
    @Before
    public void createFileStructure() {
        try {
            startDir = folder.newFolder("startDir");
            outputFile = folder.newFile("output.txt");
            File sFile = new File(startDir, "test.txt");
            sFile.createNewFile();
            File inner1 = new File(startDir.getAbsolutePath()+"/inner1");
            File inner2 = new File(startDir.getAbsolutePath()+"/inner2");
            sFile = new File(inner1, "test.txt");
            inner1.mkdir();
            sFile.createNewFile();
            sFile = new File(inner2, "test1.txt");
            inner2.mkdir();
            sFile.createNewFile();
            File inner11 = new File(startDir.getAbsolutePath()+"/inner1/inner11");
            sFile = new File(inner11, "test.txt");
            inner11.mkdir();
            sFile.createNewFile();
            sFile = new File(inner11, "test1.txt");
            sFile.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @After
    public void deleteFolder() {
        this.folder.delete();
    }

    @Test
    public void whenEnterFiveArgumentsThenGetInfoMessage() {
        try (ByteArrayOutputStream baos = new ByteArrayOutputStream();
                PrintStream ps = new PrintStream(baos)
        ) {
            System.setOut(ps);
            String[] args = {"-d=test", "-n=test", "-t=test", "-o=test", "test"};
            new Engine(args);
            ps.flush();
            String result = baos.toString("UTF8");
            String expected = "Передайте праввильное количество ключей" + System.lineSeparator();
            assertThat(result.equals(expected), is(true));
        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void whenEnterThreeArgumentsThenGetInfoMessage() {
        try (ByteArrayOutputStream baos = new ByteArrayOutputStream();
             PrintStream ps = new PrintStream(baos)
        ) {
            System.setOut(ps);
            String[] args = {"-d=test", "-n=test", "-t=test"};
            new Engine(args);
            ps.flush();
            String result = baos.toString("UTF8");
            String expected = "Передайте праввильное количество ключей" + System.lineSeparator();
            assertThat(result.equals(expected), is(true));
        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void whenEnterWrongOrderThenGetInfoMessage() {
        try (ByteArrayOutputStream baos = new ByteArrayOutputStream();
             PrintStream ps = new PrintStream(baos)
        ) {
            System.setOut(ps);
            String[] args = {"-o=test", "-d=test", "-n=test", "-t=test"};
            new Engine(args);
            ps.flush();
            String result = baos.toString("UTF8");
            String expected = "Этот ключ -o находится не на своем месте, или допущена ошибка."
                    + System.lineSeparator() +
                    "Ожидался ключ -d."
                    + System.lineSeparator();
            assertThat(result.equals(expected), is(true));
        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void whenEnterWrongKeyThenGetInfoMessage() {
        try (ByteArrayOutputStream baos = new ByteArrayOutputStream();
             PrintStream ps = new PrintStream(baos)
        ) {
            System.setOut(ps);
            String[] args = {"-d=test", "-b=test", "-t=test", "-o=test"};
            new Engine(args);
            ps.flush();
            String result = baos.toString("UTF8");
            String expected = "Этот ключ -b находится не на своем месте, или допущена ошибка."
                    + System.lineSeparator() +
                    "Ожидался ключ -n."
                    + System.lineSeparator();
            assertThat(result.equals(expected), is(true));
        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void whenEnterHelpKeyThenPrintHelpInfo() {
        try (ByteArrayOutputStream baos = new ByteArrayOutputStream();
             PrintStream ps = new PrintStream(baos)
        ) {
            System.setOut(ps);
            String[] args = {"-h"};
            new Engine(args);
            ps.flush();
            String result = baos.toString("UTF8");
            StringJoiner expected = new StringJoiner(System.lineSeparator());
            expected.add("<+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++>");
            expected.add("Описание:");
            expected.add("\tДанная программа предназначена для поиска файлов в заданном");
            expected.add("\tкаталоге с последующей записью результата в указанный файл.");
            expected.add("<+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++>");
            expected.add("Общий вид использования:");
            expected.add("\tjava -jar find.jar -d=дирректория -n=файл -t=тип_поиска -o=результат");
            expected.add("\tjava -jar find.jar -h");
            expected.add("<+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++>");
            expected.add("Ключи:");
            expected.add("\t-d - директория, в которой начинать поиск.");
            expected.add("\t-h - вывести справку по ключам.");
            expected.add("\t-n - имя файла, маска, либо регулярное выражение.");
            expected.add("\t-t - тип поиска: mask искать по маске,");
            expected.add("\t\t\t\t\t\sname по полному совпадение имени,");
            expected.add("\t\t\t\t\t\sregex по регулярному выражению.");
            expected.add("\t-o - результат записать в файл.");
            expected.add("<+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++>");
            expected.add("");
            assertThat(result.equals(expected.toString()), is(true));
        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void whenEnterWrongTypeSearchThenGetIngoMessage() {
        try (ByteArrayOutputStream baos = new ByteArrayOutputStream();
             PrintStream ps = new PrintStream(baos)
        ) {
            System.setOut(ps);
            String[] args = {"-d=test", "-n=test", "-t=mosk", "-o=test"};
            new Engine(args);
            ps.flush();
            String result = baos.toString("UTF8");
            String expected = "Неверный тип поиска!"
                    + System.lineSeparator() +
                    "Ожидается одно из следующих значений: name, mask, regex."
                    + System.lineSeparator();
            assertThat(result.equals(expected), is(true));
        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void whenNameSearchThanGetOutputFile() {
        String startPath = startDir.getAbsolutePath();
        String dir = "-d=" + startPath;
        String out = "-o=" + outputFile.getAbsolutePath();
        String[] args = {dir, "-n=test1.txt", "-t=name", out};
        new Engine(args);
        try {
            String result = FileUtils.readFileToString(outputFile, StandardCharsets.UTF_8);
            String expected = startPath + "/inner1/inner11/test1.txt"
                    + System.lineSeparator() +
                    startPath + "/inner2/test1.txt";
            assertThat(result.equals(expected), is(true));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void whenMaskSearchThanGetOutputFile() {
        String startPath = startDir.getAbsolutePath();
        String dir = "-d=" + startPath;
        String out = "-o=" + outputFile.getAbsolutePath();
        String[] args = {dir, "-n=test?.txt", "-t=mask", out};
        new Engine(args);
        try {
            String result = FileUtils.readFileToString(outputFile, StandardCharsets.UTF_8);
            String expected = startPath + "/inner1/inner11/test1.txt"
                    + System.lineSeparator() +
                    startPath + "/inner2/test1.txt";
            assertThat(result.equals(expected), is(true));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void whenRegExSearchThanGetOutputFile() {
        String startPath = startDir.getAbsolutePath();
        String dir = "-d=" + startPath;
        String out = "-o=" + outputFile.getAbsolutePath();
        String[] args = {dir, "-n=test[0-9]\\.t.t", "-t=regex", out};
        new Engine(args);
        try {
            String result = FileUtils.readFileToString(outputFile, StandardCharsets.UTF_8);
            String expected = startPath + "/inner1/inner11/test1.txt"
                    + System.lineSeparator() +
                    startPath + "/inner2/test1.txt";
            assertThat(result.equals(expected), is(true));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}