package game.mode;

import java.util.ArrayList;
import java.util.ListIterator;

import game.GameModel;
import game.Player;
import game.entities.Army;
import game.entities.EntityId;
import game.gameboard.Location;

public class SelectedArmyManager {
	private GameModel gameModel;
	private ModeController controlMode;
	private Army selectedArmy;
	private Location selectedLocation;
	private Player currentPlayer;
	private ArrayList<Army> armies;
	private ListIterator<Army> armyIterator;
	
	public SelectedArmyManager(GameModel gameModel, ModeController controlMode) {
		this.gameModel = gameModel;
		this.controlMode = controlMode;
	}
	
	public void updatePlayer() {
		currentPlayer = gameModel.getCurrentPlayer();
		armies = currentPlayer.getArmies();
		if (armies.size() > 0) {
			armyIterator = armies.listIterator();
		} else {
			armyIterator = null;
			selectedArmy = null;
			selectedLocation = null;
		}
	}

	public EntityId getSelected() {
		if (selectedArmy != null)
			return selectedArmy.getEntityId();
		else
			return null;
	}

	public Location getLocation() {
		return selectedArmy.getLocation();
	}

	public void cycleForward() {
		if (armies.size() > 0) {
			if (armyIterator.hasNext()) {
				selectedArmy = armyIterator.next();
				selectedLocation = selectedArmy.getLocation();
			} else {
				selectedArmy = armies.get(0);
				armyIterator = armies.listIterator(0);
				selectedLocation = selectedArmy.getLocation();
			}
		} else {
			selectedArmy = null;
			selectedLocation = null;
		}
	}
	
	public void cycleBackward() {
		if (armies.size() > 0) {
			if (armyIterator.hasPrevious()) {
				selectedArmy = armyIterator.previous();
				selectedLocation = selectedArmy.getLocation();
			} else {
				selectedArmy = armies.get(armies.size() - 1);
				armyIterator = armies.listIterator(armies.size() - 1);
				selectedLocation = selectedArmy.getLocation();
			}
		} else {
			selectedArmy = null;
			selectedLocation = null;
		}
	}
}
