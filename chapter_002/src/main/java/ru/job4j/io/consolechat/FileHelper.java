package ru.job4j.io.consolechat;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.util.StringJoiner;

public class FileHelper {
    public static String readFile(Path path) {
        StringJoiner result = new StringJoiner(System.lineSeparator());
        try (BufferedReader br = new BufferedReader(
                new FileReader(path.toFile(), StandardCharsets.UTF_8))
        ) {
            String str = br.readLine();
            while(str != null) {
                result.add(str);
                str = br.readLine();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return result.toString();
    }

    public static String randomReadFile(Path path) {
        StringBuilder builder = new StringBuilder();
        int strCount = getStringCount(path);
        try (BufferedReader br = new BufferedReader(
                new FileReader(path.toFile(), StandardCharsets.UTF_8))
        ) {
            int pos = (int) (Math.random() * strCount) + 1;
            for (int i = 1; i <= strCount; ++i) {
                builder.delete(0, builder.length());
                builder.append(br.readLine());
                if(i == pos) {
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return builder.toString();
    }

    private static int getStringCount(Path path) {
        int result = 0;
        StringBuilder str = new StringBuilder();
        try (BufferedReader br = new BufferedReader(
                new FileReader(path.toFile(), StandardCharsets.UTF_8))
        ) {
            str.append(br.readLine());
            while (!str.toString().equals("null")) {
                ++result;
                str.delete(0, str.length());
                str.append(br.readLine());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    public static void writeFile(Path path, String string) {
        try (BufferedWriter br = new BufferedWriter(new FileWriter(
                path.toString(), StandardCharsets.UTF_8, true))
        ) {
            String[] strArr = string.split(System.lineSeparator());
            int count = 0;
            do {
                if (strArr.length == 0) {
                    br.write(System.lineSeparator());
                } else {
                    br.write(strArr[count]);
                    br.newLine();
                }

                ++count;
            } while(count <= strArr.length - 1);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
