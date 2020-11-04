package ru.job4j.io;

import java.io.File;

/**
 * Класс для проверки, что заданный путь это директория.
 */
public class Dir {
    /**
     * Точка входа в программу.
     * @param args массив строк.
     */
    public static void main(String[] args) {
        File file = new File("/home/alexart/IdeaProjects/");
        if (!file.exists()) {
            throw new IllegalArgumentException(String.format("Not exist %s", file.getAbsoluteFile()));
        }
        if (!file.isDirectory()) {
            throw new IllegalArgumentException(String.format("Not directory %s", file.getAbsoluteFile()));
        }
        System.out.println(String.format("size : %s", file.getTotalSpace()));
        for (File subfile : file.listFiles()) {
            System.out.println(String.format(
                    "name:\t%s\tsize:\t%s",
                    subfile.getName(),
                    subfile.getTotalSpace()
            ));
        }
    }
}
