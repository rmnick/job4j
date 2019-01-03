package ru.job4j.corrector;

import org.junit.Test;

import java.io.*;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;


public class CorrectorTest {
    @Test
    public void whenItHasBadWordThenRemoveIt() {
        String[] abuse = {"ggg", "ttt", "hhh"};
        Corrector corretor = new Corrector();
        String example = "ggg gggg this is a ttt example. "
                + "bad word instttide is a correct form hhh";
        String correct = " gggg this is a  example. "
                + "bad word instttide is a correct form ";
        try (InputStream in = new ByteArrayInputStream(example.getBytes());
             OutputStream out  = new ByteArrayOutputStream()) {
            corretor.correct(in, out, abuse);
            System.out.println(out.toString());
            assertThat(out.toString(), is(correct));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
