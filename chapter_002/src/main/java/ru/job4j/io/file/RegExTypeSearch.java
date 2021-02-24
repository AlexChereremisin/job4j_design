package ru.job4j.io.file;

import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.PathMatcher;

/**
 * Класс сравнения файла по заданному регулярному выражению.
 */
public class RegExTypeSearch extends Search {
    /**
     * Метод сравнения файла заданному регулярному выражению.
     *
     * @param name    файл.
     * @param pattern заданное регулярное выражение.
     * @return true если имя файла соответствует регулярному выражению,
     * false если не соответствует.
     */
    @Override
    boolean matching(Path name, String pattern) {
        PathMatcher matcher = FileSystems
                .getDefault()
                .getPathMatcher("regex:" + pattern);
        return matcher.matches(name.getFileName());
    }
}
