package view;

import java.awt.Point;
import java.io.File;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;


public class View {
    private Assets assets;

    private static final int DEFAULT_SCREEN_WIDTH = 1152;
    private static final int DEFAULT_SCREEN_HEIGHT = 648;

    private Canvas canvas; //The GraphicsContext Goes on here.
    private GraphicsContext gc; //Image drawing is done with this
    private Group root; //Gui drawing is added to this
    
    private PanelManager panelManager;
    private Point screenDimensions = new Point();
    private Scene scene;
    
    public View(Scene scene, Group root) {
    	this.assets = Assets.getInstance();
    	this.root = root;
    	this.scene = scene;
    	canvas = new Canvas(DEFAULT_SCREEN_WIDTH, DEFAULT_SCREEN_HEIGHT);
    	gc = canvas.getGraphicsContext2D();
        setSceneTheme();
    }

	private void setSceneTheme() {
        root.getChildren().add(canvas);
        File buttonStyle = new File("assets/buttonStyle.css");
        scene.getStylesheets().clear();
        scene.getStylesheets().add("file:///" + buttonStyle.getAbsolutePath().replace("\\", "/"));;
	}

	public void drawVisiblePanels(int width, int height) {
    	screenDimensions.x = width;
    	screenDimensions.y = height;
    	panelManager.drawPanels();
    }

    public void renderGame() {
    	double width = scene.getWidth();
    	double height = scene.getHeight();
        canvas.setWidth(width);
        canvas.setHeight(height);
        gc.clearRect(0, 0, width, height);
        drawVisiblePanels((int)width, (int)height);
    }
}
