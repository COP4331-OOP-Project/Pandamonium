package game.entities.managers;

import game.entities.Army;
import game.entities.EntityId;
import game.entities.factories.ArmyFactory;
import game.entities.factories.exceptions.ArmyLimitExceededException;
import game.entities.managers.IdManager.IdManager;
import game.entities.managers.IdManager.exceptions.IdDoesNotExistException;
import game.entities.managers.IdManager.exceptions.IdLimitExceededException;
import game.entities.managers.exceptions.ArmyDoesNotExistException;
import game.entities.units.Unit;
import game.gameboard.Location;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;

public class ArmyIdManager {

    // Logger
    private final static Logger log = LogManager.getLogger(ArmyIdManager.class);

    // Army creation limits (10 max at a time)
    private static final Integer MAX_ARMY_COUNT = 10;
    private static final Integer MIN_ARMY_ID = 1;

    private ArmyFactory armyFactory;    // Army Factory
    private IdManager armyIdManager;    // Id manager for army instances

    // Constructor
    public ArmyIdManager(int playerId) {

        this.armyFactory = new ArmyFactory(playerId);                       // Setup factory
        this.armyIdManager = new IdManager(MIN_ARMY_ID, MAX_ARMY_COUNT);    // Setup id manager

    }

    // Create new army using passed units and starting location
    Army createArmy(ArrayList<Unit> units, Location location) throws ArmyLimitExceededException {

        Integer newArmyId;

        try {
            newArmyId = this.armyIdManager.getNewId();
        } catch (IdLimitExceededException e) {
            throw new ArmyLimitExceededException("Army limit is reached, can't add new army!");
        }

        // Create new army
        return this.armyFactory.createArmy(units, newArmyId, location);
    }

    // Remove army of specified id
    void removeArmy(EntityId id) throws ArmyDoesNotExistException {
        try {
            this.armyIdManager.removeId(id.getInstanceId());
        } catch (IdDoesNotExistException e) {
            throw new ArmyDoesNotExistException("Army of id " + id.getInstanceId() + " does not exist!");
        }

    }


}
