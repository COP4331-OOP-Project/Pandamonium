package view.game;

import java.awt.Point;

import javafx.scene.Group;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.input.ScrollEvent;
import javafx.scene.paint.Color;
import view.GameModelAdapter;
import view.ViewEnum;
import view.assets.AssetManager;

public class StructureOverviewPanel extends OverviewPanel{
	private static final int PANE_WIDTH = 219;
	private static final int PANE_HEIGHT = 80;
	private ScrollPane scrollPane = new ScrollPane();
	private GraphicsContext overviewGraphics;
	private Canvas canvas;
	private Group root;
	
	public StructureOverviewPanel(GameModelAdapter gameModelAdapter, AssetManager assets, ViewEnum view, Group root) {
		super(gameModelAdapter, assets, view);
		this.root = root;
		scrollPane.setVbarPolicy(ScrollBarPolicy.ALWAYS);
		scrollPane.setHbarPolicy(ScrollBarPolicy.NEVER);
		canvas = new Canvas(); //This is the canvas that goes inside of the scroll pane
		overviewGraphics = canvas.getGraphicsContext2D();
		scrollPane.setContent(canvas);
		canvas.setOnMouseClicked(event -> paneClicked(event.getX(), event.getY()));
		scrollPane.addEventFilter(ScrollEvent.SCROLL,event -> {
		    if (event.getDeltaX() != 0) { 
		        event.consume(); //This disables vertical scrolling in the scroll pane
		    }
		});
		 //This sets the style of scrollPane to that specified in the CSS document
		scrollPane.getStyleClass().setAll("scroll");
		overviewGraphics.setFill(Color.WHITE);
	}

	public void draw(GraphicsContext g, Point screenDimensions, long currentPulse) {
		scrollPane.toFront();
		overviewGraphics.clearRect(0, 0, 2500, screenDimensions.y);
		scrollPane.setMaxWidth(screenDimensions.x - 148);
		scrollPane.setMaxHeight(488);
		scrollPane.setTranslateX(74);
		scrollPane.setTranslateY(50);
		canvas.setWidth(screenDimensions.x - 148);
		canvas.setHeight(3000);
	}
	
	private void paneClicked(double x, double y) {
		//if (pointInPane(14, 87, x, y))
	}
	
	private boolean pointInPane(int paneX, int paneY, double clickX, double clickY) {
		return ((clickX >= paneX && clickX <= paneX + PANE_WIDTH) && 
				(clickY >= paneY && clickY <= paneY + PANE_HEIGHT));
	}

	public void showGUIElements() {
		root.getChildren().add(scrollPane);
	}

	public void hideGUIElements() {
		root.getChildren().remove(scrollPane);
	}
}