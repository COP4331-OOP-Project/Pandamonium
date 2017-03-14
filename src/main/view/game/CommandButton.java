package view.game;

import game.commands.CommandEnum;
import javafx.scene.control.ToggleButton;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class CommandButton extends ToggleButton{
	private CommandEnum command;
	
	public CommandButton(CommandEnum command, Image icon) {
		this.command = command;
		this.setGraphic(new ImageView(icon));
	}
	
	public CommandEnum getCommand() {
		return command;
	}
}
