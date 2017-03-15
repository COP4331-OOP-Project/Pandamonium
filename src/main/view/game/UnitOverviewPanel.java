package view.game;

import game.entities.stats.UnitStats;
import game.entities.units.Unit;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.ScrollEvent;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import view.GameModelAdapter;
import view.ViewEnum;
import view.assets.AssetManager;

import java.awt.*;
import java.util.ArrayList;

public class UnitOverviewPanel extends OverviewPanel{
	private TableView<UnitItem> unitTable = new TableView<>();
	private Label label = new Label("Unit Overview");
	private TableColumn<UnitItem, String> unitTypeColumn = new TableColumn<>("Type");
	private TableColumn<UnitItem, String> healthColumn = new TableColumn<>("Health");
	private TableColumn<UnitItem, String> attackColumn = new TableColumn<>("Attack");
	private TableColumn<UnitItem, String> defenseColumn = new TableColumn<>("Defense");
	private TableColumn<UnitItem, String> armorColumn = new TableColumn<>("Armor");
	private TableColumn<UnitItem, String> upkeepColumn = new TableColumn<>("Upkeep (Food)");
	private TableColumn<UnitItem, String> addToArmyColumn = new TableColumn<>("Army");
	private ObservableList<UnitItem> unitList = FXCollections.observableArrayList();
	private VBox unitBox = new VBox();
	private DropShadow ds = new DropShadow();
	private ScrollPane scrollPane = new ScrollPane();
	private Group root;
	
	public UnitOverviewPanel(GameModelAdapter gameModelAdapter, AssetManager assets, ViewEnum view, Group root) {
		super(gameModelAdapter, assets, view);
		this.root = root;
		scrollPane.setVbarPolicy(ScrollBarPolicy.ALWAYS);
		scrollPane.setHbarPolicy(ScrollBarPolicy.NEVER);
		scrollPane.setContent(unitBox);
		scrollPane.getStyleClass().setAll("scroll");
		scrollPane.addEventFilter(ScrollEvent.SCROLL,event -> {
		    if (event.getDeltaX() != 0) { 
		        event.consume(); //This disables horizontal scrolling in the scroll pane
		    }
		});
		ds.setOffsetY(3.0);
		ds.setRadius(3);
		ds.setColor(Color.color(0, 0, 0));
		label.setTextFill(Color.WHITE);
        label.setFont(getAssets().getFont(2));
        label.setEffect(ds);
		setUpTable();
		setUpColumns();
	}

	private void setUpTable() {
		scrollPane.addEventFilter(ScrollEvent.SCROLL,event -> {
		    if (event.getDeltaY() != 0) { 
		        event.consume(); //This disables vertical scrolling in the actual table
		    }
		});
		unitTable.setPlaceholder(new Label("You have no units"));
		unitTable.setEditable(false);
		unitTable.getStyleClass().addAll("tableViewStyle");
		unitTable.setItems(unitList);
		unitBox.setSpacing(5);
        unitBox.setPadding(new Insets(10, 0, 0, 10));
        unitBox.getChildren().addAll(label, unitTable);
	}

	private void setUpColumns() {
		unitTable.getColumns().add(unitTypeColumn);
		unitTable.getColumns().add(healthColumn);
		unitTable.getColumns().add(attackColumn);
		unitTable.getColumns().add(defenseColumn);
		unitTable.getColumns().add(armorColumn);
		unitTable.getColumns().add(upkeepColumn);
		unitTable.getColumns().add(addToArmyColumn);

		unitTypeColumn.setResizable(false);
		attackColumn.setResizable(false);
		healthColumn.setResizable(false);
		defenseColumn.setResizable(false);
		armorColumn.setResizable(false);
		upkeepColumn.setResizable(false);
		addToArmyColumn.setResizable(false);
		
		unitTypeColumn.setCellValueFactory(unitList -> unitList.getValue().unitTypeProp);
		attackColumn.setCellValueFactory(unitList -> unitList.getValue().attackProp);
		healthColumn.setCellValueFactory(unitList -> unitList.getValue().healthProp);
		defenseColumn.setCellValueFactory(unitList -> unitList.getValue().defenseProp);
		armorColumn.setCellValueFactory(unitList -> unitList.getValue().armorProp);
		upkeepColumn.setCellValueFactory(unitList -> unitList.getValue().upkeepProp);
		addToArmyColumn.setCellValueFactory(unitList -> unitList.getValue().armyProp);
	}

	public void draw(GraphicsContext g, Point screenDimensions, long currentPulse) {
		scrollPane.toFront();
		updatePositions(screenDimensions);
		updateUnits();
	}

	private void updatePositions(Point screenDimensions) {
		scrollPane.setMaxWidth(screenDimensions.x - 148);
		unitTable.setPrefWidth(screenDimensions.x - 148);
		scrollPane.setMaxHeight(488);
		scrollPane.setTranslateX(74);
		scrollPane.setTranslateY(50);
		unitTypeColumn.setPrefWidth(unitTable.getWidth()/7);
		healthColumn.setPrefWidth(unitTable.getWidth()/7 - 13);
		attackColumn.setPrefWidth(unitTable.getWidth()/7 - 13);
		defenseColumn.setPrefWidth(unitTable.getWidth()/7 - 13);
		armorColumn.setPrefWidth(unitTable.getWidth()/7 - 13);
		upkeepColumn.setPrefWidth(unitTable.getWidth()/7 - 13);
		addToArmyColumn.setPrefWidth(unitTable.getWidth()/7 - 13);
	}

	private void updateUnits() {
		ArrayList<Unit> units = getAdapter().getCurrentUnits();
        unitTable.setPrefHeight(45 + (units.size() * 35));
		unitList.clear();
		for (Unit unit : units) {
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
				unitType = "Mystery Unit";
				break;
				}
			UnitStats stats = unit.getStats();
			String armyId;
			if (unit.getArmyId() != null) {
				armyId = Integer.toString(unit.getArmyId());
			} else {
				armyId = "No Army";
			}
			unitList.add(new UnitItem(unitType, (int)unit.getCurrentHealth(), stats.getOffPow(), stats.getDefPow(),
					stats.getArmor(), unit.getUpkeep(), armyId));
			}
	}

	public void showGUIElements() {
		root.getChildren().add(scrollPane);
	}

	public void hideGUIElements() {
		root.getChildren().remove(scrollPane);
	}
	
	class UnitItem {
		private final SimpleStringProperty unitTypeProp;
		private final SimpleStringProperty attackProp;
		private final SimpleStringProperty  healthProp;
		private final SimpleStringProperty  defenseProp;
		private final SimpleStringProperty  armorProp;
		private final SimpleStringProperty  upkeepProp;
		private final SimpleStringProperty  armyProp;
		
		public UnitItem(String unitType, int health, int attack, int defense, int armor, double upkeep, String army) {
			unitTypeProp = new SimpleStringProperty(unitType);
			healthProp = new SimpleStringProperty(Integer.toString(health));
			attackProp = new SimpleStringProperty(Integer.toString(attack));
			defenseProp = new SimpleStringProperty(Integer.toString(defense));
			armorProp = new SimpleStringProperty(Integer.toString(armor));
			upkeepProp = new SimpleStringProperty(Double.toString(upkeep));
			armyProp = new SimpleStringProperty(army);
		}
		
		public String getAttack() {
			return attackProp.get();
		}
		
		public String getUnitType() {
			return unitTypeProp.get();
		}
		
		public String getHealth() {
			return healthProp.get();
		}
		
		public String getDefense() {
			return defenseProp.get();
		}
		
		public String getArmor() {
			return armorProp.get();
		}
		
		public String getUpkeep() {
			return upkeepProp.get();
		}
		
		public String getArmy() {
			return armyProp.get();
		}
	}
}