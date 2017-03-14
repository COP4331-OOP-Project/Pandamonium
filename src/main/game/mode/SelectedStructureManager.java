package game.mode;

import java.util.ArrayList;
import java.util.ListIterator;

import game.GameModel;
import game.Player;
import game.entities.EntityId;
import game.entities.structures.Structure;
import game.entities.units.Unit;
import game.gameboard.Location;

public class SelectedStructureManager {
	private GameModel gameModel;
	private ModeController controlMode;
	private Structure selectedStructure;
	private Location selectedLocation;
	private Player currentPlayer;
	private ArrayList<Structure> structures;
	private ListIterator<Structure> structureIterator;
	
	public SelectedStructureManager(GameModel gameModel, ModeController controlMode) {
		this.gameModel = gameModel;
		this.controlMode = controlMode;
	}
	
	public void updatePlayer() {
		currentPlayer = gameModel.getCurrentPlayer();
		structures = currentPlayer.getStructures();
		if (structures.size() > 0) {
			structureIterator = structures.listIterator();
		} else {
			structureIterator = null;
			selectedStructure = null;
			selectedLocation = null;
		}
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

	public void cycleForward() {
		for (int i = 0; i < structures.size() + 1; i++) {
			if (structureIterator.hasNext()) {
				selectedStructure = structureIterator.next();
				selectedLocation = selectedStructure.getLocation();
			} else {
				selectedStructure = structures.get(0);
				structureIterator = structures.listIterator(0);
				selectedLocation = selectedStructure.getLocation();
			}
			if (submodeFromStructure(selectedStructure) == controlMode.getGameSubmode()) {
				return;
			}
		}
		selectedStructure = null;
		selectedLocation = null;
	}
	
	public void cycleBackward() {
		for (int i = 0; i < structures.size() + 1; i++) {
			if (structureIterator.hasPrevious()) {
				selectedStructure = structureIterator.previous();
				selectedLocation = selectedStructure.getLocation();
			} else {
				selectedStructure = structures.get(structures.size() - 1);
				structureIterator = structures.listIterator(structures.size() - 1);
				selectedLocation = selectedStructure.getLocation();
			}
			if (submodeFromStructure(selectedStructure) == controlMode.getGameSubmode()) {
				return;
			}
		}
		selectedStructure = null;
		selectedLocation = null;
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
