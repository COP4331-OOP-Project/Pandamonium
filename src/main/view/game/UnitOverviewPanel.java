package view.game;

import java.awt.Point;

import game.entities.units.Unit;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
	private TableColumn unitTypeColumn = new TableColumn("Type");
	private TableColumn healthColumn = new TableColumn("Health");
	private TableColumn attackColumn = new TableColumn("Attack");
	private TableColumn defenseColumn = new TableColumn("Defense");
	private TableColumn armorColumn = new TableColumn("Armor");
	private TableColumn upkeepColumn = new TableColumn("Upkeep");
	private TableColumn addToArmyColumn = new TableColumn("Army");
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
		final TextField addToArmy = new TextField();
		unitTypeColumn.setResizable(false);
		healthColumn.setResizable(false);
		attackColumn.setResizable(false);
		defenseColumn.setResizable(false);
		armorColumn.setResizable(false);
		upkeepColumn.setResizable(false);
		addToArmyColumn.setResizable(false);
		unitTable.setEditable(false);
		unitTable.getColumns().addAll(unitTypeColumn, healthColumn, attackColumn, defenseColumn, 
				armorColumn, upkeepColumn, addToArmyColumn);
		canvas = new Canvas(); //This is the canvas that goes inside of the scroll pane
		overviewGraphics = canvas.getGraphicsContext2D();
		scrollPane.setContent(unitBox);
		canvas.setOnMouseClicked(event -> paneClicked(event.getX(), event.getY()));
		scrollPane.addEventFilter(ScrollEvent.SCROLL,event -> {
		    if (event.getDeltaX() != 0) { 
		        event.consume(); //This disables vertical scrolling in the scroll pane
		    }
		});
		unitTable.getStyleClass().addAll("tableViewStyle");
		unitBox.setSpacing(5);
        unitBox.setPadding(new Insets(10, 0, 0, 10));
        unitBox.getChildren().addAll(label, unitTable);
		 //This sets the style of scrollPane to that specified in the CSS document
		scrollPane.getStyleClass().setAll("scroll");
		overviewGraphics.setFill(Color.WHITE);
	}

	public void draw(GraphicsContext g, Point screenDimensions, long currentPulse) {
		ObservableList<UnitItem> unitList = FXCollections.observableArrayList();
		for (Unit unit : getAdapter().getCurrentUnits()) {
			String unitType = "";
			switch (unit.getType()) {
			case EXPLORER:
				unitType = "Explorer";
				break;
			case COLONIST:
				unitType = "Colonist";
				break;
			case MELEE:
				unitType = "Melee";
				break;
			case RANGE:
				unitType = "Ranged";
				break;
			default:
				break;
			}
			unitList.clear();
			unitList.add(new UnitItem(unitType, 1, 1, 1, 1, 1));
		}
		unitTypeColumn.setPrefWidth(unitTable.getWidth()/7);
		healthColumn.setPrefWidth(unitTable.getWidth()/7 - 13);
		attackColumn.setPrefWidth(unitTable.getWidth()/7 - 13);
		defenseColumn.setPrefWidth(unitTable.getWidth()/7 - 13);
		armorColumn.setPrefWidth(unitTable.getWidth()/7 - 13);
		upkeepColumn.setPrefWidth(unitTable.getWidth()/7 - 13);
		addToArmyColumn.setPrefWidth(unitTable.getWidth()/7 - 13);
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
	
	class UnitItem {
		private SimpleStringProperty unitTypeProp;
		private SimpleStringProperty  healthProp;
		private SimpleStringProperty  defenseProp;
		private SimpleStringProperty  armorProp;
		private SimpleStringProperty  upkeepProp;
		private SimpleStringProperty  armyProp;
		
		public UnitItem(String unitType, int health, int defense, int armor, int upkeep, int army) {
			this.unitTypeProp = new SimpleStringProperty(unitType);
			this.healthProp = new SimpleStringProperty(health + "");
			this.defenseProp = new SimpleStringProperty(defense + "");
			this.armorProp = new SimpleStringProperty(armor + "");
			this.upkeepProp = new SimpleStringProperty(upkeep + "");
			this.armyProp = new SimpleStringProperty(army + "");
		}
	}
}