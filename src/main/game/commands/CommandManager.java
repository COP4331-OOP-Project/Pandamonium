package game.commands;

import java.util.ArrayList;
import java.util.ListIterator;

import game.Player;
import game.entities.Army;
import game.entities.EntityId;
import game.entities.RallyPoint;
import game.entities.managers.exceptions.ArmyDoesNotExistException;
import game.entities.managers.exceptions.RallyPointDoesNotExistException;
import game.entities.managers.exceptions.StructureDoesNotExistException;
import game.entities.managers.exceptions.UnitDoesNotExistException;
import game.entities.structures.Structure;
import game.entities.units.Unit;

public class CommandManager {
	private iCommandable commandableItem;
	private Player currentPlayer;
	private ArrayList<CommandEnum> possibleCommands = new ArrayList<>();
	private CommandEnum selectedCommand;
	private ListIterator<CommandEnum> commandIterator;
	
	public void updateCommandList() {
			possibleCommands = commandableItem.getCommands();
	}
	
	public void cycleForward() {
		if (commandIterator.hasNext()) {
			selectedCommand = commandIterator.next();
		} else {
			selectedCommand = possibleCommands.get(0);
			commandIterator = possibleCommands.listIterator(0);
		}
	}

	public void cycleBackward() {
		if (commandIterator.hasPrevious()) {
			selectedCommand = commandIterator.previous();
		} else {
			selectedCommand = possibleCommands.get(possibleCommands.size() - 1);
			commandIterator = possibleCommands.listIterator(possibleCommands.size() - 1);
		}
	}

	public void addMoveToList(int degrees) {
		
	}
	
	public void execute() {
		
	}
	
	public void setPlayer(Player currentPlayer) {
		this.currentPlayer = currentPlayer;
	}
	
	public void updateSelectedEntity(iCommandable commandableItem) {
		this.commandableItem = commandableItem;
		updateCommandList();
		selectedCommand = possibleCommands.get(0);
		commandIterator = possibleCommands.listIterator();
	}
	
	public void executeCommand(CommandEnum command) {
		selectedCommand = command;
		execute();
	}
	
	public CommandEnum getCurrentCommand() {
		return selectedCommand;
	}

}
