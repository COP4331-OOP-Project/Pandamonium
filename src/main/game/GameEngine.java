package game;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class GameEngine extends Application {
    @Override
    public void start(Stage stage) {
        stage.setTitle("Asian Game");
        Group root = new Group();
        Scene scene = new Scene(root, Color.BLACK);
        //link the event handlers here
        new AnimationTimer() {
            @Override
            public void handle(long now) {
                //update game here
                //render game here
            }
        }.start();
        stage.setScene(scene);
        stage.show();
    }
}
