package game.entities.managers;

import game.entities.*;
import game.entities.factories.exceptions.ArmyLimitExceededException;
import game.entities.managers.exceptions.ArmyDoesNotExistException;
import game.entities.units.Unit;
import game.gameboard.Gameboard;
import game.gameboard.Location;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;

public class ArmyManager {

    // Logger
    private final static Logger log = LogManager.getLogger(ArmyManager.class);

    private ArmyIdManager armyIdManager;    // Army Id Manager
    private ArrayList<Army> armies;         // Armies

    // Constructor
    public ArmyManager(int playerId, Gameboard gb) {

        this.armyIdManager = new ArmyIdManager(playerId, gb);   // Setup id manager
        this.armies = new ArrayList<>();                        // Setup armies list

    }

    // Create new army
    public Army addArmy(ArrayList<Unit> units, Location location)
        throws ArmyDoesNotExistException, ArmyLimitExceededException {

        // Create new army
        Army a = this.armyIdManager.createArmy(units, location);

        this.armies.add(a);     // Add to list
        return a;               // Return army
    }

    public void removeArmy(EntityId id)
        throws ArmyDoesNotExistException {

        // Find and remove specified army by id from armies list
        Army a = getArmyById(id);
        armies.remove(a);
        this.armyIdManager.removeArmy(id);      // Remove army from id manager

    }

    // Get army of specified id
    public Army getArmyById(EntityId id) throws ArmyDoesNotExistException {

        // Find army of designated id from list
        for (Army a : this.armies) {
            if (a.getEntityId() == id) {
                return a;
            }
        }

        throw new ArmyDoesNotExistException(); // No army found

    }

    // Get Location of specified army
    public Location getLocationOfArmy(EntityId id) throws ArmyDoesNotExistException {

        Army a = getArmyById(id);
        return a.getLocation();

    }

    // Get Battlegroup of specified army
    public BattleGroup getBattlegroupOfArmy(EntityId id) throws ArmyDoesNotExistException {

        Army a = getArmyById(id);
        return a.getBattleGroup();

    }

    // Get Reinforcements of specified army
    public Reinforcement getReinforcementOfArmy(EntityId id) throws ArmyDoesNotExistException {

        Army a = getArmyById(id);
        return a.getReinforcements();

    }

    // Get Rally Point of specified army
    public RallyPoint getRallyPointOfArmy(EntityId id) throws ArmyDoesNotExistException {

        Army a = getArmyById(id);
        return a.getRallyPoint();

    }

    // Get Rally Point Location of specified army
    public Location getRallyPointLocationOfArmy(EntityId id) throws ArmyDoesNotExistException {

        Army a = getArmyById(id);
        return a.getRallyPointLocation();

    }

    public ArrayList<Army> getArmies(){return this.armies;}

}
