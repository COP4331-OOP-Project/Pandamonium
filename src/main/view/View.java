package view;

import java.awt.Point;
import java.io.File;

import game.GameModel;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import view.assets.AssetManager;
import view.game.Camera;

public class View {
    private AssetManager assets = new AssetManager();

    private static final int DEFAULT_SCREEN_WIDTH = 1152;
    private static final int DEFAULT_SCREEN_HEIGHT = 648;
    private Camera camera = new Camera();
    private Canvas canvas; //The GraphicsContext Goes on here.
    private GraphicsContext gc; //Image drawing is done with this
    private Group root; //Gui drawing is added to this
    
    private PanelManager panelManager;
    private Point screenDimensions = new Point();
    private Scene scene;
    
    public View(GameModel model, Scene scene, Group root) {
    	this.root = root;
    	this.scene = scene;
    	canvas = new Canvas(DEFAULT_SCREEN_WIDTH, DEFAULT_SCREEN_HEIGHT);
    	gc = canvas.getGraphicsContext2D();
    	panelManager = new PanelManager(assets, root, gc, camera);
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
        gc.clearRect(0, 0, width, height);
        panelManager.drawPanels(screenDimensions);
    }
    
    public void startDragging(double x, double y) {
    	camera.startDragging(x, y);
    }
    
    public void continueDragging(double x, double y) {
    	camera.continueDragging(x, y);
    }

	public void zoom(double deltaY) {
        camera.zoom(deltaY, screenDimensions);
    }
	
	public void tileClicked(double x, double y) {
		panelManager.tileClicked(x, y);
	}
}
