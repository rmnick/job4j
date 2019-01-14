package ru.job4j.sort;

import org.junit.After;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.is;

public class SorterByBytesTest {
    String path = "src/test/resources/";
    String name = "source";

    @Test
    public void test() {
        List<String> sourceList;
        List<String> outList;
        int numberOfStrings = 1000;
        long sizeOfCuts = 200;
        try {
            SorterByBytes sorter = new SorterByBytes();
            sorter.create(path, name, numberOfStrings);
            sourceList = Files.lines(Paths.get(path + File.separator + name + ".txt")).sorted().collect(Collectors.toList());
            sorter.run(path, sizeOfCuts);
            List<File> out = sorter.collect(path);
            outList = Files.lines(Paths.get(out.get(0).getPath())).sorted().collect(Collectors.toList());
            assertThat(sourceList.equals(outList), is(true));
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
