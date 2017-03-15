package game.commands;

import java.util.ArrayList;

public interface iCommandable {
	public ArrayList<CommandEnum> getCommands();
	public ArrayList<SubCommandEnum> getSubCommands();
}
