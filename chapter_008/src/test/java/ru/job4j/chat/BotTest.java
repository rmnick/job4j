package ru.job4j.chat;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class BotTest {
    String path = "src/test/resources/";
    String nameAnswers = "file.txt";
    String log = "log.txt";
    String nameInput = "input.txt";
    String[] ask = {"hey", "stop", "nothing", "continue", "go on", "exit"};
    String[] answers = new String[] {"test"};
    BufferedInputStream bi = null;

    /**
     * create test folder and file whit questions
     * override System.in on our file
     */
    @Before
    public void createInput() {
        new File(path).mkdir();
        File input = new File(path + nameInput);
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(input))) {
            for (String str : ask) {
                bw.write(str);
                bw.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            bi = new BufferedInputStream(new FileInputStream(input));
            System.setIn(bi);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * write all conversation in log
     * count lines in log and compare
     */
    @Test
    public void whenWeSendSomeWordsThenLogWritesIt() {
        try {
        Bot bot = new Bot(path, nameAnswers, log, answers);
        bot.createListAnswers();
        bot.start();
        bi.close();
        File file = new File(path + log);
        List<String> list = Files.lines(Paths.get(file.getPath())).sorted().collect(Collectors.toList());
        assertThat(list.size(), is(9));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * delete all source files
     * bring everything back
     */
    @After
    public void revert() {
        System.setIn(System.in);
        File dir = new File(path);
        File[] files = dir.listFiles();
        for (File file : files) {
            if (file.exists()) {
                file.delete();
            }
        }
        dir.delete();
    }

}
