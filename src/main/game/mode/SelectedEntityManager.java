package game.mode;

import game.GameModel;
import game.entities.EntityId;
import game.gameboard.Location;

public class SelectedEntityManager {
	private SelectedArmyManager armyManager;
	private SelectedRallyPointManager rallyManager;
	private SelectedStructureManager structureManager;
	private SelectedUnitManager unitManager;
	private EntityId selectedEntityId;
	private Location selectedLocation;
	private ModeController controlMode;

	public SelectedEntityManager(GameModel gameModel, ModeController controlMode) {
		this.controlMode = controlMode;
		rallyManager = new SelectedRallyPointManager(gameModel, controlMode);
		structureManager = new SelectedStructureManager(gameModel, controlMode);
		unitManager = new SelectedUnitManager(gameModel, controlMode);
		armyManager = new SelectedArmyManager(gameModel, controlMode);
	}

	public void newPlayer() {
		selectedEntityId = null;
		selectedLocation = null;
		rallyManager.updatePlayer();
		structureManager.updatePlayer();
		unitManager.updatePlayer();
		armyManager.updatePlayer();
	}

	public void setSelectedEntity(EntityId selectedEntityId) {
		this.selectedEntityId = selectedEntityId;
	}

	public EntityId getSelectedEntity() {
		return selectedEntityId;
	}

	public Location getSelectedLocation() {
		return selectedLocation;
	}

	public void cycle(boolean forward) {
		switch (controlMode.getGameMode()) {
			case RALLY_POINT :
				rallyManager.cycle(forward);
				if (rallyManager.getSelected() != null) {
					selectedEntityId = rallyManager.getSelected();
					selectedLocation = rallyManager.getLocation();
				} else {
					selectedEntityId = null;
					selectedLocation = null;
				}
				break;
			case STRUCTURE :
				structureManager.cycle(forward);
				if (structureManager.getSelected() != null) {
					selectedEntityId = structureManager.getSelected();
					selectedLocation = structureManager.getLocation();
				} else {
					selectedEntityId = null;
					selectedLocation = null;
				}
				break;
			case UNIT :
				unitManager.cycle(forward);
				if (unitManager.getSelected() != null) {
					selectedEntityId = unitManager.getSelected();
					selectedLocation = unitManager.getLocation();
				} else {
					selectedEntityId = null;
					selectedLocation = null;
				}
				break;
			case ARMY :
				armyManager.cycle(forward);
				if (armyManager.getSelected() != null) {
					selectedEntityId = armyManager.getSelected();
					selectedLocation = armyManager.getLocation();
				} else {
					selectedEntityId = null;
					selectedLocation = null;
				}
				break;
			default :
		}
	}
}
