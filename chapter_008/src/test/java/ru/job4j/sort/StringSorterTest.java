package ru.job4j.sort;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class StringSorterTest {
    String path = "src/test/resources/";

    @Before
    public void createPath() {
            File directory = new File(path);
            if (!directory.exists()) {
                directory.mkdir();
            }
    }

    @Test
    public void test() {
        StringSorter stringSorter = new StringSorter();
        stringSorter.createSource(path, 53);
        stringSorter.sort(path, 5);
        List<String> listSource = new ArrayList<>();
        List<String> listOut = new ArrayList<>();
        try {
            listSource = Files.lines(Paths.get(path + "source.txt")).sorted().collect(Collectors.toList());
            listOut = Files.lines(Paths.get(path + "out.txt")).collect(Collectors.toList());
            assertThat(listOut.equals(listSource), is(true));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @After
    public void deleteAllFilesFolder() {
        File index = new File(path);
        String[]entries = index.list();
        for (String s : entries) {
            File currentFile = new File(index.getPath(), s);
            currentFile.delete();
        }
        index.delete();
    }
}
