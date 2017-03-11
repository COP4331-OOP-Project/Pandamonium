package game;

import controls.KeyEventController;
import controls.MouseEventController;
import game.mode.ModeController;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.KeyCombination;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import view.GameModelAdapter;
import view.View;

public class GameEngine extends Application {
	private static final int MIN_WIDTH = 1160;
    private static final int MIN_HEIGHT = 658;
    private KeyEventController keyEvents;
    private MouseEventController mouseEvents;
	private View view;
	private ModeController controlMode;
	private GameModel gameModel;
	private GameModelAdapter gameModelAdapter;
	
    @Override
    public void start(Stage stage) {
        stage.setTitle("Asian Game");
        Group root = new Group();
        Scene scene = new Scene(root, Color.BLACK);
        //stage.setFullScreen(true);
        stage.setMaximized(true);
        stage.setFullScreenExitKeyCombination(KeyCombination.NO_MATCH);
        stage.setOnCloseRequest(t -> {
		    Platform.exit();
		    System.exit(0);
		});
        gameModel = new GameModel();
        controlMode = new ModeController(gameModel);
        gameModelAdapter = new GameModelAdapter(gameModel, controlMode);
        view = new View(gameModelAdapter, scene, root);
        keyEvents = new KeyEventController(controlMode, view, scene);
        keyEvents.handleEvents();
        controlMode.setKeyEventController(keyEvents);
        mouseEvents = new MouseEventController(gameModel, view, scene);
        mouseEvents.handleEvents();
        new AnimationTimer() {
            @Override
            public void handle(long currentPulse) {
            	controlMode.update();
                gameModel.updateGame();
                view.renderGame();
            }
        }.start();
        stage.setMinHeight(MIN_HEIGHT);
        stage.setMinWidth(MIN_WIDTH);
        stage.setScene(scene);
        stage.show();
    }
}
