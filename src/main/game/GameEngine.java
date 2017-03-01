package game;

import controls.KeyEventController;
import controls.MouseEventController;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import view.GameModelAdapter;
import view.View;

public class GameEngine extends Application {
    private KeyEventController keyEvents;
    private MouseEventController mouseEvents;
	private View view;
	private GameModel gameModel = new GameModel();
	private GameModelAdapter gameModelAdapter = new GameModelAdapter(gameModel);
    @Override
    public void start(Stage stage) {
        stage.setTitle("Asian Game");
        Group root = new Group();
        Scene scene = new Scene(root, Color.BLACK);
        //stage.setFullScreen(true);
        //stage.setFullScreenExitKeyCombination(KeyCombination.NO_MATCH);
        //stage.setMaximized(true);
        
        view = new View(gameModelAdapter, scene, root);
        keyEvents = new KeyEventController(gameModel, view, scene);
        keyEvents.handleEvents();
        mouseEvents = new MouseEventController(gameModel, view, scene);
        mouseEvents.handleEvents();
        new AnimationTimer() {
            @Override
            public void handle(long currentPulse) {
                //update game here
                view.renderGame(currentPulse);
            }
        }.start();
        stage.setScene(scene);
        stage.show();
    }
}
