package view.game;

import java.awt.Point;

import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.effect.DropShadow;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.ScrollEvent;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import view.GameModelAdapter;
import view.ViewEnum;
import view.assets.AssetManager;

public class UnitOverviewPanel extends OverviewPanel{
	private static final int PANE_WIDTH = 219;
	private static final int PANE_HEIGHT = 80;
	private final TableView unitTable = new TableView();
	private final VBox unitBox = new VBox();
	private DropShadow ds = new DropShadow();
	private ScrollPane scrollPane = new ScrollPane();
	private GraphicsContext overviewGraphics;
	private Canvas canvas;
	private Group root;
	
	public UnitOverviewPanel(GameModelAdapter gameModelAdapter, AssetManager assets, ViewEnum view, Group root) {
		super(gameModelAdapter, assets, view);
		this.root = root;
		scrollPane.setVbarPolicy(ScrollBarPolicy.ALWAYS);
		scrollPane.setHbarPolicy(ScrollBarPolicy.NEVER);
		ds.setOffsetY(3.0);
		ds.setRadius(3);
		ds.setColor(Color.color(0, 0, 0));
		final Label label = new Label("Unit Overview");
		label.setTextFill(Color.WHITE);
        label.setFont(getAssets().getFont(3));
        label.setEffect(ds);
		final TextField unitType = new TextField();
		final TextField health = new TextField();
		final TextField attack = new TextField();
		final TextField defense = new TextField();
		final TextField armor = new TextField();
		final TextField upkeep = new TextField();
		TableColumn unitTypeColumn = new TableColumn("Type");
		TableColumn healthColumn = new TableColumn("Health");
		TableColumn attackColumn = new TableColumn("Attack");
		TableColumn defenseColumn = new TableColumn("Defense");
		TableColumn armorColumn = new TableColumn("Armor");
		TableColumn upkeepColumn = new TableColumn("Upkeep");
		//unitTypeColumn.setResizable(false);
		//healthColumn.setResizable(false);
		//attackColumn.setResizable(false);
		//defenseColumn.setResizable(false);
		//armorColumn.setResizable(false);
		//upkeepColumn.setResizable(false);
		unitTable.setEditable(false);
		unitTable.getColumns().addAll(unitTypeColumn, healthColumn, attackColumn, defenseColumn, armorColumn, upkeepColumn);
		canvas = new Canvas(); //This is the canvas that goes inside of the scroll pane
		overviewGraphics = canvas.getGraphicsContext2D();
		scrollPane.setContent(unitBox);
		canvas.setOnMouseClicked(event -> paneClicked(event.getX(), event.getY()));
		scrollPane.addEventFilter(ScrollEvent.SCROLL,event -> {
		    if (event.getDeltaX() != 0) { 
		        event.consume(); //This disables vertical scrolling in the scroll pane
		    }
		});
		unitBox.setSpacing(5);
        unitBox.setPadding(new Insets(10, 0, 0, 10));
        unitBox.getChildren().addAll(label, unitTable);
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
		unitTable.setPrefWidth(screenDimensions.x - 148);
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