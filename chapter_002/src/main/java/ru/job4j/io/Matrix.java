package ru.job4j.io;

/**
 * Matrix класс содержит метод создания таблицы умножения.
 * @author Alexey Cheremisin
 * @since 06.05.2020
 * @version 1
 */
public class Matrix {
    /**
     * multiple метод создания таблицы умножения.
     * Создает квадратную матрицу с элементами, соответсвующим выражению (индекс строки + 1) * (индекс столбца + 1).
     * @param size : размер двумерного квадратного массива.
     * @return двумерный массив заполненый числами соответственно таблице умножения.
     */
    public int[][] multiple(int size) {
        int[][] table = new int[size][size];
        for (int row = 0; row < size; row++) {
            for (int cell = 0; cell < size; cell++) {
                table[row][cell] = (row + 1) * (cell + 1);
            }
        }
        return table;
    }
}