package ru.job4j.io;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.FileVisitor;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;

import static java.nio.file.FileVisitResult.CONTINUE;

/**
 * Реализация интерфейса FileVisitor.
 * Для печати абсолютного пути найденных файлов.
 */
public class PrintFiles implements FileVisitor<Path> {
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
        System.out.println(file.toAbsolutePath());
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