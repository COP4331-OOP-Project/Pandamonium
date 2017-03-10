package view.game;

import java.awt.Point;

import javafx.event.EventHandler;
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
import view.game.drawers.TechnologyViewItem;

public class TechOverviewPanel extends OverviewPanel{
	private ScrollPane scrollPane = new ScrollPane();
	private TechnologyViewItem fertilizer = new TechnologyViewItem(getAssets(), "Fertilizer", 
			getAssets().getImage("ICON_FOOD_HUMAN"), "+10%");
	private TechnologyViewItem wheelbarrow = new TechnologyViewItem(getAssets(), "Wheelbarrow",
			getAssets().getImage("WORK_RADIUS"), "+1");
	private TechnologyViewItem tent = new TechnologyViewItem(getAssets(), "Tent", 
			getAssets().getImage("WORKER_ON_TILE"), "+1");
	private TechnologyViewItem ironMining = new TechnologyViewItem(getAssets(), "Iron Mining", 
			getAssets().getImage("ICON_METAL"), "+10%");
	private TechnologyViewItem bed = new TechnologyViewItem(getAssets(), "Bed", 
			getAssets().getImage("BREED_WORKER"), "-1 Turn");
	private TechnologyViewItem housing = new TechnologyViewItem(getAssets(), "Housing", 
			getAssets().getImage("WORKER_ON_TILE"), "+2");
	private TechnologyViewItem draftHorse = new TechnologyViewItem(getAssets(), "Draft Horse",
			getAssets().getImage("WORK_RADIUS"), "+1");
	private TechnologyViewItem irrigation = new TechnologyViewItem(getAssets(), "Irrigation", 
			getAssets().getImage("ICON_FOOD_HUMAN"), "+10%");
	private TechnologyViewItem steamPower = new TechnologyViewItem(getAssets(), "Steam Power",
			getAssets().getImage("ICON_POWER"), "+10%");
	private TechnologyViewItem militia = new TechnologyViewItem(getAssets(), "Militia", 
			getAssets().getImage("WORKER_TO_SOLDIER"), "-1 Turn");
	private TechnologyViewItem pesticides = new TechnologyViewItem(getAssets(), "Pesticides", 
			getAssets().getImage("ICON_FOOD_HUMAN"), "+10%");
	private TechnologyViewItem steelMining = new TechnologyViewItem(getAssets(), "Steel Mining", 
			getAssets().getImage("ICON_METAL"), "+10%");
	private TechnologyViewItem barracks = new TechnologyViewItem(getAssets(), "Barracks", 
			getAssets().getImage("WORKER_TO_SOLDIER"), "-1 Turn");
	private TechnologyViewItem beer = new TechnologyViewItem(getAssets(), "Beer", 
			getAssets().getImage("BREED_WORKER"), "-1 Turn");
	private TechnologyViewItem blastFurnace = new TechnologyViewItem(getAssets(), "Blast Furnace",
			getAssets().getImage("ICON_METAL"), "+10%");
	private TechnologyViewItem roads = new TechnologyViewItem(getAssets(), "Roads",
			getAssets().getImage("WORK_RADIUS"), "+1");
	private TechnologyViewItem vodka = new TechnologyViewItem(getAssets(), "Vodka", 
			getAssets().getImage("BREED_WORKER"), "-1 Turn");
	private TechnologyViewItem urbanPlanning = new TechnologyViewItem(getAssets(), "Urban Planning", 
			getAssets().getImage("WORKER_ON_TILE"), "+2");
	private TechnologyViewItem windPower = new TechnologyViewItem(getAssets(), "Wind Power",
			getAssets().getImage("ICON_POWER"), "+10%");
	private TechnologyViewItem militaryAcademy = new TechnologyViewItem(getAssets(), "Military Academy", 
			getAssets().getImage("WORKER_TO_SOLDIER"), "-1 Turn");
	private TechnologyViewItem nuclearPower = new TechnologyViewItem(getAssets(), "Nuclear Power",
			getAssets().getImage("ICON_POWER"), "+10%");
	private GraphicsContext techGraphics;
	private Canvas canvas;
	private Group root;
	
	public TechOverviewPanel(GameModelAdapter gameModelAdapter, AssetManager assets, ViewEnum view, Group root) {
		super(gameModelAdapter, assets, view);
		this.root = root;
		scrollPane.setVbarPolicy(ScrollBarPolicy.NEVER);
		scrollPane.setHbarPolicy(ScrollBarPolicy.ALWAYS);
		canvas = new Canvas(); //This is the canvas that goes inside of the scroll pane
		techGraphics = canvas.getGraphicsContext2D();
		scrollPane.setContent(canvas);
		scrollPane.addEventFilter(ScrollEvent.SCROLL,new EventHandler<ScrollEvent>() {
	        @Override
	        public void handle(ScrollEvent event) {
	            if (event.getDeltaY() != 0) { 
	                event.consume(); //This disables vertical scrolling in the scroll pane
	            }
	        }
	    });
		 //This sets the style of scrollPane to that specified in the CSS document
		scrollPane.getStyleClass().setAll("scroll");
		techGraphics.setFill(Color.WHITE);
	}

	public void draw(GraphicsContext g, Point screenDimensions, long currentPulse) {
		scrollPane.toFront();
		techGraphics.clearRect(0, 0, 2500, screenDimensions.y);
		scrollPane.setMaxWidth(screenDimensions.x - 148);
		scrollPane.setMaxHeight(488);
		scrollPane.setTranslateX(74);
		scrollPane.setTranslateY(50);
		canvas.setWidth(2450);
		canvas.setHeight(screenDimensions.y - 147);
		checkFoodIcons();
		drawTechnologies();
		drawConnectors();
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

	public void showGUIElements() {
		root.getChildren().add(scrollPane);
	}

	public void hideGUIElements() {
		root.getChildren().remove(scrollPane);
	}
}
