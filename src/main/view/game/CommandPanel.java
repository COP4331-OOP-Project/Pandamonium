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
	private CommandButton commandBuild = new CommandButton(CommandEnum.BUILD_STRUCTURE, getImage("COMMAND_BUILD"));
	private CommandButton commandHeal = new CommandButton(CommandEnum.HEAL, getImage("COMMAND_HEAL"));
	private CommandButton commandAttack = new CommandButton(CommandEnum.ATTACK, getImage("COMMAND_ATTACK"));
	private CommandButton commandDefend = new CommandButton(CommandEnum.DEFEND, getImage("COMMAND_DEFEND"));
	private CommandButton commandPowerUp = new CommandButton(CommandEnum.POWER_UP, getImage("COMMAND_POWER_UP"));
	private CommandButton commandPowerDown = new CommandButton(CommandEnum.POWER_DOWN, getImage("COMMAND_POWER_DOWN"));
	private CommandButton cancelQueue = new CommandButton(CommandEnum.CANCEL_QUEUE, getImage("COMMAND_CANCEL_QUEUE"));
	private CommandButton commandDecommission = new CommandButton(CommandEnum.DECOMMISSION, getImage("COMMAND_DECOMMISSION"));
	private CommandButton commandMove = new CommandButton(CommandEnum.MOVE, getImage("COMMAND_MOVE"));
	private CommandButton commandassignWorker = new CommandButton(CommandEnum.ASSIGN_WORKER, getImage("COMMAND_ASSIGN_WORKER"));
	private CommandButton unassignAllWorker = new CommandButton(CommandEnum.UNASSIGN_ALL_WORKERS, getImage("COMMAND_UNASSIGN_ALL_WORKERS"));
	private CommandButton commandpickupWorker = new CommandButton(CommandEnum.PICK_UP_WORKER, getImage("COMMAND_PICKUP_WORKER"));
	private CommandButton commanddropoffWorker = new CommandButton(CommandEnum.DROP_OFF_WORKER, getImage("COMMAND_DROP_OFF_WORKER"));
	private CommandButton commandstartProspecting = new CommandButton(CommandEnum.START_PROSPECTING, getImage("COMMAND_START_PROSPECTING"));
	private CommandButton commandstopProspecting = new CommandButton(CommandEnum.STOP_PROSPECTING, getImage("COMMAND_STOP_PROSPECTING"));
	private CommandButton commandFoundCapitol = new CommandButton(CommandEnum.FOUND_CAPITOL, getImage("COMMAND_FOUND_CAPITOL"));
	private ArrayList<CommandButton> commandButtons = new ArrayList<>();
	private HoverPanel hoverPanel;
	
	public CommandPanel(GameModelAdapter gameModelAdapter, Group root, AssetManager assets, ViewEnum view) {
		super(gameModelAdapter, assets, view);
		setIsVisible(false);
		this.root = root;
		hoverPanel = new HoverPanel(gameModelAdapter, assets, view);
		setUpCommandButton(commandBuild);
		setUpCommandButton(commandHeal);
		setUpCommandButton(commandAttack);
		setUpCommandButton(commandDefend);
		setUpCommandButton(commandPowerUp);
		setUpCommandButton(commandPowerDown);
		setUpCommandButton(cancelQueue);
		setUpCommandButton(commandDecommission);
		setUpCommandButton(commandMove);
	}
	
	public void setUpCommandButton(CommandButton CommandButton) {
		CommandButton.getStyleClass().setAll("commandButton");
		CommandButton.setOnAction(event -> {});
		commandButtonPane.getChildren().removeAll();
		commandButtonPane.getChildren().add(CommandButton);
		commandButtons.add(CommandButton);
	}

	public void draw(GraphicsContext g, Point screenDimensions, long currentPulse) {
		this.screenDimensions = screenDimensions;
        drawCommandPanel(g);
	}

    private void drawCommandPanel(GraphicsContext g) {
    	if (getAdapter().getCurrentMode() == Mode.RALLY_POINT) {
    		yDistance = COMMAND_Y_RP;
    	} else {
    		yDistance = COMMAND_Y_NORMAL;
    	}
		g.drawImage(getImage("GUI_COMMAND_PANEL"), 0, yDistance);
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
}

    private void drawCommandButtons(GraphicsContext g) {
    	ArrayList<CommandEnum> possibleCommands = getAdapter().getCommands();
		for (CommandButton commandButton : commandButtons) {
			boolean commandExists = false;
			for (int i = 0; i < possibleCommands.size(); i++) {
    			if (commandButton.getCommand() == possibleCommands.get(i)) {
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
    /*
	private void drawCommandButton(GraphicsContext g, CommandButton commandBuild, int x, int y, CommandEnum selected) {
	//	if (current == selected) {
			commandBuild.getStyleClass().setAll("commandButtonSelected");
		//	drawHovered(g, selected);
		} else {
			commandBuild.getStyleClass().setAll("commandButton");
		}
		commandBuild.setTranslateX(x + SPACING);
		commandBuild.setTranslateY(y + SPACING);
	}
*/
	public void hideGUIElements() {
		root.getChildren().remove(commandButtonPane);
	}

	public void showGUIElements() {
		root.getChildren().add(commandButtonPane);
	}
}
