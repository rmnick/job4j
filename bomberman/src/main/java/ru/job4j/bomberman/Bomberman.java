package ru.job4j.bomberman;

public class Bomberman implements Runnable {

    private final Board board;

    private Cell position;


    public Bomberman(final Board board, final Cell startPosition) {
        this.board = board;
        this.position = startPosition;
    }

    //input control command
    public void command(Directions dir) throws InterruptedException {
        switch (dir) {
            case UP:
                move(Directions.UP);
                break;
            case DOWN:
                move(Directions.UP);
                break;
            case LEFT:
                move(Directions.LEFT);
                break;
            case RIGHT:
                move(Directions.RIGHT);
                break;
                default:
                    break;
        }

    }
    //just for tests
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            try {
                command(Directions.RIGHT);
                Thread.sleep(100);
                if (this.position.hasQueuedThreads()) {
                    Thread.currentThread().interrupt();
                    System.out.println("GAME OVER !");
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    private void move(Directions dir) throws InterruptedException {
        if (correctStep(dir.x, this.position.getX())
                && correctStep(dir.y, this.position.getY())) {
            if (board.move(this.position, destCell(dir))) {
                this.position = this.destCell(dir);
                System.out.printf("Bomberman steps on %d : %d \n", this.position.getX(), this.position.getY());
            }
        }
    }

    private boolean correctStep(int delta, int arg) {
        return (arg + delta < board.size && arg + delta > -1);
    }

    private int step(int arg, int delta) {
        return arg + delta;
    }

    public Cell destCell(Directions dir) {
        return board.board[step(position.getY(), dir.y)][step(position.getX(), dir.x)];
    }
}

