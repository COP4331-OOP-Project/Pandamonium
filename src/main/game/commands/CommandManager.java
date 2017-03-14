package game.commands;

import java.util.ArrayList;
import java.util.ListIterator;

import game.Player;
import game.entities.EntityId;

public class CommandManager {
	private iCommandable commandableItem;
	private CommandExecutor commandExecutor = new CommandExecutor();
	private Player currentPlayer;
	private ArrayList<CommandEnum> possibleCommands = new ArrayList<>();
	private CommandEnum selectedCommand;
	private ListIterator<CommandEnum> commandIterator;
	
	public void updateCommandList() {
			possibleCommands = commandableItem.getCommands();
	}
	
	public void cycleForward() {
		if (commandableItem != null) {
			if (commandIterator.hasNext()) {
				selectedCommand = commandIterator.next();
			} else {
				selectedCommand = possibleCommands.get(0);
				commandIterator = possibleCommands.listIterator(0);
			}
		}

	}

	public void cycleBackward() {
		if (commandableItem != null) {
			if (commandIterator.hasPrevious()) {
				selectedCommand = commandIterator.previous();
			} else {
				selectedCommand = possibleCommands.get(possibleCommands.size() - 1);
				commandIterator = possibleCommands.listIterator(possibleCommands.size() - 1);
			}
		}
	}

	public void addMoveToList(int degrees) {
		
	}
	
	public void execute(EntityId selectedEntity) {
		commandExecutor.executeCommand(selectedEntity, selectedCommand, currentPlayer);
	}
	
	public void setPlayer(Player currentPlayer) {
		this.currentPlayer = currentPlayer;
		commandableItem = null;
		selectedCommand = null;
		commandIterator = null;
	}
	
	public void updateSelectedEntity(iCommandable commandableItem) {
		this.commandableItem = commandableItem;
		updateCommandList();
		selectedCommand = possibleCommands.get(0);
		commandIterator = possibleCommands.listIterator();
	}
	
	public void setCommand(CommandEnum command) {
		selectedCommand = command;
	}
	
	public CommandEnum getCurrentCommand() {
		return selectedCommand;
	}
	
	public ArrayList<CommandEnum> getPossibleCommands() {
		return possibleCommands;
	}
}
