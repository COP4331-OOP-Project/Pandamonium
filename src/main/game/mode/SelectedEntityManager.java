package game.mode;

import game.GameModel;
import game.entities.EntityId;
import game.gameboard.Location;

public class SelectedEntityManager {
	private SelectedArmyManager armyManager;
	private SelectedRallyPointManager rallyManager;
	private SelectedStructureManager structureManager;
	private SelectedUnitManager unitManager;
	private EntityId selectedEntity;
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
		selectedEntity = null;
		selectedLocation = null;
		rallyManager.updatePlayer();
		structureManager.updatePlayer();
		unitManager.updatePlayer();
		armyManager.updatePlayer();
	}

	public void setSelectedEntity(EntityId selectedEntity) {
		this.selectedEntity = selectedEntity;
	}

	public EntityId getSelectedEntity() {
		return selectedEntity;
	}

	public Location getSelectedLocation() {
		return selectedLocation;
	}

	public void cycle(boolean forward) {
		switch (controlMode.getGameMode()) {
			case RALLY_POINT :
				rallyManager.cycle(forward);
				if (rallyManager.getSelected() != null) {
					selectedEntity = rallyManager.getSelected();
					selectedLocation = rallyManager.getLocation();
				} else {
					selectedEntity = null;
					selectedLocation = null;
				}
				break;
			case STRUCTURE :
				structureManager.cycle(forward);
				if (structureManager.getSelected() != null) {
					selectedEntity = structureManager.getSelected();
					selectedLocation = structureManager.getLocation();
				} else {
					selectedEntity = null;
					selectedLocation = null;
				}
				break;
			case UNIT :
				unitManager.cycle(forward);
				if (unitManager.getSelected() != null) {
					selectedEntity = unitManager.getSelected();
					selectedLocation = unitManager.getLocation();
				} else {
					selectedEntity = null;
					selectedLocation = null;
				}
				break;
			case ARMY :
				armyManager.cycle(forward);
				if (armyManager.getSelected() != null) {
					selectedEntity = armyManager.getSelected();
					selectedLocation = armyManager.getLocation();
				} else {
					selectedEntity = null;
					selectedLocation = null;
				}
				break;
			default :
		}
	}
}
