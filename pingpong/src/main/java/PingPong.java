import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class PingPong extends Application {
    private static final String JOB4J = "Пинг-понг www.job4j.ru";
    static final int LIMIT_X = 300;
    static final int LIMIT_Y = 300;

    @Override
    public void start(Stage stage) {
        Group group = new Group();
        Rectangle rect = new Rectangle(100, 150, 10, 10);
        group.getChildren().add(rect);
        new Thread(new RectangleMove(rect)).start();
        stage.setScene(new Scene(group, LIMIT_X, LIMIT_Y));
        stage.setTitle(JOB4J);
        stage.setResizable(false);
        stage.show();
    }
}