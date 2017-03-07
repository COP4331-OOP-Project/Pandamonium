package game.mode;

import java.util.ArrayList;

import game.GameModel;
import game.Player;
import game.entities.EntityId;
import game.entities.RallyPoint;
import game.gameboard.Location;

public class SelectedRallyPointManager {
	private RallyPoint selectedRally;
	private Location selectedLocation;
	private GameModel gameModel;
	private ModeController controlMode;
	private Player currentPlayer;
	private ArrayList<RallyPoint> rallyPoints;
	private int selectedElement = -1;
	
	public SelectedRallyPointManager(GameModel gameModel, ModeController controlMode) {
		this.gameModel = gameModel;
		this.controlMode = controlMode;
	}
	
	public void updatePlayer() {
		currentPlayer  = gameModel.getCurrentPlayer();
		rallyPoints = currentPlayer.getRallyPoints();
		if (rallyPoints.size() > 0) {
			selectedRally = rallyPoints.get(0);
			selectedLocation = selectedRally.getLocation();
			selectedElement = 0;
		} else {
			selectedRally = null;
			selectedLocation = null;
			selectedElement = -1;
		}
		cycle(true);
	}

	public EntityId getSelected() {
		if (selectedRally != null)
			return selectedRally.getEntityId();
		else
			return null;
	}

	public Location getLocation() {
		return selectedLocation;
	}

	public void cycle(boolean forward) {
		if (rallyPoints.size() > 0) {
			if (selectedElement == -1) {
				selectedRally = rallyPoints.get(0);
				selectedLocation = selectedRally.getLocation();
				selectedElement = 0;
			} else {
				if (forward) {
					cycleForward();
				} else {
					cycleBackward();
				}
			}
		} else {
			selectedRally = null;
			selectedLocation = null;
			selectedElement = -1;
		}
	}
	
	private void cycleForward() {
		selectedElement = selectedElement + 1 % rallyPoints.size();
		selectedRally = rallyPoints.get(selectedElement);
		selectedLocation = selectedRally.getLocation();
	}
	
	private void cycleBackward() {
		selectedElement--;
		if (selectedElement == -1) {
			selectedElement = rallyPoints.size();
		}
		selectedRally = rallyPoints.get(selectedElement);
		selectedLocation = selectedRally.getLocation();
	}
}
