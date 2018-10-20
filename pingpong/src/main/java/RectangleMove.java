import javafx.scene.shape.Rectangle;

import java.util.concurrent.ThreadLocalRandom;

public class RectangleMove implements Runnable {
    private final Rectangle rect;

    public RectangleMove(Rectangle rect) {
        this.rect = rect;
    }

    @Override
    public void run() {
        int dX = makeDelta();
        int dY = makeDelta();
        while (!Thread.currentThread().isInterrupted()) {
            if (this.rect.getX() == PingPong.LIMIT_X - rect.getWidth()) {
                dX = -1;
            }
            if (this.rect.getY() == 0) {
                dY = 1;
            }
            if (this.rect.getX() == 0) {
                dX = 1;
            }
            if (this.rect.getY() == PingPong.LIMIT_Y - rect.getWidth()) {
                dY = -1;
            }
            this.rect.setX(this.rect.getX() + dX);
            this.rect.setY(this.rect.getY() + dY);
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private int makeDelta() {
        int randomNum = ThreadLocalRandom.current().nextInt(3) - 1;
        return randomNum == 0 ? makeDelta() : randomNum;
    }
}