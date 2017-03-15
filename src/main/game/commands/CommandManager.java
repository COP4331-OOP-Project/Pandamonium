package game.commands;

import java.util.ArrayList;
import java.util.ListIterator;

import game.Player;
import game.entities.EntityId;

public class CommandManager {
	private iCommandable commandableItem;
	private CommandExecutor commandExecutor;
	private Player currentPlayer;
	private ArrayList<CommandEnum> possibleCommands = new ArrayList<>();
	private ArrayList<SubCommandEnum> possibleSubCommands = new ArrayList<>();
	private CommandEnum selectedCommand;
	private SubCommandEnum selectedSubCommand;
	private ListIterator<CommandEnum> commandIterator;

	public CommandManager(Player p) {
		this.currentPlayer = p;
	}
	
	public void updateCommandList() {
			possibleCommands = commandableItem.getCommands();
			possibleSubCommands = commandableItem.getSubCommands();
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

	public void executeSubCommand(EntityId selectedEntity) {
		commandExecutor.executeSubCommand(selectedEntity, selectedSubCommand, currentPlayer);
	}
	
	public void setPlayer(Player currentPlayer) {
		this.currentPlayer = currentPlayer;
		commandableItem = null;
		selectedCommand = null;
		commandIterator = null;
		this.commandExecutor =
				new CommandExecutor(currentPlayer.getStructureManager(),
						currentPlayer.getUnitManager(),
						currentPlayer.getWorkerManager(),
						currentPlayer.getArmyManager(),
						currentPlayer.getPlacementManager());
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

	public void setSubCommand(SubCommandEnum command) {
		selectedSubCommand = command;
	}

	public CommandEnum getCurrentCommand() {
		return selectedCommand;
	}

	public SubCommandEnum getCurrentSubCommand() {
		return selectedSubCommand;
	}

	public ArrayList<CommandEnum> getPossibleCommands() {
		return possibleCommands;
	}

	public ArrayList<SubCommandEnum> getPossibleSubCommands() {
		return possibleSubCommands;
	}
}
