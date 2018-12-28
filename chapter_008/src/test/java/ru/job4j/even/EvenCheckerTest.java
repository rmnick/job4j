package ru.job4j.even;

import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class EvenCheckerTest {
    @Test
    public void whenInputStreamHasEvenSimbolThenTrue() {
        EvenChecker checker = new EvenChecker();
        String source = "dasdad9*.,asd, dasd, , 4dsda 33d";
        try (InputStream in = new ByteArrayInputStream(source.getBytes())) {
            assertThat(checker.findEvenSimbol(in), is(true));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void whenInputStreamDoesntHaveEvenSimbolThenTrue() {
        EvenChecker checker = new EvenChecker();
        String source = "dasd1ad*.,asd, dasd, , dsda 33d";
        try (InputStream in = new ByteArrayInputStream(source.getBytes())) {
            assertThat(checker.findEvenSimbol(in), is(false));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void whenInputStreamHasEvenNumberThenTrue() {
        EvenChecker checker = new EvenChecker();
        String source = "dasdad91*.,as3d, dasd, , 112dsda 33d";
        try (InputStream in = new ByteArrayInputStream(source.getBytes())) {
            assertThat(checker.findEvenNumber(in), is(true));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void whenInputStreamDoesntHaveEvenNumberThenTrue() {
        EvenChecker checker = new EvenChecker();
        String source = "d41asdad21*.,as3d, da63sd, , 211dsda 33d";
        try (InputStream in = new ByteArrayInputStream(source.getBytes())) {
            assertThat(checker.findEvenNumber(in), is(false));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
