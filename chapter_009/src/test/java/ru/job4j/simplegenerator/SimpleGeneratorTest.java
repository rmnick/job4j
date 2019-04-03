package ru.job4j.simplegenerator;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class SimpleGeneratorTest {
    @Test
    public void whenSearchRightKeyThenReplaceIt() {
        SimpleGenerator sg = new SimpleGenerator();
        String example = "hey, ${name} ${soname}, i'm glad to see you again, ${for example";
        String correct = "hey, Petr Arsentev, i'm glad to see you again, ${for example";
        Map<String, String> map = new HashMap<>();
        map.put("name", "Petr");
        map.put("soname", "Arsentev");
        String result = sg.fixString(example, map);
        assertThat(result, is(correct));

    }

    @Test (expected = KeyException.class)
    public void whenStringHasUnnecessaryKeysThenThrowException() {
        SimpleGenerator sg = new SimpleGenerator();
        String example = "hey, ${name} ${soname}, i'm glad to see you again, ${example}";
        Map<String, String> map = new HashMap<>();
        map.put("name", "Petr");
        map.put("soname", "Arsentev");
        sg.fixString(example, map);
    }

    @Test
    public void whenMapHasUnnecessaryKeysThenThrowException() {
        String msg = "something";
        SimpleGenerator sg = new SimpleGenerator();
        String example = "hey, ${name} ${soname}, i'm glad to see you again, ${for example";
        Map<String, String> map = new HashMap<>();
        map.put("name", "Petr");
        map.put("soname", "Arsentev");
        map.put("dummy", "dummy");
        try {
            sg.fixString(example, map);
        } catch (KeyException e) {
            msg = e.getMessage();
        }
        assertThat("the string doesn't contain dummy", is(msg));
    }
}
