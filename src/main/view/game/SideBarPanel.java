package view.game;

import java.awt.Point;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import view.GameModelAdapter;
import view.Panel;
import view.PanelManager;
import view.ViewEnum;
import view.assets.AssetManager;

public class SideBarPanel extends Panel{
	private AnchorPane sideBarButtons = new AnchorPane();
	ImageView humanUnit = new ImageView(getImage("ICON_HUMAN_HEAD"));
	ImageView pandaUnit = new ImageView(getImage("ICON_PANDA_HEAD"));
	Button visibilityButton = new Button();
	Button unitButton = new Button();
	Button structureButton = new Button();
	Button researchButton = new Button();
	Button settingsButton = new Button();
	Button endTurnButton = new Button();
	private Group root;
	private GameModelAdapter gameModelAdapter;
	
	public SideBarPanel(GameModelAdapter gameModelAdapter, AssetManager assets, ViewEnum view, 
			Group root, PanelManager panelManager) {
    	super(gameModelAdapter, assets, view);
    	this.root = root;
    	this.gameModelAdapter = gameModelAdapter;
    	setUpButton(visibilityButton, getImage("ICON_VISIBILITY"));
    	visibilityButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                panelManager.toggleToggler();
            }
        });
    	setUpButton(unitButton, getImage("ICON_HUMAN_HEAD"));
    	unitButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                panelManager.toggleUnitOverview();
            }
        });
    	setUpButton(structureButton, getImage("ICON_STRUCTURE"));
    	structureButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                panelManager.toggleStructureOverview();
            }
        });
    	setUpButton(researchButton, getImage("ICON_RESEARCH"));
    	researchButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                panelManager.toggleTechOverview();
            }
        });
    	setUpButton(settingsButton, getImage("ICON_SETTINGS"));
    	settingsButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                //Show in game settings panel
            }
        });
		setUpButton(endTurnButton, getImage("ICON_END_TURN"));
		endTurnButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				gameModelAdapter.endTurn();
			}
		});
    }
	
	public void setUpButton(Button button, Image image) {
		button.setGraphic(new ImageView(image));
		sideBarButtons.getChildren().add(button);
		button.getStyleClass().setAll("sideBarButton");
	}
	
	public void draw(GraphicsContext g, Point screenDimensions, long currentPulse) {
		drawSideBar(g, screenDimensions);
		if (gameModelAdapter.getPlayer() == 0) {
			unitButton.setGraphic(humanUnit);
		} else {
			unitButton.setGraphic(pandaUnit);
		}
		setPositions(screenDimensions);
	}
	
	private void setPositions(Point screenDimensions) {
		int x = screenDimensions.x - 55;
		visibilityButton.setTranslateX(x);
		visibilityButton.setTranslateY(65);
		unitButton.setTranslateX(x);
		unitButton.setTranslateY(120);
		structureButton.setTranslateX(x);
		structureButton.setTranslateY(175);
		researchButton.setTranslateX(x);
		researchButton.setTranslateY(230);
		settingsButton.setTranslateX(x);
		settingsButton.setTranslateY(285);
		endTurnButton.setTranslateX(x);
		endTurnButton.setTranslateY(340);
	}

	private void drawSideBar(GraphicsContext g, Point screenDimensions) {
		g.drawImage(getImage("GUI_SIDE"), screenDimensions.x - getAssets().getImageWidth("GUI_SIDE"), 50);
	}

	public void hideGUIElements() {
		root.getChildren().remove(sideBarButtons);
	}

	public void showGUIElements() {
		root.getChildren().add(sideBarButtons);
	}
}
