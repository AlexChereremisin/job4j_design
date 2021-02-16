package ru.job4j.io.consolechat;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.util.StringJoiner;

public class FileHelper {

    public static String readFile(Path path) {
        StringJoiner rsl = new StringJoiner(System.lineSeparator());
        try (BufferedReader br = new BufferedReader(
                new FileReader(path.toFile(), StandardCharsets.UTF_8))
        ) {
            String str = br.readLine();
            while(str != null) {
                rsl.add(str);
                str = br.readLine();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return rsl.toString();
    }

    public static void writeFile(Path path, String ... strings) {
        try (BufferedWriter br = new BufferedWriter(new FileWriter(
                path.toString(), StandardCharsets.UTF_8, false))
        ) {
            for (String s : strings) {
                br.write(s);
                br.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
