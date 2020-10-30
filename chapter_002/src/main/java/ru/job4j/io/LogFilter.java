package ru.job4j.io;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

/**
 * Класс для демонстрации работы с буфферезированым потоком.
 */
public class LogFilter {
    /**
     * Метод поиска в лог-файле строки.
     * Шаблон : предпоследнее число должно быть 404.
     * @param file лог-файл.
     * @return список найденных по шаблону строк.
     */
    public static List<String> filter(String file) {
        List<String> rsl = new ArrayList<>();
        try (BufferedReader in = new BufferedReader(new FileReader("log.txt"))) {
            String patternStr = ".*\"\s404.*$";
            Pattern pattern = Pattern.compile(patternStr);
            in.lines().forEach((line) -> {
                if (pattern.matcher(line).find()) {
                    rsl.add(line);
                }
            });
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return rsl;
    }

    /**
     * Метод записи списка строк в лог-файл.
     * @param log список строк для записи.
     * @param file имя файла.
     */
    public static void save(List<String> log, String file) {
        try (PrintWriter writer = new PrintWriter(
                new BufferedOutputStream(
                        new FileOutputStream(file)))
        ) {
            StringBuilder str = new StringBuilder();
            for (String s : log) {
                str.append(s).append("\n");
            }
            writer.write(str.toString());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Точка входа в программу.
     * @param args массив строк.
     */
    public static void main(String[] args) {
        List<String> log = filter("log.txt");
        save(log, "404.txt");
    }
}
