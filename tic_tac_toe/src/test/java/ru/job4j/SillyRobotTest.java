package ru.job4j;

import org.junit.Test;
import ru.job4j.model.*;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;


public class SillyRobotTest {
    @Test
    public void whenBoardHasOneEmptyCellThenReturnThisCellCoordinates() {
        Board board = new Board();
        Logic logic = new Logic(board);
        boolean mark = false;
        SillyRobot robot = new SillyRobot(board, logic, mark);
        for (int j = 0; j < board.getBoard().length; j++) {
            for (int i = 0; i < board.getBoard().length; i++) {
                if (!(j == 2 && i == 2)) {
                    board.getBoard()[j][i] = new Figure(true);
                }
            }
        }
        assertThat(robot.findWay(), is(new int[]{2, 2}));
    }

    @Test
    public void whenBoardHasOneEmptyCellThenMoveInThisCell() {
        Board board = new Board();
        Logic logic = new Logic(board);
        boolean mark = false;
        SillyRobot robot = new SillyRobot(board, logic, mark);
        for (int j = 0; j < board.getBoard().length; j++) {
            for (int i = 0; i < board.getBoard().length; i++) {
                if (!(j == 2 && i == 2)) {
                    board.getBoard()[j][i] = new Figure(true);
                }
            }
        }
        robot.move();
        assertThat(board.getBoard()[2][2].getMark(), is(false));
    }
}
