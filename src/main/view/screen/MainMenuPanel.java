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
import view.Panel;
import view.PanelManager;
import view.View;
import view.ViewEnum;
import view.assets.AssetManager;

public class MainMenuPanel extends Panel{
	private static final int MAIN_MENU_BUTTON_SPACING = 100;
	private static final int DISTANCE_UP_FROM_CENTER = 80;
    private DropShadow ds = new DropShadow();
	PanelManager panelManager;
	AnchorPane mainMenuElements = new AnchorPane();
	Group root;
	Button startGame = new Button("Start Game");
	Button mapMaker = new Button("Map Maker");
	Button settings = new Button("Settings");
	Button exitGame = new Button("Exit Game");
	
	public MainMenuPanel(Group root, PanelManager panelManager, AssetManager assets, ViewEnum viewEnum) {
		super(assets, viewEnum);
		this.root = root;
		this.panelManager = panelManager;
    	ds.setOffsetY(6.0);
    	ds.setColor(Color.color(0, 0, 0));
		setUpButtons();
	}

	private void setUpButtons() {
		startGame.setId("mainMenuButton");
		startGame.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                panelManager.setMode(ViewEnum.MAIN_GAME);
            }
        });
		mapMaker.setId("mainMenuButton");
		mapMaker.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                panelManager.setMode(ViewEnum.MAP_MAKER);
            }
        });
		settings.setId("mainMenuButton");
		settings.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
               panelManager.setMode(ViewEnum.SETTINGS);
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
	public void draw(GraphicsContext g, Point screenDimensions) {
		g.drawImage(getAssets().getImage("MENU_BACKGROUND"), 0, 0, screenDimensions.x, screenDimensions.y);
		g.setEffect(ds);
		g.setFont(getAssets().getFont(4));
		g.setFill(Color.WHITE);
		g.fillText("Asian Game!", screenDimensions.x/2 - 240, screenDimensions.y/4);
		g.setEffect(null);
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
