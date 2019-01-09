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

public class SorterTest {

    @After
    public void deleteAllFilesFolder() {
        String path = "src/test/resources/";
        File index = new File(path);
        String[]entries = index.list();
        for (String s : entries) {
            File currentFile = new File(index.getPath(), s);
            currentFile.delete();
        }
    }

    @Test
    public void test() {
        String path = "src/test/resources/";
        Sorter sorter = new Sorter();
        sorter.createSource(path, 53);
        sorter.sort(path, 5);
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
}
