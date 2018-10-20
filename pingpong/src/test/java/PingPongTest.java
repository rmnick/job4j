import org.junit.Test;

import java.util.Random;

/**
 * temporary block
 * testing some ideas for chapter_006
 */

public class PingPongTest {
    @Test
    public void test() {
        Random rnd = new Random();
        int i = 0;
        while (i < 100) {
            int j = rnd.nextInt(3) - 1;
            System.out.println(j);
            i++;
        }
    }
}
