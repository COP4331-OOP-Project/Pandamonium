package game.mode;

import java.util.ArrayList;

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
	private int selectedElement = -1;
	
	public SelectedArmyManager(GameModel gameModel, ModeController controlMode) {
		this.gameModel = gameModel;
		this.controlMode = controlMode;
	}
	
	public void updatePlayer() {
		currentPlayer  = gameModel.getCurrentPlayer();
		armies = currentPlayer.getArmies();
		if (armies.size() > 0) {
			selectedArmy = armies.get(0);
			selectedLocation = selectedArmy.getLocation();
			selectedElement = 0;
		} else {
			selectedArmy = null;
			selectedLocation = null;
			selectedElement = -1;
		}
		cycle(true);
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

	public void cycle(boolean forward) {
		if (armies.size() > 0) {
			if (selectedElement == -1) {
				selectedArmy = armies.get(0);
				selectedLocation = selectedArmy.getLocation();
				selectedElement = 0;
			} else {
				if (forward) {
					cycleForward();
				} else {
					cycleBackward();
				}
			}
		} else {
			selectedArmy = null;
			selectedLocation = null;
			selectedElement = -1;
		}
	}
	
	private void cycleForward() {
		selectedElement = selectedElement + 1 % armies.size();
		selectedArmy = armies.get(selectedElement);
		selectedLocation = selectedArmy.getLocation();
	}
	
	private void cycleBackward() {
		selectedElement--;
		if (selectedElement == -1) {
			selectedElement = armies.size();
		}
		selectedArmy = armies.get(selectedElement);
		selectedLocation = selectedArmy.getLocation();
	}
}
