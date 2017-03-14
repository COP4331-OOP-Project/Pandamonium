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
	private CommandButton commandBuild = new CommandButton(CommandEnum.MAKE, getImage("COMMAND_BUILD"));
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
    	drawAllCommandButtons(g);
    }

    private void drawAllCommandButtons(GraphicsContext g) {
    	drawCommandButton(g, commandBuild, 0, yDistance, CommandEnum.MAKE);
        drawCommandButton(g, commandHeal, ICON_WIDTH, yDistance, CommandEnum.HEAL);
    	drawCommandButton(g, commandAttack, ICON_WIDTH * 2, yDistance, CommandEnum.ATTACK);
    	drawCommandButton(g, commandDefend, 0, yDistance + ICON_WIDTH, CommandEnum.DEFEND);
    	drawCommandButton(g, commandPowerUp, ICON_WIDTH, yDistance + ICON_WIDTH, CommandEnum.POWER_UP);
    	drawCommandButton(g, commandPowerDown, ICON_WIDTH * 2, yDistance + ICON_WIDTH, CommandEnum.POWER_DOWN);
    	drawCommandButton(g, cancelQueue, 0, yDistance + ICON_WIDTH * 2, CommandEnum.CANCEL_QUEUE);
    	drawCommandButton(g, commandDecommission, ICON_WIDTH, yDistance + ICON_WIDTH * 2, CommandEnum.DECOMMISSION);
    	drawCommandButton(g, commandMove, ICON_WIDTH * 2, yDistance + ICON_WIDTH * 2, CommandEnum.MOVE);
}

	private void drawHovered(GraphicsContext g, CommandEnum selected) {
		switch(selected) {
			case MAKE:
				hoverPanel.drawText(g, new Point(190, yDistance), "Build Entity");
				break;
			case HEAL:
				hoverPanel.drawText(g, new Point(190, yDistance), "Heal Entity");
				break;
			case ATTACK:
				hoverPanel.drawText(g, new Point(190, yDistance), "Attack");
				break;
			case DEFEND:
				hoverPanel.drawText(g, new Point(190, yDistance), "Defend");
				break;
			case POWER_UP:
				hoverPanel.drawText(g, new Point(190, yDistance), "Power Up");
				break;
			case POWER_DOWN:
				hoverPanel.drawText(g, new Point(190, yDistance), "Power Down");
				break;
			case DECOMMISSION:
				hoverPanel.drawText(g, new Point(190, yDistance), "Decommission");
				break;
			case MOVE:
				hoverPanel.drawText(g, new Point(190, yDistance), "Move");
				break;
		}
	}

	private void drawCommandButton(GraphicsContext g, CommandButton commandBuild, int x, int y, CommandEnum selected) {
		//if (current == selected) {
		//	commandBuild.getStyleClass().setAll("commandButtonSelected");
		//	drawHovered(g, selected);
		//} else {
			commandBuild.getStyleClass().setAll("commandButton");
		//}
		commandBuild.setTranslateX(x + SPACING);
		commandBuild.setTranslateY(y + SPACING);
	}

	public void hideGUIElements() {
		root.getChildren().remove(commandButtonPane);
	}

	public void showGUIElements() {
		root.getChildren().add(commandButtonPane);
	}
}
