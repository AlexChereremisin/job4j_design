/**
 * Экзаменационное задание по блоку 1. "Структуры данных и алгоритмы".
 *
 * Это задание сводиться к определению разницы между начальным состояние массива и измененным.
 * Например. Дан массива чисел.
 * Начальное состояние 1 10 13 4 5
 * Конечное состояние 1 13 4
 * Разница будет будет массив 10 5.
 * Это элементарный пример. Ваша задача более сложная.
 *
 * Нужно реализовать класс.
 *
 * public class Analyze {
 *    public Info diff(List<User> previous, List<User> current) {
 *        return null;
 *    }
 *
 *    public static class User {
 *       int id;
 *       String name;
 *    }
 *
 *    public static class Info {
 *        int added;
 *        int changed;
 *        int deleted;
 *    }
 * }
 *
 * метод должен возвращать статистику об изменении коллекции.
 * List<User> previous - начальные данные,
 * List<User> current - измененные данные.
 * Нужно понять:
 * Сколько добавлено новых пользователей.
 * Сколько изменено пользователей. Изменённым считается объект в котором изменилось имя. а id осталось прежним.
 * Сколько удалено пользователей.
 * Обязательно напишите тесты.
 */
package ru.job4j.exam;