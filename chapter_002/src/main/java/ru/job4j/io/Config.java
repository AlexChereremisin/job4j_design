package ru.job4j.io;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringJoiner;
import java.util.regex.Pattern;

/**
 * Класс чтения настроек из файла .properties.
 */
public class Config {
    /**
     * Путь до файла настроек.
     */
    private final String path;
    /**
     * Внутренний контейнер для ключ=значение из конфигурационного файла.
     */
    private final Map<String, String> values = new HashMap<String, String>();

    /**
     * Конмтруктор для инициализации поля path.
     * @param path путь до файла конфигурации.
     */
    public Config(final String path) {
        this.path = path;
    }

    /**
     * Метод заполнения value значениями из файла.
     */
    public void load() {
        try (BufferedReader read = new BufferedReader(new FileReader(this.path))) {
            read.lines().forEach(s -> {
                if (this.isPair(s)) {
                    String[] arr = s.split("=");
                    this.values.put(arr[0], arr[1]);
                }
            });
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Метод проверки, что строка является комментарием.
     * @param str строка из файла.
     * @return true - если это ключ-значение, false - комментарий.
     */
    private boolean isPair(String str) {
        Pattern comment = Pattern.compile("^#");
        return !comment.matcher(str).find();
    }

    /**
     * Метод получения значения по ключу.
     * @param key ключ.
     * @return значение по ключу.
     */
    public String value(String key) {
        return this.values.get(key);
    }

    /**
     * Переопределенный метод toString, для чтения из файла строк.
     * @return строка со всем содержимым файла.
     */
    @Override
    public String toString() {
        StringJoiner out = new StringJoiner(System.lineSeparator());
        try (BufferedReader read = new BufferedReader(new FileReader(this.path))) {
            read.lines().forEach(out::add);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return out.toString();
    }

    /**
     * Точка входа в программу.
     * @param args массив строк.
     */
    public static void main(String[] args) {
        System.out.println(new Config("./chapter_002/data/app.properties"));
    }
}
