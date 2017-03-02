package game;

import controls.KeyEventController;
import controls.MouseEventController;
import game.mode.ControlMode;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.KeyCombination;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import view.GameModelAdapter;
import view.View;

public class GameEngine extends Application {
    private KeyEventController keyEvents;
    private MouseEventController mouseEvents;
	private View view;
	private ControlMode controlMode;
	private GameModel gameModel;
	private GameModelAdapter gameModelAdapter;
    @Override
    public void start(Stage stage) {
        stage.setTitle("Asian Game");
        Group root = new Group();
        Scene scene = new Scene(root, Color.BLACK);
        //stage.setFullScreen(true);
        stage.setFullScreenExitKeyCombination(KeyCombination.NO_MATCH);
        stage.setMaximized(true);
        stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent t) {
                Platform.exit();
                System.exit(0);
            }
        });
        gameModel = new GameModel();
        controlMode = new ControlMode(gameModel);
        gameModelAdapter = new GameModelAdapter(gameModel, controlMode);
        view = new View(gameModelAdapter, scene, root);
        keyEvents = new KeyEventController(controlMode, view, scene);
        keyEvents.handleEvents();
        mouseEvents = new MouseEventController(gameModel, view, scene);
        mouseEvents.handleEvents();
        new AnimationTimer() {
            @Override
            public void handle(long currentPulse) {
                gameModel.updateGame();
                view.renderGame(currentPulse);
            }
        }.start();
        stage.setScene(scene);
        stage.show();
    }
}
