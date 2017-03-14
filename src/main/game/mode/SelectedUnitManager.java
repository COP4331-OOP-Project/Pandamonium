package game.mode;

import java.util.ArrayList;
import java.util.ListIterator;

import game.GameModel;
import game.Player;
import game.entities.EntityId;
import game.entities.units.Unit;
import game.gameboard.Location;


public class SelectedUnitManager {
	private GameModel gameModel;
	private ModeController controlMode;
	private Unit selectedUnit;
	private Location selectedLocation;
	private Player currentPlayer;
	private ArrayList<Unit> units;
	private ListIterator<Unit> unitIterator;

	public SelectedUnitManager(GameModel gameModel,
			ModeController controlMode) {
		this.gameModel = gameModel;
		this.controlMode = controlMode;
	}

	public void updatePlayer() {
		currentPlayer = gameModel.getCurrentPlayer();
		units = currentPlayer.getUnits();
		if (units.size() > 0) {
			unitIterator = units.listIterator();
		} else {
			unitIterator = null;
			selectedUnit = null;
			selectedLocation = null;
		}
	}

	public EntityId getSelected() {
		if (selectedUnit != null)
			return selectedUnit.getEntityId();
		else
			return null;
	}

	public Location getLocation() {
		return selectedLocation;
	}

	public void cycleForward() {
		if(units != null) {
			for (int i = 0; i < units.size() + 1; i++) {
				if (unitIterator.hasNext()) {
					selectedUnit = unitIterator.next();
					selectedLocation = selectedUnit.getLocation();
				} else {
					selectedUnit = units.get(0);
					unitIterator = units.listIterator(0);
					selectedLocation = selectedUnit.getLocation();
				}
				if (submodeFromUnit(selectedUnit) == controlMode.getGameSubmode()) {
					return;
				}
			}
		}
		selectedUnit = null;
		selectedLocation = null;
	}
	
	public void cycleBackward() {
		for (int i = 0; i < units.size() + 1; i++) {
			if (unitIterator.hasPrevious()) {
				selectedUnit = unitIterator.previous();
				selectedLocation = selectedUnit.getLocation();
			} else {
				selectedUnit = units.get(units.size() - 1);
				unitIterator = units.listIterator(units.size() - 1);
				selectedLocation = selectedUnit.getLocation();
			}
			if (submodeFromUnit(selectedUnit) == controlMode.getGameSubmode()) {
				return;
			}
		}
		selectedUnit = null;
		selectedLocation = null;
	}

	private Submode submodeFromUnit(Unit unit) {
		switch (unit.getType()) {
			case COLONIST :
				return Submode.COLONIST;
			case EXPLORER :
				return Submode.EXPLORER;
			case MELEE :
				return Submode.MELEE;
			case RANGE :
				return Submode.RANGED;
			default :
				return null;
		}
	}
}
