package view.game;

import java.awt.Point;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.ToggleButton;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import view.GameModelAdapter;
import view.Panel;
import view.ViewEnum;
import view.assets.AssetManager;

public class CommandPanel extends Panel{
	private static final int COMMAND_Y_NORMAL = 99;
	private static final int COMMAND_Y_RP = 50;
	private static final int ICON_WIDTH = 55;
	private static final int SPACING = 15;
	private int yDistance = COMMAND_Y_NORMAL;
	private DropShadow ds = new DropShadow();
	private Point screenDimensions;
	private AnchorPane commandToggleButtons = new AnchorPane();
	private Group root;
	private ToggleButton commandBuild = new ToggleButton();
	private ToggleButton commandHeal = new ToggleButton();
	private ToggleButton commandAttack = new ToggleButton();
	private ToggleButton commandDefend = new ToggleButton();
	private ToggleButton commandPowerUp = new ToggleButton();
	private ToggleButton commandPowerDown = new ToggleButton();
	private ToggleButton cancelQueue = new ToggleButton();
	private ToggleButton commandDecommission = new ToggleButton();
	private ToggleButton commandMove = new ToggleButton();
	private HoverPanel hoverPanel;
	
	public CommandPanel(GameModelAdapter gameModelAdapter, Group root, AssetManager assets, ViewEnum view) {
		super(gameModelAdapter, assets, view);
		this.root = root;
		hoverPanel = new HoverPanel(gameModelAdapter, assets, view);
		setUpToggleButton(commandBuild, getAssets().getImage("COMMAND_BUILD"));
		setUpToggleButton(commandHeal, getAssets().getImage("COMMAND_HEAL"));
		setUpToggleButton(commandAttack, getAssets().getImage("COMMAND_ATTACK"));
		setUpToggleButton(commandDefend, getAssets().getImage("COMMAND_DEFEND"));
		setUpToggleButton(commandPowerUp, getAssets().getImage("COMMAND_POWER_UP"));
		setUpToggleButton(commandPowerDown, getAssets().getImage("COMMAND_POWER_DOWN"));
		setUpToggleButton(cancelQueue, getAssets().getImage("COMMAND_CANCEL_QUEUE"));
		setUpToggleButton(commandDecommission, getAssets().getImage("COMMAND_DECOMMISSION"));
		setUpToggleButton(commandMove, getAssets().getImage("COMMAND_MOVE"));
	}
	
	public void setUpToggleButton(ToggleButton toggleButton, Image image) {
		toggleButton.setGraphic(new ImageView(image));
		commandToggleButtons.getChildren().add(toggleButton);
		toggleButton.getStyleClass().setAll("commandButton");
		toggleButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
            }
        });
	}

	public void draw(GraphicsContext gc, Point screenDimensions, long currentPulse) {
		this.screenDimensions = screenDimensions;
        drawCommandPanel(gc);
	}

    private void drawCommandPanel(GraphicsContext g) {
    	/*
    	if (game.getCurrentMode() == ModeEnum.RALLY_POINT) {
    		yDistance = COMMAND_Y_RP;
    	} else {
    		yDistance = COMMAND_Y_NORMAL;
    	}
		g.drawImage(getAssets().getImage("GUI_COMMAND_PANEL"), 0, yDistance);
    	drawAllToggleButtons(g);
    	*/
    }
    /*
    private void drawAllToggleButtons(GraphicsContext g) {
    	drawToggleButton(g, commandBuild, 0, yDistance, CommandEnum.MAKE, game.getCurrentCommand());
        drawToggleButton(g, commandHeal, ICON_WIDTH, yDistance, CommandEnum.HEAL, game.getCurrentCommand());
    	drawToggleButton(g, commandAttack, ICON_WIDTH * 2, yDistance, CommandEnum.ATTACK, game.getCurrentCommand());
    	drawToggleButton(g, commandDefend, 0, yDistance + ICON_WIDTH, CommandEnum.DEFEND, game.getCurrentCommand());
    	drawToggleButton(g, commandPowerUp, ICON_WIDTH, yDistance + ICON_WIDTH, CommandEnum.POWER_UP, game.getCurrentCommand());
    	drawToggleButton(g, commandPowerDown, ICON_WIDTH * 2, yDistance + ICON_WIDTH, CommandEnum.POWER_DOWN, game.getCurrentCommand());
    	drawToggleButton(g, cancelQueue, 0, yDistance + ICON_WIDTH * 2, CommandEnum.CANCEL_COMMAND_QUEUE, game.getCurrentCommand());
    	drawToggleButton(g, commandDecommission, ICON_WIDTH, yDistance + ICON_WIDTH * 2, CommandEnum.DECOMISSION, game.getCurrentCommand());
    	drawToggleButton(g, commandMove, ICON_WIDTH * 2, yDistance + ICON_WIDTH * 2, CommandEnum.MOVE, game.getCurrentCommand());
}
*/
/*
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
			case CANCEL_COMMAND_QUEUE:
				hoverPanel.drawText(g, new Point(190, yDistance), "Cancel Commands");
				break;
			case DECOMISSION:
				hoverPanel.drawText(g, new Point(190, yDistance), "Decommission");
				break;
			case MOVE:
				hoverPanel.drawText(g, new Point(190, yDistance), "Move");
				break;
		}
	}

	private void drawToggleButton(GraphicsContext g, ToggleButton commandBuild, int x, int y, CommandEnum selected, Enum current) {
		if (current == selected) {
			commandBuild.getStyleClass().setAll("commandButtonSelected");
			drawHovered(g, selected);
		} else {
			commandBuild.getStyleClass().setAll("commandButton");
		}
		commandBuild.setTranslateX(x + SPACING);
		commandBuild.setTranslateY(y + SPACING);
	}
	*/
	public void hideGUIElements() {
		root.getChildren().remove(commandToggleButtons);
	}

	public void showGUIElements() {
		root.getChildren().add(commandToggleButtons);
	}

}
