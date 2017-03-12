package view.game;

import java.awt.Point;
import java.util.ArrayList;

import game.entities.stats.StructureStats;
import game.entities.structures.Structure;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
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

public class StructureOverviewPanel extends OverviewPanel{
	private TableView<StructureItem> structureTable = new TableView<>();
	private Label label = new Label("structure Overview");
	private TableColumn<StructureItem, String> structureTypeColumn = new TableColumn<>("Type");
	private TableColumn<StructureItem, String> healthColumn = new TableColumn<>("Health");
	private TableColumn<StructureItem, String> attackColumn = new TableColumn<>("Attack");
	private TableColumn<StructureItem, String> defenseColumn = new TableColumn<>("Defense");
	private TableColumn<StructureItem, String> armorColumn = new TableColumn<>("Armor");
	private TableColumn<StructureItem, String> upkeepColumn = new TableColumn<>("Upkeep");
	private TableColumn<StructureItem, String> workerColumn = new TableColumn<>("Workers");
	private ObservableList<StructureItem> structureList = FXCollections.observableArrayList();
	private VBox structureBox = new VBox();
	private DropShadow ds = new DropShadow();
	private ScrollPane scrollPane = new ScrollPane();
	private Group root;
	
	public StructureOverviewPanel(GameModelAdapter gameModelAdapter, AssetManager assets, ViewEnum view, Group root) {
		super(gameModelAdapter, assets, view);
		this.root = root;
		scrollPane.setVbarPolicy(ScrollBarPolicy.ALWAYS);
		scrollPane.setHbarPolicy(ScrollBarPolicy.NEVER);
		scrollPane.setContent(structureBox);
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
		structureTable.setEditable(false);
		structureTable.getStyleClass().addAll("tableViewStyle");
		structureTable.setItems(structureList);
		structureBox.setSpacing(5);
        structureBox.setPadding(new Insets(10, 0, 0, 10));
        structureBox.getChildren().addAll(label, structureTable);
	}

	private void setUpColumns() {
		structureTable.getColumns().add(structureTypeColumn);
		structureTable.getColumns().add(healthColumn);
		structureTable.getColumns().add(attackColumn);
		structureTable.getColumns().add(defenseColumn);
		structureTable.getColumns().add(armorColumn);
		structureTable.getColumns().add(upkeepColumn);
		structureTable.getColumns().add(workerColumn);

		structureTypeColumn.setResizable(false);
		attackColumn.setResizable(false);
		healthColumn.setResizable(false);
		defenseColumn.setResizable(false);
		armorColumn.setResizable(false);
		upkeepColumn.setResizable(false);
		workerColumn.setResizable(false);
		
		structureTypeColumn.setCellValueFactory(structureList -> structureList.getValue().structureTypeProp);
		attackColumn.setCellValueFactory(structureList -> structureList.getValue().attackProp);
		healthColumn.setCellValueFactory(structureList -> structureList.getValue().healthProp);
		defenseColumn.setCellValueFactory(structureList -> structureList.getValue().defenseProp);
		armorColumn.setCellValueFactory(structureList -> structureList.getValue().armorProp);
		upkeepColumn.setCellValueFactory(structureList -> structureList.getValue().upkeepProp);
		workerColumn.setCellValueFactory(structureList -> structureList.getValue().workerProp);
	}

	public void draw(GraphicsContext g, Point screenDimensions, long currentPulse) {
		scrollPane.toFront();
		updatePositions(screenDimensions);
		updateStructures();
	}

	private void updatePositions(Point screenDimensions) {
		scrollPane.setMaxWidth(screenDimensions.x - 148);
		structureTable.setPrefWidth(screenDimensions.x - 148);
		scrollPane.setMaxHeight(488);
		scrollPane.setTranslateX(74);
		scrollPane.setTranslateY(50);
		structureTypeColumn.setPrefWidth(structureTable.getWidth()/7);
		healthColumn.setPrefWidth(structureTable.getWidth()/7 - 13);
		attackColumn.setPrefWidth(structureTable.getWidth()/7 - 13);
		defenseColumn.setPrefWidth(structureTable.getWidth()/7 - 13);
		armorColumn.setPrefWidth(structureTable.getWidth()/7 - 13);
		upkeepColumn.setPrefWidth(structureTable.getWidth()/7 - 13);
		workerColumn.setPrefWidth(structureTable.getWidth()/7 - 13);
	}

	private void updateStructures() {
		ArrayList<Structure> structures = getAdapter().getStructures();
        structureTable.setPrefHeight(45 + (structures.size() * 35));
		structureList.clear();
		for (Structure structure : structures) {
			String structureType = "";
			switch (structure.getType()) {
			case CAPITOL:
				structureType = "Capitol";
				break;
			case FARM:
				structureType = "Farm";
				break;
			case MINE:
				structureType = "Mine";
				break;
			case PLANT:
				structureType = "Plant";
				break;
			case OBSERVE:
				structureType = "Observation Tower";
				break;
			case FORT:
				structureType = "Fort";
				break;
			case UNIVERSITY:
				structureType = "University";
				break;
			default:
				break;
			}
			StructureStats stats = structure.getStats();
			//Have no way to get attack or worker count right now, leaving at -999 until later
			structureList.add(new StructureItem(structureType, stats.getHealth(), -999, stats.getDefPow(),
					stats.getArmor(), (int)stats.getUpkeep(), -99));
			}
	}

	public void showGUIElements() {
		root.getChildren().add(scrollPane);
	}

	public void hideGUIElements() {
		root.getChildren().remove(scrollPane);
	}
	
	class StructureItem {
		private final SimpleStringProperty structureTypeProp;
		private final SimpleStringProperty attackProp;
		private final SimpleStringProperty  healthProp;
		private final SimpleStringProperty  defenseProp;
		private final SimpleStringProperty  armorProp;
		private final SimpleStringProperty  upkeepProp;
		private final SimpleStringProperty  workerProp;
		
		public StructureItem(String structureType, int health, int attack, int defense, int armor, int upkeep, int workers) {
			structureTypeProp = new SimpleStringProperty(structureType);
			healthProp = new SimpleStringProperty(health + "");
			attackProp = new SimpleStringProperty(attack + "");
			defenseProp = new SimpleStringProperty(defense + "");
			armorProp = new SimpleStringProperty(armor + "");
			upkeepProp = new SimpleStringProperty(upkeep + "");
			workerProp = new SimpleStringProperty(workers + "");
		}
		
		public String getAttack() {
			return attackProp.get();
		}
		
		public String getstructureType() {
			return structureTypeProp.get();
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
		
		public String getWorkers() {
			return workerProp.get();
		}
	}
}