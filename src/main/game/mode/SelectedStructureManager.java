package game.mode;

import java.util.ArrayList;

import game.GameModel;
import game.Player;
import game.entities.EntityId;
import game.entities.structures.Structure;
import game.gameboard.Location;

public class SelectedStructureManager {
	private GameModel gameModel;
	private ModeController controlMode;
	private Structure selectedStructure;
	private Location selectedLocation;
	private Player currentPlayer;
	private ArrayList<Structure> structures;
	private int selectedElement = -1;
	
	public SelectedStructureManager(GameModel gameModel, ModeController controlMode) {
		this.gameModel = gameModel;
		this.controlMode = controlMode;
	}
	
	public void updatePlayer() {
		currentPlayer  = gameModel.getCurrentPlayer();
		structures = currentPlayer.getStructures();
		if (structures.size() > 0) {
			cycle(true);
		} else {
			selectedStructure = null;
			selectedLocation = null;
			selectedElement = -1;
		}
		cycle(true);
	}

	public EntityId getSelected() {
		if (selectedStructure != null)
			return selectedStructure.getEntityId();
		else 
			return null;
	}

	public Location getLocation() {
		return selectedLocation;
	}

	public void cycle(boolean forward) {
		if (structures.size() > 0) {
			if (selectedElement == -1) {
				selectedElement = 0;
			} 
			int startingElement = selectedElement;
			if (forward) {
				iterateForward();
			} else {
				iterateBackward();
			}
			while(selectedElement != startingElement) {
				selectedStructure = structures.get(selectedElement);
				if (submodeFromStructure(selectedStructure) == controlMode.getGameSubmode()) {
					break;
				}
				if (forward) {
					iterateForward();
				} else {
					iterateBackward();
				}
			}
			selectedStructure = structures.get(selectedElement);
			selectedLocation = selectedStructure.getLocation();
			if (submodeFromStructure(selectedStructure) != controlMode.getGameSubmode()) {
				selectedStructure = null;
				selectedLocation = null;
				selectedElement = -1;
			}
		} else {
			selectedStructure = null;
			selectedLocation = null;
			selectedElement = -1;
		}
	}

	private void iterateForward() {
		selectedElement++;
		if (selectedElement >= structures.size()) {
			selectedElement = 0;
		}
	}
	
	private void iterateBackward() {
		selectedElement--;
		if (selectedElement < 0) {
			selectedElement = structures.size() - 1;
		}
	}
	
	private Submode submodeFromStructure(Structure structure) {
		switch (structure.getType()) {
			case CAPITOL:
				return Submode.CAPITOL;
			case FARM:
				return Submode.FARM;
			case FORT:
				return Submode.FORT;
			case MINE:
				return Submode.MINE;
			case OBSERVE:
				return Submode.OBSERVE;
			case PLANT:
				return Submode.PLANT;
			case UNIVERSITY:
				return Submode.UNIVERSITY;
			default:
				return null;
		}
	}
}
