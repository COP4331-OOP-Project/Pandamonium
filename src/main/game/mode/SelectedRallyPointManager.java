package game.mode;

import java.util.ArrayList;
import java.util.ListIterator;

import game.GameModel;
import game.Player;
import game.entities.Army;
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
	private ListIterator<RallyPoint> rallyIterator;
	
	public SelectedRallyPointManager(GameModel gameModel, ModeController controlMode) {
		this.gameModel = gameModel;
		this.controlMode = controlMode;
	}
	
	public void updatePlayer() {
		currentPlayer = gameModel.getCurrentPlayer();
		rallyPoints = currentPlayer.getRallyPoints();
		if (rallyPoints.size() > 0) {
			rallyIterator = rallyPoints.listIterator();
		} else {
			rallyIterator = null;
			selectedRally = null;
			selectedLocation = null;
		}
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
	
	public void cycleForward() {
		if(rallyPoints!=null) {
			if (rallyPoints.size() > 0) {
				if (rallyIterator.hasNext()) {
					selectedRally = rallyIterator.next();
					selectedLocation = selectedRally.getLocation();
				} else {
					selectedRally = rallyPoints.get(0);
					rallyIterator = rallyPoints.listIterator(0);
					selectedLocation = selectedRally.getLocation();
				}
			}
		}else {
			selectedRally = null;
			selectedLocation = null;
		}
	}
	
	public void cycleBackward() {
		if (rallyPoints.size() > 0) {
			if (rallyIterator.hasPrevious()) {
				selectedRally = rallyIterator.previous();
				selectedLocation = selectedRally.getLocation();
			} else {
				selectedRally = rallyPoints.get(rallyPoints.size() - 1);
				rallyIterator = rallyPoints.listIterator(rallyPoints.size() - 1);
				selectedLocation = selectedRally.getLocation();
			}
		} else {
			selectedRally = null;
			selectedLocation = null;
		}
	}
}
