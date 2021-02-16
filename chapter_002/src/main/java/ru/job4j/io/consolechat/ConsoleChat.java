package ru.job4j.io.consolechat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;

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

    private ArrayList<String> getAnswers() {
        String[] strArr = FileHelper.readFile(Path.of(botAnswers)).split(System.lineSeparator());
        return new ArrayList<>(Arrays.asList(strArr));
    }

    public void run() {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            ArrayList<String> answers = this.getAnswers();
            ArrayList<String> log = new ArrayList<>();
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
                log.add(str.toString());
                if (!flag) {
                    answer.append(answers.get((int)(Math.random() * answers.size())));
                    System.out.println(answer.toString());
                    log.add(answer.toString());
                }
                str.delete(0, str.length());
                answer.delete(0, answer.length());
            }
            log.add(str.toString());
            FileHelper.writeFile(Path.of(path), log.toArray(String[]::new));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new ConsoleChat("./chapter_002/data/log.txt", "./chapter_002/data/answer.txt").run();
    }
}
