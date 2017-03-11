package view.screen;

import java.awt.Point;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import view.GameModelAdapter;
import view.Panel;
import view.PanelManager;
import view.RotationAnimation;
import view.ViewEnum;
import view.assets.AssetManager;

public class MainMenuPanel extends Panel {
	private static final int MAIN_MENU_BUTTON_SPACING = 100;
	private static final int DISTANCE_UP_FROM_CENTER = 80;
	private DropShadow ds = new DropShadow();
	private PanelManager panelManager;
	private AnchorPane mainMenuElements = new AnchorPane();
	private Group root;
	private Button startGame = new Button("Start Game");
	private Button mapMaker = new Button("Map Maker");
	private Button settings = new Button("Settings");
	private Button exitGame = new Button("Exit Game");
	private RotationAnimation humanFigure;
	private RotationAnimation humanFigure2;
	private RotationAnimation pandaFigure;
	private RotationAnimation pandaFigure2;

	public MainMenuPanel(GameModelAdapter gameModelAdapter, Group root, PanelManager panelManager,
			AssetManager assets, ViewEnum viewEnum) {
		super(gameModelAdapter, assets, viewEnum);
		humanFigure = new RotationAnimation(new Image[]{getImage("UNIT_GIANT_HUMAN")},
				17, 1.7, true, 22);
		pandaFigure = new RotationAnimation(new Image[]{getImage("UNIT_GIANT_PANDA")},
				15, 1.6, true, 25);
		humanFigure2 = new RotationAnimation(new Image[]{getImage("UNIT_GIANT_HUMAN")},
				7, 1.0, true, 23);
		pandaFigure2 = new RotationAnimation(new Image[]{getImage("UNIT_GIANT_PANDA")},
				7, 1.0, true, 24);
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
				getAdapter().startGame();
				panelManager.setMode(ViewEnum.INTRO);
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

	public void draw(GraphicsContext g, Point screenDimensions, long currentPulse) {
		g.drawImage(getImage("MENU_BACKGROUND"), 0, 0, screenDimensions.x,
				screenDimensions.y);
		drawCharacters(g, screenDimensions, currentPulse);
		g.setEffect(ds);
		g.setFont(getAssets().getFont(4));
		g.setFill(Color.WHITE);
		g.fillText("Asian Game!", screenDimensions.x / 2 - 240, screenDimensions.y / 4);
		g.setEffect(null);
		startGame.setTranslateX(screenDimensions.x / 2 - startGame.getWidth() / 2);
		startGame.setTranslateY(screenDimensions.y / 2 - DISTANCE_UP_FROM_CENTER);
		mapMaker.setTranslateX(screenDimensions.x / 2 - mapMaker.getWidth() / 2);
		mapMaker.setTranslateY(
				screenDimensions.y / 2 + MAIN_MENU_BUTTON_SPACING - DISTANCE_UP_FROM_CENTER);
		settings.setTranslateX(screenDimensions.x / 2 - settings.getWidth() / 2);
		settings.setTranslateY(
				screenDimensions.y / 2 + MAIN_MENU_BUTTON_SPACING * 2 - DISTANCE_UP_FROM_CENTER);
		exitGame.setTranslateX(screenDimensions.x / 2 - exitGame.getWidth() / 2);
		exitGame.setTranslateY(
				screenDimensions.y / 2 + MAIN_MENU_BUTTON_SPACING * 3 - DISTANCE_UP_FROM_CENTER);
	}

	private void drawCharacters(GraphicsContext g, Point screenDimensions, long currentPulse) {
		pandaFigure.draw(g, screenDimensions.x / 2 + 150, screenDimensions.y / 2 - 50, 1, 1,
				currentPulse);
		pandaFigure2.draw(g, screenDimensions.x / 2 + 300, screenDimensions.y / 2 - 50, 1, 1,
				currentPulse);
		humanFigure.draw(g, screenDimensions.x / 2 - 350, screenDimensions.y / 2 - 50, 1, 1,
				currentPulse);
		humanFigure2.draw(g, screenDimensions.x / 2 - 500, screenDimensions.y / 2 - 50, 1, 1,
				currentPulse);
	}

	public void hideGUIElements() {
		root.getChildren().remove(mainMenuElements);
	}

	public void showGUIElements() {
		root.getChildren().add(mainMenuElements);
	}
}
