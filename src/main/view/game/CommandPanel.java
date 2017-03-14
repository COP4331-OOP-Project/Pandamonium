package view.game;

import java.awt.Point;
import java.util.ArrayList;

import game.commands.CommandEnum;
import game.mode.Mode;
import javafx.scene.Group;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import view.GameModelAdapter;
import view.Panel;
import view.ViewEnum;
import view.assets.AssetManager;

import java.awt.*;

public class CommandPanel extends Panel{
	private static final int COMMAND_Y_NORMAL = 99;
	private static final int COMMAND_Y_RP = 50;
	private static final int ICON_WIDTH = 55;
	private static final int SPACING = 15;
	private int yDistance = COMMAND_Y_NORMAL;
	private DropShadow ds = new DropShadow();
	private Point screenDimensions;
	private AnchorPane commandButtonPane = new AnchorPane();
	private Group root;
	private Point c1 = new Point();
	private Point c2 = new Point();
	private Point c3 = new Point();
	private Point c4 = new Point();
	private Point c5 = new Point();
	private Point c6 = new Point();
	private Point c7 = new Point();
	private Point c8 = new Point();
	private Point c9 = new Point();
	private Point c10 = new Point();
	private Point c11 = new Point();
	private Point c12 = new Point();
	private CommandButton build = new CommandButton(CommandEnum.BUILD_STRUCTURE, getImage("COMMAND_BUILD"));
	private CommandButton heal = new CommandButton(CommandEnum.HEAL, getImage("COMMAND_HEAL"));
	private CommandButton attack = new CommandButton(CommandEnum.ATTACK, getImage("COMMAND_ATTACK"));
	private CommandButton defend = new CommandButton(CommandEnum.DEFEND, getImage("COMMAND_DEFEND"));
	private CommandButton powerUp = new CommandButton(CommandEnum.POWER_UP, getImage("COMMAND_POWER_UP"));
	private CommandButton powerDown = new CommandButton(CommandEnum.POWER_DOWN, getImage("COMMAND_POWER_DOWN"));
	private CommandButton cancelQueue = new CommandButton(CommandEnum.CANCEL_QUEUE, getImage("COMMAND_CANCEL_QUEUE"));
	private CommandButton decommission = new CommandButton(CommandEnum.DECOMMISSION, getImage("COMMAND_DECOMMISSION"));
	private CommandButton move = new CommandButton(CommandEnum.MOVE, getImage("COMMAND_MOVE"));
	private CommandButton assignWorker = new CommandButton(CommandEnum.ASSIGN_WORKER, getImage("COMMAND_ASSIGN_WORKER"));
	private CommandButton unassignAllWorker = new CommandButton(CommandEnum.UNASSIGN_ALL_WORKERS, getImage("COMMAND_UNASSIGN_ALL_WORKERS"));
	private CommandButton pickupWorker = new CommandButton(CommandEnum.PICK_UP_WORKER, getImage("COMMAND_PICKUP_WORKER"));
	private CommandButton dropoffWorker = new CommandButton(CommandEnum.DROP_OFF_WORKER, getImage("COMMAND_DROP_OFF_WORKER"));
	private CommandButton startProspecting = new CommandButton(CommandEnum.START_PROSPECTING, getImage("COMMAND_START_PROSPECTING"));
	private CommandButton stopProspecting = new CommandButton(CommandEnum.STOP_PROSPECTING, getImage("COMMAND_STOP_PROSPECTING"));
	private CommandButton foundCapitol = new CommandButton(CommandEnum.FOUND_CAPITOL, getImage("COMMAND_FOUND_CAPITOL"));
	private CommandButton farm = new CommandButton(CommandEnum.WORKER_FARM, getImage("ICON_FOOD_HUMAN"));
	private CommandButton mine = new CommandButton(CommandEnum.WORKER_MINE, getImage("ICON_METAL"));
	private CommandButton generate = new CommandButton(CommandEnum.WORKER_GENERATE, getImage("ICON_POWER"));
	private CommandButton breed = new CommandButton(CommandEnum.BREED_WORKERS, getImage("BREED_WORKER"));
	private CommandButton createSoldiers = new CommandButton(CommandEnum.CREATE_SOLDIERS, getImage("WORKER_TO_SOLDIER_HUMAN"));
	private CommandButton disbandArmy = new CommandButton(CommandEnum.DISBAND_ARMY, getImage("COMMAND_FOUND_CAPITOL"));
	private CommandButton reinforceArmy = new CommandButton(CommandEnum.REINFORCE_ARMY, getImage("COMMAND_GOTO_RALLY_POINT"));
	private CommandButton moveRallyPoint = new CommandButton(CommandEnum.MOVE_RALLY_POINT, getImage("COMMAND_GOTO_RALLY_POINT"));
	private ArrayList<CommandButton> commandButtons = new ArrayList<>();
	ArrayList<CommandEnum> possibleCommands = new ArrayList<>();
	private HoverPanel hoverPanel;
	
	public CommandPanel(GameModelAdapter gameModelAdapter, Group root, AssetManager assets, ViewEnum view) {
		super(gameModelAdapter, assets, view);
		setIsVisible(false);
		this.root = root;
		hoverPanel = new HoverPanel(gameModelAdapter, assets, view);
		setUpCommandButton(build);
		setUpCommandButton(heal);
		setUpCommandButton(attack);
		setUpCommandButton(defend);
		setUpCommandButton(powerUp);
		setUpCommandButton(powerDown);
		setUpCommandButton(cancelQueue);
		setUpCommandButton(decommission);
		setUpCommandButton(move);
		setUpCommandButton(assignWorker);
		setUpCommandButton(unassignAllWorker);
		setUpCommandButton(pickupWorker);
		setUpCommandButton(dropoffWorker);
		setUpCommandButton(startProspecting);
		setUpCommandButton(stopProspecting);
		setUpCommandButton(foundCapitol);
		setUpCommandButton(farm);
		setUpCommandButton(mine);
		setUpCommandButton(generate);
		setUpCommandButton(breed);
		setUpCommandButton(createSoldiers);
		setUpCommandButton(disbandArmy);
		setUpCommandButton(reinforceArmy);
		setUpCommandButton(moveRallyPoint);
	}
	
	public void setUpCommandButton(CommandButton commandButton) {
		commandButton.getStyleClass().setAll("commandButton");
		commandButton.setOnAction(event -> {});
		commandButtonPane.getChildren().removeAll();
		commandButtonPane.getChildren().add(commandButton);
		commandButtons.add(commandButton);
		commandButton.setOnAction(event -> {
			setCommand(commandButton.getCommand());
			getAdapter().executeCommand();
		});
	}

	public void draw(GraphicsContext g, Point screenDimensions, long currentPulse) {
		this.screenDimensions = screenDimensions;
		possibleCommands = getAdapter().getCommands();
		drawCommandPanel(g);
	}

    private void drawCommandPanel(GraphicsContext g) {
    	if (getAdapter().getCurrentMode() == Mode.RALLY_POINT) {
    		yDistance = COMMAND_Y_RP;
    	} else {
    		yDistance = COMMAND_Y_NORMAL;
    	}
    	if (getAdapter().getPlayer() == 0) {
    		farm.setIcon(getImage("ICON_FOOD_HUMAN"));
    		createSoldiers.setIcon(getImage("WORKER_TO_SOLDIER_HUMAN"));
    	} else {
    		farm.setIcon(getImage("ICON_FOOD_PANDA"));
    		createSoldiers.setIcon(getImage("WORKER_TO_SOLDIER_PANDA"));
    	}
    	if (possibleCommands.size() >= 1 && possibleCommands.size() <= 3) {
    		g.drawImage(getImage("GUI_COMMAND_PANEL1"), 0, yDistance);
    	} else if (possibleCommands.size() >= 4 && possibleCommands.size() <= 6) {
    		g.drawImage(getImage("GUI_COMMAND_PANEL2"), 0, yDistance);
    	} else if (possibleCommands.size() >= 7 && possibleCommands.size() <= 9) {
    		g.drawImage(getImage("GUI_COMMAND_PANEL3"), 0, yDistance);
    	} else if (possibleCommands.size() >= 10 && possibleCommands.size() <= 12) {
    		g.drawImage(getImage("GUI_COMMAND_PANEL4"), 0, yDistance);
    	}
    	updateCommandButtonLocations(g);
    	drawCommandButtons(g);
    }

    private void updateCommandButtonLocations(GraphicsContext g) {
    	c1.x = 0;
    	c1.y = yDistance;
    	c2.x = ICON_WIDTH;
    	c2.y = yDistance;
    	c3.x = ICON_WIDTH * 2;
    	c3.y = yDistance;
    	c4.x = 0;
    	c4.y = yDistance + ICON_WIDTH;
    	c5.x = ICON_WIDTH;
    	c5.y = yDistance + ICON_WIDTH;
    	c6.x = ICON_WIDTH * 2;
    	c6.y = yDistance + ICON_WIDTH;
    	c7.x = 0;
    	c7.y = yDistance + ICON_WIDTH * 2;
    	c8.x = ICON_WIDTH;
    	c8.y = yDistance + ICON_WIDTH * 2;
    	c9.x = ICON_WIDTH * 2;
    	c9.y = yDistance + ICON_WIDTH * 2;
    	c10.x = 0;
    	c10.y = yDistance + ICON_WIDTH * 3;
    	c11.x = ICON_WIDTH;
    	c11.y = yDistance + ICON_WIDTH * 3;
    	c12.x = ICON_WIDTH * 3;
    	c12.y = yDistance + ICON_WIDTH * 3;
}

    private void drawCommandButtons(GraphicsContext g) {
    	 
		for (CommandButton commandButton : commandButtons) {
			boolean commandExists = false;
			for (int i = 0; i < possibleCommands.size(); i++) {
    			if (commandButton.getCommand() == possibleCommands.get(i)) {
    				if (getAdapter().getSelectedCommand() == commandButton.getCommand()) {
						commandButton.getStyleClass().setAll("commandButtonSelected");
					} else {
						commandButton.getStyleClass().setAll("commandButton");
					}
    				switch (i) {
    					case 0:
    						commandButton.setTranslateX(c1.x + SPACING);
    						commandButton.setTranslateY(c1.y + SPACING);
    						break;
    					case 1:
    						commandButton.setTranslateX(c2.x + SPACING);
    						commandButton.setTranslateY(c2.y + SPACING);
    						break;
    					case 2:
    						commandButton.setTranslateX(c3.x + SPACING);
    						commandButton.setTranslateY(c3.y + SPACING);
    						break;
    					case 3:
    						commandButton.setTranslateX(c4.x + SPACING);
    						commandButton.setTranslateY(c4.y + SPACING);
    						break;
    					case 4:
    						commandButton.setTranslateX(c5.x + SPACING);
    						commandButton.setTranslateY(c5.y + SPACING);
    						break;
    					case 5:
    						commandButton.setTranslateX(c6.x + SPACING);
    						commandButton.setTranslateY(c6.y + SPACING);
    						break;
    					case 6:
    						commandButton.setTranslateX(c7.x + SPACING);
    						commandButton.setTranslateY(c7.y + SPACING);
    						break;
    					case 7:
    						commandButton.setTranslateX(c8.x + SPACING);
    						commandButton.setTranslateY(c8.y + SPACING);
    						break;
    					case 8:
    						commandButton.setTranslateX(c9.x + SPACING);
    						commandButton.setTranslateY(c9.y + SPACING);
    						break;
    					case 9:
    						commandButton.setTranslateX(c10.x + SPACING);
    						commandButton.setTranslateY(c10.y + SPACING);
    						break;
    					case 10:
    						commandButton.setTranslateX(c11.x + SPACING);
    						commandButton.setTranslateY(c11.y + SPACING);
    						break;
    					case 11:
    						commandButton.setTranslateX(c12.x + SPACING);
    						commandButton.setTranslateY(c12.y + SPACING);
    						break;
    				}
    				commandExists = true;
    			}
    			if (commandExists) {
    				commandButton.setVisible(true);
    			} else {
    				commandButton.setVisible(false);
    			}
    		}
    	}
    }

	public void hideGUIElements() {
		root.getChildren().remove(commandButtonPane);
	}

	public void showGUIElements() {
		root.getChildren().add(commandButtonPane);
	}
}
