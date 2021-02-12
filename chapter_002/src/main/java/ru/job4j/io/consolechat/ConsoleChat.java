package ru.job4j.io.consolechat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Path;

public class ConsoleChat {
    private final String path;
    private final String botAnswers;
    private static final String OUT = "закончить";
    private static final String STOP = "стоп";
    private static final String CONTINUE = "продолжить";

    public ConsoleChat(String path, String botAnswers) {
        this.path = path;
        this.botAnswers = botAnswers;
    }

    public void run() {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            boolean flag = false;
            StringBuilder str = new StringBuilder();
            StringBuilder answer = new StringBuilder();
            while (!str.append(reader.readLine()).toString().equals(OUT)) {
                if (str.toString().equals(STOP)) {
                    flag = true;
                }
                if (str.toString().equals(CONTINUE)) {
                    flag = false;
                }
                if (!flag) {
                    answer.append(FileHelper.randomReadFile(Path.of(this.botAnswers)));
                    System.out.println(answer.toString());
                }
                FileHelper.writeFile(Path.of(path), str.append(System.lineSeparator()).append(answer).toString());
                str.delete(0, str.length());
                answer.delete(0, answer.length());
            }
            FileHelper.writeFile(Path.of(path), str.append(System.lineSeparator()).append(answer).toString());
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new ConsoleChat("./chapter_002/data/log.txt", "./chapter_002/data/answer.txt").run();
    }
}
