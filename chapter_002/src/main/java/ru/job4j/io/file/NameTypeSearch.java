package ru.job4j.io.file;

import java.nio.file.Path;

/**
 * Класс сравнения файла по заданному имени.
 */
public class NameTypeSearch extends Search {
    /**
     * Метод сравнения файла и заданного имени.
     *
     * @param name    файл.
     * @param pattern заданное имя.
     * @return true если имя файла совпадает с заданным именем,
     * false если не совпадает.
     */
    @Override
    boolean matching(Path name, String pattern) {
        return name.getFileName().toString().equals(pattern);
    }
}
