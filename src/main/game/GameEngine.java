package game;

import controls.KeyEventController;
import controls.MouseEventController;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import view.View;

public class GameEngine extends Application {
    private KeyEventController keyEvents;
    private MouseEventController mouseEvents;
	private View view;
	private GameModel game;
    @Override
    public void start(Stage stage) {
        stage.setTitle("Asian Game");
        Group root = new Group();
        Scene scene = new Scene(root, Color.BLACK);
        //stage.setFullScreen(true);
        //stage.setFullScreenExitKeyCombination(KeyCombination.NO_MATCH);
        //stage.setMaximized(true);
        GameModel model = new GameModel();
        view = new View(model, scene, root);
        keyEvents = new KeyEventController(game, view, scene);
        keyEvents.handleEvents();
        mouseEvents = new MouseEventController(game, view, scene);
        mouseEvents.handleEvents();
        new AnimationTimer() {
            @Override
            public void handle(long now) {
                //update game here
                view.renderGame();
            }
        }.start();
        stage.setScene(scene);
        stage.show();
    }
}
