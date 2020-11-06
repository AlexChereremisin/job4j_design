package ru.job4j.io;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.FileVisitor;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import static java.nio.file.FileVisitResult.CONTINUE;

/**
 * Класс поиска файла по предикату.
 */
public class SearchFiles implements FileVisitor<Path> {
    /**
     * Список путей до найденных файлов.
     */
    private final List<Path> pathList = new ArrayList<>();
    /**
     * Предикат, по которому будем искать.
     */
    private Predicate<Path> predicate;

    /**
     * Конструктор для инициализации предиката.
     * @param predicate предикат типа Path.
     */
    public SearchFiles(Predicate<Path> predicate) {
        this.predicate = predicate;
    }

    /**
     * Геттер поля pathList.
     * @return ссылка на поле pathList.
     */
    public List<Path> getPaths() {
        return this.pathList;
    }

    /**
     * Метод для описания действий перед заходом в директорию.
     * @param dir директория.
     * @param attrs аттрибуты директории.
     * @return обьект типа FileVisitResult, это перечисление
     * разных типов дествий после выполнения операций внутри файла.
     * @throws IOException исключение.
     */
    @Override
    public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
        return CONTINUE;
    }

    /**
     * Метод работы с файлом.
     * @param file файл.
     * @param attrs аттрибуты файла.
     * @return обьект типа FileVisitResult, это перечисление
     * разных типов дествий после выполнения операций внутри файла.
     * @throws IOException исключение.
     */
    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        if (predicate.test(file)) {
            pathList.add(file);
        }
        return CONTINUE;
    }

    /**
     * Метод для описания действий, если неудачное прочтение файла.
     * @param file файл.
     * @param exc аттрибуты файла.
     * @return обьект типа FileVisitResult, это перечисление
     * разных типов дествий после выполнения операций внутри файла.
     * @throws IOException исключение.
     */
    @Override
    public FileVisitResult visitFileFailed(Path file, IOException exc) throws IOException {
        return CONTINUE;
    }

    /**
     * Метод для описания действий после выхода из деректории.
     * @param dir директория.
     * @param exc аттрибуты директории.
     * @return обьект типа FileVisitResult, это перечисление
     * разных типов дествий после выполнения операций внутри файла.
     * @throws IOException исключение.
     */
    @Override
    public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
        return CONTINUE;
    }
}
