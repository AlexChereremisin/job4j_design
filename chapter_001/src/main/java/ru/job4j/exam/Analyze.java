package ru.job4j.exam;

import java.util.HashMap;
import java.util.List;
import java.util.Objects;

/**
 * Класс реализации сбора статистики по коллекции.
 */
public class Analyze {
    /**
     * Метод вычисления статистических данных по коллекции.
     * @param previous начальное состояние коллекции.
     * @param current текущее сосиояние коллекции.
     * @return экземпляр класса Info, содержащий поля:
     * added - элементы добавленные current коллекцию.
     * changed - элементы измененные в current коллекции.
     * (Один id для previous и current коллекций, но разные name).
     * deleted - элементы удаленные в current коллекции.
     */
    public Info diff(List<User> previous, List<User> current) {
        Info rsl = new Info();
        HashMap<Integer, String> map = new HashMap<>();
        for (User user : previous) {
            map.put(user.id, user.name);
        }
        for (User user : current) {
            if (map.containsKey(user.id)) {
                if (!Objects.equals(map.get(user.id), user.name)) {
                    ++rsl.changed;
                }
                map.remove(user.id);
            } else {
                ++rsl.added;
            }
        }
        rsl.deleted = map.size();
        return rsl;
    }

    /**
     * Модель данных User.
     */
    public static class User {
        /**
         * Id/.
         */
        int id;
        /**
         * Имя пользователя.
         */
        String name;
    }

    /**
     * Модель данных статистической информации по коллекции.
     */
    public static class Info {
        /**
         * Количество новых элементов в текущей коллекции.
         */
        int added;
        /**
         * Количество измененных элементов в текущей коллекции.
         */
        int changed;
        /**
         * Количество удаленных элементов в текущей коллекции.
         */
        int deleted;
    }
}
