package ru.job4j.io;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.PrintWriter;

/**
 * Класс содержит метод анализа валидности сервера.
 */
public class Analyze {
    /**
     * Метод для анализа недоступности сервера.
     * @param source файл с данными в виде
     *               status time
     *               где status - 200, 300, 400, 500.
     *               А time - время в формате hh:mm:ss.
     * @param target в этот файл будут записаны диапазоны времени,
     *               когда сервер был недоступен.
     *               Начальное время - статус 500 или 400.
     *               Конечное время - изменение статуса с
     *               400 или 500 на 200 или 300.
     */
    public void unavailable(String source, String target) {
        try (
                BufferedReader reader = new BufferedReader(new FileReader(source));
                PrintWriter writer = new PrintWriter(new FileOutputStream(target))
        ) {
            StringBuilder str = new StringBuilder();
            reader.lines().forEach(s -> {
                if (s.startsWith("400") || s.startsWith("500")) {
                    if (str.toString().endsWith("#") || str.length() == 0) {
                        str.append(s.trim().split("\s")[1]).append(";");
                    } else if (str.toString().endsWith("!")) {
                        str.replace(
                                str.length() - 9,
                                str.length(),
                                s.trim().split("\s")[1]
                        ).append("!");
                    } else {
                        str.append(s.trim().split("\s")[1]).append("!");
                    }
                }  else if (str.toString().endsWith("!")) {
                    str.replace(
                            str.length() - 9,
                            str.length(),
                            s.trim().split("\s")[1]
                    ).append("#");
                } else if (str.toString().endsWith(";")) {
                    str.append(s.trim().split("\s")[1]).append("#");
                }
            });
            for (String diapason : str.toString().split("#")) {
                writer.println(diapason);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
