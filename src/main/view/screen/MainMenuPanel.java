package view.screen;

import java.awt.Point;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import view.Assets;
import view.Panel;
import view.View;
import view.ViewEnum;

public class MainMenuPanel extends Panel{
	private static final int MAIN_MENU_BUTTON_SPACING = 100;
	private static final int DISTANCE_UP_FROM_CENTER = 80;
    private DropShadow ds = new DropShadow();
	View view;
	AnchorPane mainMenuElements = new AnchorPane();
	Group root;
	Button startGame = new Button("Start Game");
	Button mapMaker = new Button("Map Maker");
	Button settings = new Button("Settings");
	Button exitGame = new Button("Exit Game");
	
	public MainMenuPanel(Group root, View view, Assets assets, ViewEnum viewEnum) {
		super(assets, viewEnum);
		this.view = view;
		this.root = root;
    	ds.setOffsetY(6.0);
    	ds.setColor(Color.color(0, 0, 0));
		setUpButtons();
	}

	private void setUpButtons() {
		startGame.setId("mainMenuButton");
		startGame.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                //view.mainGameMode();
            }
        });
		mapMaker.setId("mainMenuButton");
		mapMaker.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                //view.mapMakerMode();
            }
        });
		settings.setId("mainMenuButton");
		settings.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
               // view.settingsMode();
            }
        });
		exitGame.setId("mainMenuButton");
		exitGame.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Platform.exit();
            }
        });
		
		mainMenuElements.getChildren().add(startGame);
		mainMenuElements.getChildren().add(mapMaker);
		mainMenuElements.getChildren().add(settings);
		mainMenuElements.getChildren().add(exitGame);
	}

	@Override
	public void draw(GraphicsContext gc, Point screenDimensions) {
		gc.drawImage(getAssets().getImage("MENU_BACKGROUND"), 0, 0, screenDimensions.x, screenDimensions.y);
		gc.setEffect(ds);
		gc.setFont(getAssets().getFont(4));
		gc.setFill(Color.WHITE);
		gc.fillText("Asian Game!", screenDimensions.x/2 - 240, screenDimensions.y/4);
		gc.setEffect(null);
		startGame.setTranslateX(screenDimensions.x / 2 - startGame.getWidth() / 2);
		startGame.setTranslateY(screenDimensions.y / 2 - DISTANCE_UP_FROM_CENTER);
		mapMaker.setTranslateX(screenDimensions.x / 2 - mapMaker.getWidth() / 2);
		mapMaker.setTranslateY(screenDimensions.y / 2 + MAIN_MENU_BUTTON_SPACING - DISTANCE_UP_FROM_CENTER);
		settings.setTranslateX(screenDimensions.x / 2 - settings.getWidth() / 2);
		settings.setTranslateY(screenDimensions.y / 2 + MAIN_MENU_BUTTON_SPACING * 2 - DISTANCE_UP_FROM_CENTER);
		exitGame.setTranslateX(screenDimensions.x / 2 - exitGame.getWidth() / 2);
		exitGame.setTranslateY(screenDimensions.y / 2 + MAIN_MENU_BUTTON_SPACING * 3 - DISTANCE_UP_FROM_CENTER);
	}

	@Override
	public void hideGUIElements() {
		root.getChildren().remove(mainMenuElements);
	}

	@Override
	public void showGUIElements() {
		root.getChildren().add(mainMenuElements);
	}
}
