package view.game;

import game.entities.EntitySubtypeEnum;
import game.entities.structures.Structure;
import game.entities.structures.University;
import game.entityTypeResearch.UniversityAlreadyDoingResearchException;
import game.techTree.nodeTypes.TechTreeNode;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Group;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.*;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.ScrollEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import view.GameModelAdapter;
import view.ViewEnum;
import view.assets.AssetManager;

import java.awt.*;
import java.util.ArrayList;

public class TechOverviewPanel extends OverviewPanel{
	private static final int PANE_WIDTH = 219;
	private static final int PANE_HEIGHT = 80;
	private Label label = new Label("Technology Overview");
	private Label label2 = new Label();
	private TechModeEnum currentMode = TechModeEnum.TECHNOLOGY;
	private AnchorPane techBox = new AnchorPane();
	private ScrollPane scrollPane = new ScrollPane();
	private TechViewItem fertilizer;
	private TechViewItem wheelbarrow;
	private TechViewItem tent;
	private TechViewItem ironMining;
	private TechViewItem bed;
	private TechViewItem housing;
	private TechViewItem draftHorse;
	private TechViewItem irrigation;
	private TechViewItem steamPower;
	private TechViewItem militia;
	private TechViewItem pesticides;
	private TechViewItem steelMining;
	private TechViewItem barracks;
	private TechViewItem beer;
	private TechViewItem blastFurnace;
	private TechViewItem roads;
	private TechViewItem vodka;
	private TechViewItem urbanPlanning;
	private TechViewItem windPower;
	private TechViewItem militaryAcademy;
	private TechViewItem nuclearPower;
	private ToggleButton techsToggle = new ToggleButton("Technologies");
	private ToggleButton improvementsToggle = new ToggleButton("Improvements"); 
	private ArrayList<University> currentUniversities;
	private ComboBox<String> upgradableComboBox;
	private ObservableList<String> upgradableList;
	private DropShadow ds = new DropShadow();
	private EntitySubtypeEnum selectedEntity;
	private GraphicsContext techGraphics;
	private boolean initialized = false;
	private int itemsResearching = 0;
	private int universityCount = 0;
	private int player;
	private Canvas canvas;
	private Group root;
	
	public TechOverviewPanel(GameModelAdapter gameModelAdapter, AssetManager assets, ViewEnum view, Group root, int player) {
		super(gameModelAdapter, assets, view);
		currentUniversities = new ArrayList<>();
		this.player = player;
		this.root = root;
		upgradableList = FXCollections.observableArrayList(
		        "Colonist",
		        "Explorer",
		        "Melee",
		        "Ranged",
		        "Capitol",
		        "Farm",
		        "Mine",
		        "Power Plant",
		        "Fort",
		        "Observation Tower",
		        "University"
		);
		setUpCanvas();
		setUpLabel();
		setUpButtons();
		setUpUpgradableList();
		setUpScrollPane();
		techBox.getChildren().addAll(canvas, label, label2, techsToggle, improvementsToggle, upgradableComboBox);
	}

	public void setUpTechsForPlayer() {
		ArrayList<TechTreeNode> techs;
		if (player == 0) {
			techs = getAdapter().getHumanTechNodes();
		} else {
			techs = getAdapter().getPandaTechNodes();
		}
		TechTreeNode fertilizerNode = techs.get(0);
		TechTreeNode wheelbarrowNode = techs.get(1);
		TechTreeNode tentNode = techs.get(2);
		TechTreeNode ironMiningNode = fertilizerNode.getChildren().get(0);
		TechTreeNode bedNode = tentNode.getChildren().get(0);
		TechTreeNode housingNode = ironMiningNode.getChildren().get(0);
		TechTreeNode draftHorseNode = housingNode.getChildren().get(0);
		TechTreeNode irrigationNode = housingNode.getChildren().get(1);
		TechTreeNode steamPowerNode = housingNode.getChildren().get(2);
		TechTreeNode militiaNode = draftHorseNode.getChildren().get(0);
		TechTreeNode pesticidesNode = irrigationNode.getChildren().get(0);
		TechTreeNode steelMiningNode = steamPowerNode.getChildren().get(0);
		TechTreeNode barracksNode = militiaNode.getChildren().get(0);
		TechTreeNode beerNode  = pesticidesNode.getChildren().get(0);
		TechTreeNode blastFurnaceNode = steelMiningNode.getChildren().get(0);
		TechTreeNode roadsNode = barracksNode.getChildren().get(0);
		TechTreeNode vodkaNode = beerNode.getChildren().get(0);
		TechTreeNode urbanPlanningNode = roadsNode.getChildren().get(0);
		TechTreeNode windPowerNode = roadsNode.getChildren().get(1);
		TechTreeNode militaryAcademyNode = urbanPlanningNode.getChildren().get(0);
		TechTreeNode nuclearPowerNode = windPowerNode.getChildren().get(0);
		fertilizer = new TechViewItem(getAssets(), fertilizerNode, getAdapter(), this);
		wheelbarrow = new TechViewItem(getAssets(), wheelbarrowNode, getAdapter(), this);
		tent = new TechViewItem(getAssets(), tentNode, getAdapter(), this);
		ironMining = new TechViewItem(getAssets(), ironMiningNode, getAdapter(), this);
		bed = new TechViewItem(getAssets(), bedNode, getAdapter(), this);
		housing = new TechViewItem(getAssets(), housingNode, getAdapter(), this);
		draftHorse = new TechViewItem(getAssets(), draftHorseNode, getAdapter(), this);
		irrigation = new TechViewItem(getAssets(), irrigationNode, getAdapter(), this);
		steamPower = new TechViewItem(getAssets(), steamPowerNode, getAdapter(), this);
		militia = new TechViewItem(getAssets(), militiaNode, getAdapter(), this);
		pesticides = new TechViewItem(getAssets(), pesticidesNode, getAdapter(), this);
		steelMining = new TechViewItem(getAssets(), steelMiningNode, getAdapter(), this);
		barracks = new TechViewItem(getAssets(), barracksNode, getAdapter(), this);
		beer = new TechViewItem(getAssets(), beerNode, getAdapter(), this);
		blastFurnace = new TechViewItem(getAssets(), blastFurnaceNode, getAdapter(), this);
		roads = new TechViewItem(getAssets(), roadsNode, getAdapter(), this);
		vodka = new TechViewItem(getAssets(), vodkaNode, getAdapter(), this);
		urbanPlanning = new TechViewItem(getAssets(), urbanPlanningNode, getAdapter(), this);
		windPower = new TechViewItem(getAssets(), windPowerNode, getAdapter(), this);
		militaryAcademy = new TechViewItem(getAssets(), militaryAcademyNode, getAdapter(), this);
		nuclearPower = new TechViewItem(getAssets(), nuclearPowerNode, getAdapter(), this);
	}

	private void setUpButtons() {
		techsToggle.getStyleClass().setAll("button");
		improvementsToggle.getStyleClass().setAll("button");
		techsToggle.setTranslateX(380);
		techsToggle.setTranslateY(17);
		improvementsToggle.setTranslateX(540);
		improvementsToggle.setTranslateY(17);
		toggleTech();
		techsToggle.setOnAction(event -> {
			toggleTech();
		});
        improvementsToggle.setOnAction(event -> {
			toggleImprovements();
		});
	}

	private void toggleImprovements() {
		currentMode = TechModeEnum.IMPROVEMENTS;
		improvementsToggle.getStyleClass().setAll("buttonSelected");
		techsToggle.getStyleClass().setAll("button");
		improvementsToggle.setSelected(true);
		techsToggle.setSelected(false);
	}

	private void toggleTech() {
		currentMode = TechModeEnum.TECHNOLOGY;
		techsToggle.getStyleClass().setAll("buttonSelected");
		improvementsToggle.getStyleClass().setAll("button");
		techsToggle.setSelected(true);
		improvementsToggle.setSelected(false);
	}
	
	private void setUpUpgradableList() {
		upgradableComboBox = new ComboBox<String>(upgradableList);
		upgradableComboBox.setButtonCell(new ListCell<String>(){
		        @Override
		        protected void updateItem(String string, boolean empty) {
		            super.updateItem(string, empty); 
		            if(!(empty || string==null)){
		                setStyle("-fx-text-fill: white");
		                setText(string);
		            }
		       }
		});
		upgradableComboBox.setTranslateX(13);
		upgradableComboBox.setTranslateY(60);
		upgradableComboBox.getSelectionModel().selectFirst();
		selectedEntity = EntitySubtypeEnum.COLONIST;
	}

	private void setUpCanvas() {
		canvas = new Canvas(); //This is the canvas that goes inside of the scroll pane
		canvas.setOnMouseClicked(event -> paneClicked(event.getX(), event.getY()));
		techGraphics = canvas.getGraphicsContext2D();
		techGraphics.setFill(Color.WHITE);
	}

	private void setUpScrollPane() {
		scrollPane.setVbarPolicy(ScrollBarPolicy.NEVER);
		scrollPane.setContent(techBox);
		scrollPane.addEventFilter(ScrollEvent.SCROLL,event -> {
		    if (event.getDeltaY() != 0) { 
		        event.consume(); //This disables vertical scrolling in the scroll pane
		    }
		});
		 //This sets the style of scrollPane to that specified in the CSS document
		scrollPane.getStyleClass().setAll("scroll");
	}
	
	private void setUpLabel() {
		label.setTextFill(Color.WHITE);
        label.setFont(getAssets().getFont(2));
        label.setEffect(ds);
        label.setTranslateX(10);
        label.setTranslateY(10);
        label2.setTextFill(Color.WHITE);
        label2.setFont(getAssets().getFont(1));
        label2.setEffect(ds);
        label2.setTranslateX(720);
        label2.setTranslateY(20);
	}

	public void draw(GraphicsContext g, Point screenDimensions, long currentPulse) {
		if (initialized == false) {
			if (getAdapter().getGameStarted()) {
				initialized = true;
				updatePlayer();
			}
		}
		label2.setText("Available: " + (universityCount - itemsResearching));
		techGraphics.clearRect(0, 0, 2500, screenDimensions.y);
		scrollPane.toFront();
		scrollPane.setMaxHeight(488);
		scrollPane.setTranslateY(50);
		switch (currentMode) {
			case TECHNOLOGY:
				upgradableComboBox.setVisible(false);
				scrollPane.setTranslateX(74);
				scrollPane.setMaxWidth(screenDimensions.x - 148);
				scrollPane.setHbarPolicy(ScrollBarPolicy.ALWAYS);
				canvas.setWidth(2450);
				canvas.setTranslateY(0);
				canvas.setHeight(screenDimensions.y - 147);
				drawTechnologies();
				drawConnectors();
				break;
			case IMPROVEMENTS:
				upgradableComboBox.setVisible(true);
				updateSelectedItem();
				scrollPane.setMaxWidth(screenDimensions.x - 1);
				scrollPane.setHbarPolicy(ScrollBarPolicy.NEVER);
				canvas.setWidth(screenDimensions.x  - 148);
				canvas.setHeight(screenDimensions.y - 147);
				canvas.setTranslateY(0);
				drawImprovementText();
				break;
		}
		
	}

	private void updateSelectedItem() {
		String selected = upgradableComboBox.getSelectionModel().getSelectedItem();
		switch (selected) {
			case "Colonist":
				selectedEntity = EntitySubtypeEnum.COLONIST;
				break;
			case "Explorer":
				selectedEntity = EntitySubtypeEnum.EXPLORER;
				break;
			case "Melee":
				selectedEntity = EntitySubtypeEnum.MELEE;
				break;
			case "Capitol":
				selectedEntity = EntitySubtypeEnum.CAPITOL;
				break;
			case "Farm":
				selectedEntity = EntitySubtypeEnum.FARM;
				break;
			case "Mine":
				selectedEntity = EntitySubtypeEnum.MINE;
				break;
			case "Power Plant":
				selectedEntity = EntitySubtypeEnum.PLANT;
				break;
			case "Fort":
				selectedEntity = EntitySubtypeEnum.FORT;
				break;
			case "Observation Tower":
				selectedEntity = EntitySubtypeEnum.OBSERVE;
				break;
			case "University":
				selectedEntity = EntitySubtypeEnum.UNIVERSITY;
				break;
		}
	}

	private void drawImprovementText() {
		techGraphics.setEffect(ds);
		techGraphics.setFont(getAssets().getFont(2));
		techGraphics.fillText("Visibility Radius", 17, 130);
		techGraphics.fillText("Attack Strength", 17, 180);
		techGraphics.fillText("Defense Strength", 17, 230);
		techGraphics.fillText("Armor Strength", 17, 280);
		techGraphics.fillText("Health", 17, 330);
		techGraphics.fillText("Efficiency", 17, 380);
		switch (selectedEntity) {
			case EXPLORER:
			case COLONIST:
			case MELEE:
			case RANGE:
				techGraphics.fillText("Movement Rate", 17, 430);
				break;
			default :
				break;
		}
		techGraphics.setEffect(null);
	}

	private void drawConnectors() {
		//These are all those little connector graphics in between the technologies
		techGraphics.drawImage(getAssets().getImage("TECH_CONNECT1"), 235, 132);
		techGraphics.drawImage(getAssets().getImage("TECH_CONNECT2"), 235, 289);
		techGraphics.drawImage(getAssets().getImage("TECH_CONNECT1"), 510, 180);
		techGraphics.drawImage(getAssets().getImage("TECH_CONNECT3"), 785, 124);
		techGraphics.drawImage(getAssets().getImage("TECH_CONNECT5"), 1060, 124);
		techGraphics.drawImage(getAssets().getImage("TECH_CONNECT5"), 1060, 235);
		techGraphics.drawImage(getAssets().getImage("TECH_CONNECT5"), 1060, 345);
		techGraphics.drawImage(getAssets().getImage("TECH_CONNECT5"), 1335, 125);
		techGraphics.drawImage(getAssets().getImage("TECH_CONNECT5"), 1335, 235);
		techGraphics.drawImage(getAssets().getImage("TECH_CONNECT5"), 1335, 345);
		techGraphics.drawImage(getAssets().getImage("TECH_CONNECT1"), 1610, 235);
		techGraphics.drawImage(getAssets().getImage("TECH_CONNECT4"), 1610, 125);
		techGraphics.drawImage(getAssets().getImage("TECH_CONNECT6"), 1885, 180);
		techGraphics.drawImage(getAssets().getImage("TECH_CONNECT5"), 2160, 180);
		techGraphics.drawImage(getAssets().getImage("TECH_CONNECT5"), 2160, 290);
	}

	private void drawTechnologies() {
        fertilizer.draw(techGraphics, new Point (0,80));
        wheelbarrow.draw(techGraphics, new Point(0, 190));
        tent.draw(techGraphics, new Point(0, 300));
        ironMining.draw(techGraphics, new Point(275, 135));
        bed.draw(techGraphics, new Point(275, 245));
        housing.draw(techGraphics, new Point(550, 190));
        draftHorse.draw(techGraphics, new Point(825, 80));
        irrigation.draw(techGraphics, new Point(825, 190));
        steamPower.draw(techGraphics, new Point(825, 300));
        militia.draw(techGraphics, new Point(1100, 80));
        pesticides.draw(techGraphics, new Point(1100, 190));
        steelMining.draw(techGraphics, new Point(1100, 300));
        barracks.draw(techGraphics, new Point(1375, 80));
        beer.draw(techGraphics, new Point(1375, 190));
        blastFurnace.draw(techGraphics, new Point(1375, 300));
        roads.draw(techGraphics, new Point(1650, 135));
        vodka.draw(techGraphics, new Point(1650, 245));
        urbanPlanning.draw(techGraphics, new Point(1925, 135));
        windPower.draw(techGraphics, new Point(1925, 245));
        militaryAcademy.draw(techGraphics, new Point(2200, 135));
        nuclearPower.draw(techGraphics, new Point(2200, 245));
	}
	
	private void paneClicked(double x, double y) {
		if (currentMode == TechModeEnum.TECHNOLOGY) {
			if (pointInPane(14, 87, x, y)) {
				fertilizer.onClick();
			} else if (pointInPane(14, 197, x, y)) {
				wheelbarrow.onClick();
			} else if (pointInPane(14, 307, x, y)) {
				tent.onClick();
			} else if (pointInPane(14, 197, x, y)) {
				wheelbarrow.onClick();
			} else if (pointInPane(289, 142, x, y)) {
				ironMining.onClick();
			} else if (pointInPane(289, 252, x, y)) {
				bed.onClick();
			} else if (pointInPane(564, 197, x, y)) {
				housing.onClick();
			} else if (pointInPane(839, 87, x, y)) {
				draftHorse.onClick();
			}else if (pointInPane(839, 197, x, y)) {
				irrigation.onClick();
			}else if (pointInPane(839, 307, x, y)) {
				steamPower.onClick();
			}else if (pointInPane(1114, 87, x, y)) {
				militia.onClick();
			}else if (pointInPane(1114, 197, x, y)) {
				pesticides.onClick();
			}else if (pointInPane(1114, 307, x, y)) {
				steelMining.onClick();
			}else if (pointInPane(1389, 87, x, y)) {
				barracks.onClick();
			}else if (pointInPane(1389, 197, x, y)) {
				beer.onClick();
			}else if (pointInPane(1389, 307, x, y)) {
				blastFurnace.onClick();
			}else if (pointInPane(1664, 142, x, y)) {
				roads.onClick();
			}else if (pointInPane(1664, 252, x, y)) {
				vodka.onClick();
			}else if (pointInPane(1939, 142, x, y)) {
				urbanPlanning.onClick();
			}else if (pointInPane(1939, 252, x, y)) {
				windPower.onClick();
			}else if (pointInPane(2214, 142, x, y)) {
				militaryAcademy.onClick();
			}else if (pointInPane(2214, 252, x, y)) {
				nuclearPower.onClick();
			}
		}
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

	public void updatePlayer() {
		setUpTechsForPlayer();
		updateUniversities();
	}

	private void updateUniversities() {
		currentUniversities.clear();
		for (Structure structure : getAdapter().getStructures()) {
			if (structure.getType() == EntitySubtypeEnum.UNIVERSITY) {
				currentUniversities.add((University)structure);
			}
		}
		universityCount = currentUniversities.size();
	}
	
	public int getUniversityCount() {
		return universityCount;
	}
	
	public int getItemsResearching() {
		return itemsResearching;
	}

	public void research(TechTreeNode techNode) {
		int universityToResearchWith = universityCount - itemsResearching - 1;
		if (itemsResearching < universityCount) {
			itemsResearching++;
			try {
				currentUniversities.get(universityToResearchWith).research(techNode);
			} catch (UniversityAlreadyDoingResearchException e) {
				e.printStackTrace();
			}
		}
	}

	public void decreaseItemsResearching() {
		itemsResearching--;
	}
}
