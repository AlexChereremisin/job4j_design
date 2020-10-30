package ru.job4j.io;

import java.io.FileOutputStream;

/**
 * Класс для демонстрации записи в файл посредством FileOutputStream.
 */
public class MatrixFile {
    /**
     * Точка входа в программу.
     * @param args массив строк.
     */
    public static void main(String[] args) {
        Matrix matrix = new Matrix();
        int[][] array = matrix.multiple(9);
        try (FileOutputStream out = new FileOutputStream("result.txt")) {
            StringBuilder str = new StringBuilder();
            for (int[] row : array) {
                for (int col : row) {
                    str.append(col).append("\t");
                }
                str.append("\n");
                out.write(str.toString().getBytes());
                str.delete(0, str.length() - 1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
