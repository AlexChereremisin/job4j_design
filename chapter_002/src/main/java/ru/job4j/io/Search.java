package ru.job4j.io;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

/**
 * Класс поиска файла по предикату.
 */
public class Search {
    /**
     * Точка входа в программу.
     * @param args массив строк.
     * @throws IOException исключение ввода-вывода.
     */
    public static void main(String[] args) throws IOException {
        Path start = Paths.get(".");
        search(start, "properties").forEach(System.out::println);
    }

    /**
     * Метод поиска файла по предикату.
     * @param root путь по которому ищем файл.
     * @param ext выражение, которое будем искать.
     * @return список путей до найденных файлов.
     * @throws IOException исключение ввода-вывода.
     */
    public static List<Path> search(Path root, String ext) throws IOException {
        SearchFiles searcher = new SearchFiles(p -> p.toFile().getName().endsWith(ext));
        Files.walkFileTree(root, searcher);
        return searcher.getPaths();
    }
}
