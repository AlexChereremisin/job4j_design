package ru.job4j.io.file;

/**
 * Класс запуска поиска файла в каталоге с поиском в подпапках
 * и записью результата в файл.
 */
public class FindFile {
    /**
     * Точка входа в программу.
     * @param args - массив строк.
     */
    public static void main(String[] args) {
        new Engine(args);
    }
}
