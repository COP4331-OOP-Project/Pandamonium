package view.game;

import java.awt.Point;

import javafx.scene.Group;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import view.GameModelAdapter;
import view.Panel;
import view.PanelManager;
import view.ViewEnum;
import view.assets.AssetManager;

public class ToggleBarPanel extends Panel{
	private AnchorPane buttons = new AnchorPane();
	private boolean resourcesVisible = false;
	private boolean unitsVisible = true;
	private boolean structuresVisible = true;
	private boolean miniMapVisible = true;
	private PanelManager panelManager;
	private Button resources = new Button();
	private Button units = new Button();
	private Button structures = new Button();
	private Button miniMap = new Button();
	private Group root;

	private DropShadow ds = new DropShadow();
	public ToggleBarPanel(GameModelAdapter gameModelAdapter, Group root, PanelManager panelManager, AssetManager assets, ViewEnum viewEnum) {
		super(gameModelAdapter, assets, viewEnum);
		this.root = root;
		this.panelManager = panelManager;
		setUpButton(resources, getAssets().getImage("UNCHECKED"));
		resources.setOnAction(event -> toggleResources());
		setUpButton(units, getAssets().getImage("CHECKED"));
		units.setOnAction(event -> toggleUnits());
		setUpButton(structures, getAssets().getImage("CHECKED"));
		structures.setOnAction(event -> toggleStructures());
		setUpButton(miniMap, getAssets().getImage("CHECKED"));
		miniMap.setOnAction(event -> toggleMiniMap());
		ds.setOffsetY(2.0f);
    	ds.setColor(Color.color(0, 0, 0));
		toggle();
	}

	private void toggleResources() {
		resourcesVisible = !resourcesVisible;
		if (resourcesVisible) {
			resources.setGraphic(new ImageView(getImage("CHECKED")));
		} else {
			resources.setGraphic(new ImageView(getImage("UNCHECKED")));
		}
		panelManager.toggleResources();
	}
	
	private void toggleStructures() {
		structuresVisible = !structuresVisible;
		if (structuresVisible) {
			structures.setGraphic(new ImageView(getImage("CHECKED")));
		} else {
			structures.setGraphic(new ImageView(getImage("UNCHECKED")));
		}
	}
	
	private void toggleUnits() {
		unitsVisible = !unitsVisible;
		if (unitsVisible) {
			units.setGraphic(new ImageView(getImage("CHECKED")));
		} else {
			units.setGraphic(new ImageView(getImage("UNCHECKED")));
		}
	}
	
	private void toggleMiniMap() {
		miniMapVisible = !miniMapVisible;
		if (miniMapVisible) {
			miniMap.setGraphic(new ImageView(getImage("CHECKED")));
		} else {
			miniMap.setGraphic(new ImageView(getImage("UNCHECKED")));
		}
		panelManager.toggleMiniMap();
	}
 
	public void setUpButton(Button button, Image image) {
		button.setGraphic(new ImageView(image));
		buttons.getChildren().add(button);
		button.getStyleClass().setAll("sideBarButton");
	}
	
	public void draw(GraphicsContext g, Point screenDimensions, long currentPulse) {
		positionButtons(screenDimensions);
		Image toggle = getImage("GUI_TOGGLE");
		g.drawImage(toggle, screenDimensions.x - 265, 50);
		drawText(g, screenDimensions);
	}

	private void positionButtons(Point screenDimensions) {
		resources.setTranslateX(screenDimensions.x - 120);
		resources.setTranslateY(103);
		units.setTranslateX(screenDimensions.x - 120);
		units.setTranslateY(133);
		structures.setTranslateX(screenDimensions.x - 120);
		structures.setTranslateY(163);
		miniMap.setTranslateX(screenDimensions.x - 120);
		miniMap.setTranslateY(193);
	}

	private void drawText(GraphicsContext g, Point screenDimensions) {
		g.setFont(getAssets().getFont(1));
		g.setEffect(ds);
		g.fillText("Toggle Visible", screenDimensions.x - 253, 77);
		g.setFont(getAssets().getFont(0));
		g.fillText("Resources", screenDimensions.x - 253, 120);
		g.fillText("Units", screenDimensions.x - 253, 150);
		g.fillText("Structures", screenDimensions.x - 253, 180);
		g.fillText("MiniMap", screenDimensions.x - 253, 210);
		g.setEffect(null);
	}

	public void hideGUIElements() {
		root.getChildren().remove(buttons);
	}

	public void showGUIElements() {
		root.getChildren().add(buttons);
	}

}
