package ru.job4j.io;

import java.io.FileInputStream;

/**
 * Класс для демонстрации считывания данных из файла.
 */
public class EvenNumberFile {
    /**
     * Точка входа в программу.
     * @param args массив строк.
     */
    public static void main(String[] args) {
        try(FileInputStream in = new FileInputStream("even.txt")) {
            StringBuilder str = new StringBuilder();
            int read;
            while ((read = in.read()) != -1) {
                str.append((char) read);
            }
            for (String s : str.toString().split(System.lineSeparator())) {
                if (Integer.parseInt(s) % 2 == 0) {
                    System.out.println(s + " четное.");
                } else {
                    System.out.println(s + " нечетное.");
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
