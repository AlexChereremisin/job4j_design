package ru.job4j.io.file;

import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.PathMatcher;

/**
 * Класс сравнения файла по заданной маске.
 */
public class MaskTypeSearch extends Search {
    /**
     * Метод сравнения файла заданной маске.
     *
     * @param name    файл.
     * @param pattern заданная маска.
     * @return true если имя файла соответствует заданной маске,
     * false если не соответствует.
     */
    @Override
    boolean matching(Path name, String pattern) {
        PathMatcher matcher = FileSystems
                .getDefault()
                .getPathMatcher("glob:" + pattern);
        return matcher.matches(name.getFileName());
    }
}
