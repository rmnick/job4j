package ru.job4j;

import org.junit.Test;
import ru.job4j.model.Board;
import ru.job4j.model.Figure;
import ru.job4j.model.Logic;

public class TempTest {
    @Test
    public void test() {
        int result;
        for (int i = 0; i < 30; i++) {
            result = (int) (Math.random() * 3);
            System.out.println(result);
        }
    }

    @Test
    public void boardTest() {
        Board board = new Board();
        Logic logic = new Logic(board);
        logic.move(new Figure(false), 0, 0);
        System.out.println(board.getBoard()[0][1]);
    }

}
