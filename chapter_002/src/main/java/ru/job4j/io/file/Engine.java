package ru.job4j.io.file;

import java.util.HashMap;
import java.util.Map;
import java.util.StringJoiner;

/**
 * Класс для выбора типа поиска.
 * Типы поиска:
 * -> по имени
 * -> по маске
 * -> по регулярному выражению
 */
public class Engine {
    /**
     * Карта соответствий переданных параметров типам поиска.
     */
    private final Map<String, Search> typeMap = new HashMap<>();

    {
        typeMap.put("name", new NameTypeSearch());
        typeMap.put("mask", new MaskTypeSearch());
        typeMap.put("regex", new RegExTypeSearch());
    }

    /**
     * Конструктор класса.
     * Внутри происходит вызов валидации ключей.
     *
     * @param keys параметры поиска и записи в файл.
     */
    public Engine(String[] keys) {
        this.validateKeys(keys);
    }

    /**
     * Метод выбора типа поиска из карты typeMap.
     *
     * @param param параметры поиска.
     */
    private void runTypeSearch(String[] param) {
        Search type = this.typeMap.get(param[2]);
        if (type == null) {
            System.out.println("Неверный тип поиска!");
            System.out.println("Ожидается одно из следующих значений: name, mask, regex.");
            return;
        }
        type.run(param[0], param[1], param[3]);
    }

    /**
     * Метод формирования и вывода справки в консоль.
     */
    private void showHelp() {
        StringJoiner help = new StringJoiner(System.lineSeparator());
        help.add("<+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++>");
        help.add("Описание:");
        help.add("\tДанная программа предназначена для поиска файлов в заданном");
        help.add("\tкаталоге с последующей записью результата в указанный файл.");
        help.add("<+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++>");
        help.add("Общий вид использования:");
        help.add("\tjava -jar find.jar -d=дирректория -n=файл -t=тип_поиска -o=результат");
        help.add("\tjava -jar find.jar -h");
        help.add("<+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++>");
        help.add("Ключи:");
        help.add("\t-d - директория, в которой начинать поиск.");
        help.add("\t-h - вывести справку по ключам.");
        help.add("\t-n - имя файла, маска, либо регулярное выражение.");
        help.add("\t-t - тип поиска: mask искать по маске,");
        help.add("\t\t\t\t\t\sname по полному совпадение имени,");
        help.add("\t\t\t\t\t\sregex по регулярному выражению.");
        help.add("\t-o - результат записать в файл.");
        help.add("<+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++>");
        System.out.println(help.toString());
    }

    /**
     * Метод валидации ключей.
     *
     * @param keys - ключи полученные из консоли.
     */
    private void validateKeys(String[] keys) {
        if (keys.length == 1 && keys[0].equals("-h")) {
            this.showHelp();
        } else if (keys.length == 4) {
            String[] param = new String[4];
            String[] validKeys = {"-d", "-n", "-t", "-o"};
            for (int i = 0; i < 4; ++i) {
                if (keys[i].startsWith(validKeys[i])) {
                    param[i] = keys[i].split("=")[1];
                } else {
                    System.out.println("Этот ключ " + keys[i].split("=")[0] +
                            " находится не на своем месте, или допущена ошибка.");
                    System.out.println("Ожидался ключ " + validKeys[i] + ".");
                    return;
                }
            }
            this.runTypeSearch(param);
        } else {
            System.out.println("Передайте праввильное количество ключей");
        }
    }
}
