package ru.job4j.io.file;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.StringJoiner;

/**
 * Класс поиска файла в заданном каталоге.
 */
public abstract class Search {
    /**
     * Результаты поиска.
     */
    private final StringJoiner searchResult = new StringJoiner(System.lineSeparator());

    /**
     * Метод записи результата поиска в файл.
     * @param output путь до файла.
     */
    private void writeResultToFile(Path output) {
        try (
            BufferedWriter bw = new BufferedWriter(
                new FileWriter(
                    output.toString(),
                    StandardCharsets.UTF_8,
                    false
                )
            )
        ) {
            bw.write(searchResult.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Иетод обхода содержимого папки и подпапок.
     * @param d начальная папка.
     * @param n имя, маска или регулярное выражение.
     * @param o путь до файла с результатами поиска.
     */
    final void run(String d, String n, String o) {
        Path startDir = Paths.get(d);
        try {
            Files.walkFileTree(startDir, new SimpleFileVisitor<Path>() {
                /*
                 * Метод проверки соответствия файла заданным критериям поиска.
                 * @param file путь до файла.
                 */
                void find(Path file) {
                    Path name = file.getFileName();
                    if (name != null && matching(name, n)) {
                        searchResult.add(file.toAbsolutePath().toString());
                    }
                }
                @Override
                public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) {
                    find(file);
                    return FileVisitResult.CONTINUE;
                }

                @Override
                public FileVisitResult visitFileFailed(Path file, IOException exc) {
                    System.err.println(exc);
                    return FileVisitResult.CONTINUE;
                }
            });
            writeResultToFile(Path.of(o));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Метод сравнения имени файла критериям поиска.
     * @param name путь до файла.
     * @param pattern имя, маска или регулярное выражение.
     * @return true если имя файла соответствует критериям,
     * false если не соответствует.
     */
    abstract boolean matching(Path name, String pattern);
}
