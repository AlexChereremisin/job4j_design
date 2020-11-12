package ru.job4j.io;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class ZipTest {
    private final String[] args = new String[3];

    @Rule
    public TemporaryFolder folder = new TemporaryFolder();

    @Before
    public void Setup() {
        try {
          this.folder.create();
          this.folder.newFolder("1");
          this.folder.newFolder("2");
          this.folder.newFile("1/1.java");
          this.folder.newFile("1/1.class");
          this.folder.newFile("2/2.java");
          this.folder.newFile("2/2.class");
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        this.args[0] = "-d=" + this.folder.getRoot().getAbsolutePath();
        this.args[1] = "-e=class";
        this.args[2] = "-o=" + this.folder.getRoot().getAbsolutePath() + "/test.zip";
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenAddNullListThenException() {
        new Zip().packFiles(null, new File("./chapter_002/pr.zip"));
    }

    @Test
    public void whenCreateZipThanZipExist() {
        ArgZip argZip = new ArgZip(this.args);
        SearchFiles searchFiles = new SearchFiles(
                p -> !p.toFile().getName().endsWith(argZip.exclude())
        );
        try {
            Files.walkFileTree(Path.of(argZip.directory()), searchFiles);
            List<File> files = new ArrayList<>();
            searchFiles.getPaths().forEach(p -> files.add(p.toFile()));
            Zip zip = new Zip();
            zip.packFiles(files, new File(argZip.output()));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        assertThat(Path.of(argZip.output()).toFile().exists(), is(true));
    }
}