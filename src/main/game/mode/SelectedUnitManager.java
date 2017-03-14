package game.mode;

import game.GameModel;
import game.Player;
import game.entities.EntityId;
import game.entities.units.Unit;
import game.gameboard.Location;

import java.util.ArrayList;

public class SelectedUnitManager {
	private GameModel gameModel;
	private ModeController controlMode;
	private Unit selectedUnit;
	private Location selectedLocation;
	private Player currentPlayer;
	private ArrayList<Unit> units;
	private int selectedElement = -1;

	public SelectedUnitManager(GameModel gameModel,
			ModeController controlMode) {
		this.gameModel = gameModel;
		this.controlMode = controlMode;
	}

	public void updatePlayer() {
		currentPlayer = gameModel.getCurrentPlayer();
		units = currentPlayer.getUnits();
		if (units.size() > 0) {
			cycle(true);
		} else {
			selectedUnit = null;
			selectedLocation = null;
			selectedElement = -1;
		}
		cycle(true);
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

	public void cycle(boolean forward) {
		if (units.size() > 0) {
			if (selectedElement == -1) {
				selectedElement = 0;
			}
			int startingElement = selectedElement;
			if (forward) {
				iterateForward();
			} else {
				iterateBackward();
			}
			while (selectedElement != startingElement) {
				selectedUnit = units.get(selectedElement);
				if (submodeFromUnit(selectedUnit) == controlMode
						.getGameSubmode()) {
					break;
				}
				if (forward) {
					iterateForward();
				} else {
					iterateBackward();
				}
			}
			selectedUnit = units.get(selectedElement);
			selectedLocation = selectedUnit.getLocation();
			if (submodeFromUnit(selectedUnit) != controlMode.getGameSubmode()) {
				selectedUnit = null;
				selectedLocation = null;
				selectedElement = -1;
			}
		} else {
			selectedUnit = null;
			selectedLocation = null;
			selectedElement = -1;
		}
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

	private void iterateForward() {
		selectedElement++;
		if (selectedElement >= units.size()) {
			selectedElement = 0;
		}
	}

	private void iterateBackward() {
		selectedElement--;
		if (selectedElement < 0) {
			selectedElement = units.size() - 1;
		}
	}

}
