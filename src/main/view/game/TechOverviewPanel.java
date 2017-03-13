package view.game;

import java.awt.Point;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Group;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.control.ToggleButton;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.ScrollEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import view.GameModelAdapter;
import view.ViewEnum;
import view.assets.AssetManager;

public class TechOverviewPanel extends OverviewPanel{
	private static final int PANE_WIDTH = 219;
	private static final int PANE_HEIGHT = 80;
	private Label label = new Label("Technology Overview");
	private TechModeEnum currentMode = TechModeEnum.TECHNOLOGY;
	private AnchorPane techBox = new AnchorPane();
	private ScrollPane scrollPane = new ScrollPane();
	private TechViewItem fertilizer = new TechViewItem(getAssets(), "Fertilizer", 
			getAssets().getImage("ICON_FOOD_HUMAN"), "+10%");
	private TechViewItem wheelbarrow = new TechViewItem(getAssets(), "Wheelbarrow",
			getAssets().getImage("WORK_RADIUS"), "+1");
	private TechViewItem tent = new TechViewItem(getAssets(), "Tent", 
			getAssets().getImage("WORKER_ON_TILE"), "+1");
	private TechViewItem ironMining = new TechViewItem(getAssets(), "Iron Mining", 
			getAssets().getImage("ICON_METAL"), "+10%");
	private TechViewItem bed = new TechViewItem(getAssets(), "Bed", 
			getAssets().getImage("BREED_WORKER"), "-1 Turn");
	private TechViewItem housing = new TechViewItem(getAssets(), "Housing", 
			getAssets().getImage("WORKER_ON_TILE"), "+2");
	private TechViewItem draftHorse = new TechViewItem(getAssets(), "Draft Horse",
			getAssets().getImage("WORK_RADIUS"), "+1");
	private TechViewItem irrigation = new TechViewItem(getAssets(), "Irrigation", 
			getAssets().getImage("ICON_FOOD_HUMAN"), "+10%");
	private TechViewItem steamPower = new TechViewItem(getAssets(), "Steam Power",
			getAssets().getImage("ICON_POWER"), "+10%");
	private TechViewItem militia = new TechViewItem(getAssets(), "Militia", 
			getAssets().getImage("WORKER_TO_SOLDIER"), "-1 Turn");
	private TechViewItem pesticides = new TechViewItem(getAssets(), "Pesticides", 
			getAssets().getImage("ICON_FOOD_HUMAN"), "+10%");
	private TechViewItem steelMining = new TechViewItem(getAssets(), "Steel Mining", 
			getAssets().getImage("ICON_METAL"), "+10%");
	private TechViewItem barracks = new TechViewItem(getAssets(), "Barracks", 
			getAssets().getImage("WORKER_TO_SOLDIER"), "-1 Turn");
	private TechViewItem beer = new TechViewItem(getAssets(), "Beer", 
			getAssets().getImage("BREED_WORKER"), "-1 Turn");
	private TechViewItem blastFurnace = new TechViewItem(getAssets(), "Blast Furnace",
			getAssets().getImage("ICON_METAL"), "+10%");
	private TechViewItem roads = new TechViewItem(getAssets(), "Roads",
			getAssets().getImage("WORK_RADIUS"), "+1");
	private TechViewItem vodka = new TechViewItem(getAssets(), "Vodka", 
			getAssets().getImage("BREED_WORKER"), "-1 Turn");
	private TechViewItem urbanPlanning = new TechViewItem(getAssets(), "Urban Planning", 
			getAssets().getImage("WORKER_ON_TILE"), "+2");
	private TechViewItem windPower = new TechViewItem(getAssets(), "Wind Power",
			getAssets().getImage("ICON_POWER"), "+10%");
	private TechViewItem militaryAcademy = new TechViewItem(getAssets(), "Military Academy", 
			getAssets().getImage("WORKER_TO_SOLDIER"), "-1 Turn");
	private TechViewItem nuclearPower = new TechViewItem(getAssets(), "Nuclear Power",
			getAssets().getImage("ICON_POWER"), "+10%");
	private ToggleButton techsToggle = new ToggleButton("Technologies");
	private ToggleButton improvementsToggle = new ToggleButton("Improvements");
	private ComboBox<String> universityComboBox;
	private ObservableList<String> universityList = FXCollections.observableArrayList();
	private ComboBox<String> upgradableComboBox;
	private ObservableList<String> upgradableList = FXCollections.observableArrayList();
	private DropShadow ds = new DropShadow();
	private GraphicsContext techGraphics;
	private Canvas canvas;
	private Group root;
	
	public TechOverviewPanel(GameModelAdapter gameModelAdapter, AssetManager assets, ViewEnum view, Group root) {
		super(gameModelAdapter, assets, view);
		this.root = root;
		setUpCanvas();
		setUpLabel();
		setUpButtons();
		setUpUniversityList();
		setUpUpgradableList();
		setUpScrollPane();
		techBox.getChildren().addAll(canvas, label, techsToggle, improvementsToggle, universityComboBox, upgradableComboBox);
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
	
	private void setUpUniversityList() {
		upgradableComboBox = new ComboBox<String>(universityList);
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
	}
	
	private void setUpUpgradableList() {
		universityComboBox = new ComboBox<String>(upgradableList);
		universityComboBox.setButtonCell(new ListCell<String>(){
		        @Override
		        protected void updateItem(String string, boolean empty) {
		            super.updateItem(string, empty); 
		            if(!(empty || string==null)){
		                setStyle("-fx-text-fill: white");
		                setText(string);
		            }
		       }
		});
		universityComboBox.setTranslateX(700);
		universityComboBox.setTranslateY(17);
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
	}

	public void draw(GraphicsContext g, Point screenDimensions, long currentPulse) {
		techGraphics.clearRect(0, 0, 2500, screenDimensions.y);
		scrollPane.toFront();
		scrollPane.setMaxHeight(488);
		scrollPane.setTranslateX(74);
		scrollPane.setTranslateY(50);
		switch (currentMode) {
			case TECHNOLOGY:
				upgradableComboBox.setVisible(false);
				scrollPane.setMaxWidth(screenDimensions.x - 148);
				scrollPane.setHbarPolicy(ScrollBarPolicy.ALWAYS);
				canvas.setWidth(2450);
				canvas.setTranslateY(0);
				canvas.setHeight(screenDimensions.y - 147);
				checkFoodIcons();
				drawTechnologies();
				drawConnectors();
				break;
			case IMPROVEMENTS:
				upgradableComboBox.setVisible(true);
				scrollPane.setMaxWidth(screenDimensions.x - 148);
				scrollPane.setHbarPolicy(ScrollBarPolicy.NEVER);
				canvas.setWidth(screenDimensions.x  - 148);
				canvas.setTranslateY(-135);
				canvas.setHeight(screenDimensions.y - 147);
				break;
		}
		
	}

	private void checkFoodIcons() {
		if (getAdapter().getPlayer() == 0) {
			fertilizer.setIcon(getAssets().getImage("ICON_FOOD_HUMAN")); 
			irrigation.setIcon(getAssets().getImage("ICON_FOOD_HUMAN"));
			pesticides.setIcon(getAssets().getImage("ICON_FOOD_HUMAN"));
		} else {
			fertilizer.setIcon(getAssets().getImage("ICON_FOOD_PANDA")); 
			irrigation.setIcon(getAssets().getImage("ICON_FOOD_PANDA"));
			pesticides.setIcon(getAssets().getImage("ICON_FOOD_PANDA"));
		}
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
}
