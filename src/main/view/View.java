package view;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import view.assets.AssetManager;
import view.game.Camera;

import java.awt.*;
import java.io.File;

public class View {
    private AssetManager assets = new AssetManager();
    private static final int DEFAULT_SCREEN_WIDTH = 1152;
    private static final int DEFAULT_SCREEN_HEIGHT = 648;
    private Camera camera;
    private Canvas canvas; //The GraphicsContext Goes on here.
    private GraphicsContext g; //Image drawing is done with this
    private Group root; //Gui drawing is added to this
    private PanelManager panelManager;
    private Point screenDimensions = new Point();
    private Scene scene;
    private int pulse = 0;
    private GameModelAdapter gameModelAdapter;
    
    public View(GameModelAdapter gameModelAdapter, Scene scene, Group root) {
    	this.root = root;
    	this.scene = scene;
    	canvas = new Canvas(DEFAULT_SCREEN_WIDTH, DEFAULT_SCREEN_HEIGHT);
    	screenDimensions.x = DEFAULT_SCREEN_WIDTH;
    	screenDimensions.y = DEFAULT_SCREEN_HEIGHT;
    	camera = new Camera(screenDimensions);
    	g = canvas.getGraphicsContext2D();
    	this.gameModelAdapter = gameModelAdapter;
    	panelManager = new PanelManager(gameModelAdapter, assets, root, g, camera);
        setSceneTheme();
    }

	private void setSceneTheme() {
        root.getChildren().add(canvas);
        File buttonStyle = new File("assets/buttonStyle.css");
        scene.getStylesheets().clear();
        scene.getStylesheets().add("file:///" + buttonStyle.getAbsolutePath().replace("\\", "/"));;
	}

    public void renderGame() {
    	double width = scene.getWidth();
    	double height = scene.getHeight();
        canvas.setWidth(width);
        canvas.setHeight(height);
        screenDimensions.x = (int)width;
        screenDimensions.y = (int)height;
        g.clearRect(0, 0, width, height);
        panelManager.drawPanels(screenDimensions, pulse);
        pulse++;
    }
    
    public void startDragging(double x, double y) {
    	camera.startDragging(x, y);
    }
    
    public void continueDragging(double x, double y) {
    	camera.continueDragging(x, y);
    }

	public void zoom(double deltaY) {
        camera.zoom(deltaY);
    }
	
	public void tileClicked(double x, double y) {
		panelManager.tileClicked(x, y);
	}

	public void toggleUnitOverview() {
		panelManager.toggleUnitOverview();
	}

	public void toggleStructureOverview() {
		panelManager.toggleStructureOverview();
	}

	public void centerOnCurrentTypeInstance() {
		panelManager.centerOnSelected();
	}

	public void endTurn() {
		panelManager.endTurn();
		if (this.gameModelAdapter.isGameOver()) {
		    System.out.println("Game over.");
		    panelManager.setMode(ViewEnum.MAIN_MENU);
        }
	}
}
