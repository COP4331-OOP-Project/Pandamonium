package view;

import game.GameModel;
import game.Player;
import game.commands.CommandEnum;
import game.commands.SubCommandEnum;
import game.entities.EntityId;
import game.entities.EntitySubtypeEnum;
import game.entities.managers.exceptions.StructureDoesNotExistException;
import game.entities.managers.exceptions.UnitDoesNotExistException;
import game.entities.structures.Structure;
import game.entities.units.Unit;
import game.gameboard.Location;
import game.gameboard.SimpleTile;
import game.mode.Mode;
import game.mode.ModeController;
import game.mode.Submode;

import java.awt.*;
import java.util.ArrayList;

public class GameModelAdapter {
	GameModel gameModel;
	ModeController controlMode;

	public GameModelAdapter(GameModel gameModel, ModeController controlMode) {
		this.gameModel = gameModel;
		this.controlMode = controlMode;
	}

	public void startGame() {
		gameModel.initializeGame();
	}

	public SimpleTile[][] getCurrentTiles() {
		return gameModel.getCurrentPlayer().getSimpleTiles();
	}

	public Mode getCurrentMode() {
		return controlMode.getGameMode();
	}

	public Submode getCurrentSubmode() {
		return controlMode.getGameSubmode();
	}

	public void setMode(Mode mode) {
		controlMode.setMode(mode);
	}

	public void setSubmode(Submode submode) {
		controlMode.setSubmode(submode);
	}

	public int getPlayerId() {
		if (gameModel.getCurrentPlayer() == gameModel.getPlayer(0)) {
			return 0;
		} else {
			return 1;
		}
	}

	// Send whatever info needed for command to execute to controlMode
	
	public Unit getSelectedUnit() {
		try {
			return gameModel.getCurrentPlayer().getUnit(getSelectedEntity());
		} catch (UnitDoesNotExistException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public Structure getSelectedStructure() throws StructureDoesNotExistException {
		return gameModel.getCurrentPlayer().getStructure(getSelectedEntity());
	}

	public int getTurnNum() {
		return gameModel.getTurnNum();
	}

	// Yes I know these violate LoD
	public int getCurrentNutrients() {
		return (int) gameModel.getCurrentPlayer().getNutrients().getAmount();
	}

	public int getCurrentMetal() {
		return (int) gameModel.getCurrentPlayer().getMetal().getAmount();
	}

	public int getCurrentPower() {
		return (int) gameModel.getCurrentPlayer().getPower().getAmount();
	}

	public ArrayList<Unit> getCurrentUnits() {
		return gameModel.getCurrentPlayer().getUnits();
	}
	
	public ArrayList<Structure> getStructures() {
		return gameModel.getCurrentPlayer().getStructures();
	}
	
	public EntityId getSelectedEntity() {
		return controlMode.getSelectedEntity();
	}

	public Point getSelectedPoint() {
		if (controlMode.getSelectedLocation() != null) {
			return locationToPoint(controlMode.getSelectedLocation());
		} else {
			return null;
		}
	}
	
	public Point locationToPoint(Location location) {
		return new Point(location.getX(), location.getY());
	}
	
	public Point getTurnStartPoint() {
		for (Structure structure : getStructures()) {
			if (structure.getType() == EntitySubtypeEnum.CAPITOL) {
				return new Point(structure.getLocationX(), structure.getLocationY());
			}
		}
		for (Unit unit : getCurrentUnits()) {
			if (unit.getType() == EntitySubtypeEnum.COLONIST) {
				return new Point(unit.getLocationX(), unit.getLocationY());
			}
		}
		return new Point(0,0);
	}
	
	public void executeCommand() {
		controlMode.execute();
	}
	
	public ArrayList<CommandEnum> getCommands() {
		return controlMode.getCommands();
	}

	public ArrayList<SubCommandEnum> getSubCommands() {
		return controlMode.getSubCommands();
	}

	public CommandEnum getSelectedCommand() {
		return controlMode.getSelectedCommand();
	}

	public SubCommandEnum getSelectedSubCommand() {
		return controlMode.getSelectedSubCommand();
	}

	public void endTurn() {
		controlMode.endTurn();
	}

	public void setCommand(CommandEnum command) {
		controlMode.setCommand(command);
	}

	public void setSubCommand(SubCommandEnum command) { controlMode.setSubCommand(command); }
}
