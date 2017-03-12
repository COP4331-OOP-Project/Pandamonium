package game.entities.managers;

import game.entities.EntityId;
import game.entities.EntitySubtypeEnum;
import game.entities.factories.exceptions.TotalUnitLimitExceededException;
import game.entities.factories.exceptions.UnitTypeDoesNotExistException;
import game.entities.factories.exceptions.UnitTypeLimitExceededException;
import game.entities.managers.exceptions.UnitDoesNotExistException;
import game.entities.units.*;
import game.gameboard.Location;

import java.util.ArrayList;

public class UnitManager {

    private ArrayList<Melee> melees;
    private ArrayList<Ranged> ranges;
    private ArrayList<Explorer> explorers;
    private ArrayList<Colonist> colonists;

    private UnitIdManager unitIdManager;

    // Constructor
    public UnitManager(int playerId) {

        this.unitIdManager = new UnitIdManager(playerId);   // Initialize unit id manager

        // Setup unit type collections
        this.melees = new ArrayList<>();
        this.ranges = new ArrayList<>();
        this.explorers = new ArrayList<>();
        this.colonists = new ArrayList<>();

    }

    // Add unit based on type at designated location
    public void addUnit(EntitySubtypeEnum unitType, Location location)
            throws UnitTypeLimitExceededException, TotalUnitLimitExceededException, UnitTypeDoesNotExistException {

        switch (unitType) {
            case COLONIST:
                this.colonists.add(this.unitIdManager.createColonist(location));
                break;
            case EXPLORER:
                this.explorers.add(this.unitIdManager.createExplorer(location));
                break;
            case MELEE:
                this.melees.add(this.unitIdManager.createMelee(location));
                break;
            case RANGE:
                this.ranges.add(this.unitIdManager.createRanged(location));
                break;
            default:
                throw new UnitTypeDoesNotExistException("Unit type " + unitType + " does not exist.");

        }

    }

    // Remove unit based on type and given id
    public void removeUnit(EntitySubtypeEnum unitType, EntityId entityId)
        throws UnitDoesNotExistException, UnitTypeDoesNotExistException {

        switch (unitType) {
            case COLONIST:
                removeEntityFromList(entityId, this.colonists);
                this.unitIdManager.removeColonist(entityId);
                break;
            case EXPLORER:
                removeEntityFromList(entityId, this.explorers);
                this.unitIdManager.removeExplorer(entityId);
                break;
            case MELEE:
                removeEntityFromList(entityId, this.melees);
                this.unitIdManager.removeMelee(entityId);
                break;
            case RANGE:
                removeEntityFromList(entityId, this.ranges);
                this.unitIdManager.removeRanged(entityId);
                break;
            default:
                throw new UnitTypeDoesNotExistException("Unit type " + unitType + " does not exist.");

        }

    }

    // Return the unit instance of the passed entity id
    public Unit getUnitById(EntityId entityId) throws UnitDoesNotExistException {

        // Collect all available instances
        ArrayList<Unit> allUnits = getTotalUnits();

        // Find unit of designated id from all instances
        for (Unit u : allUnits) {
            if (u.getEntityId() == entityId) {
                return u;
            }
        }

        throw new UnitDoesNotExistException(); // No unit found

    }

    // Remove unit of designated id from players list of units
    private void removeEntityFromList(EntityId entityId, ArrayList<? extends Unit> units) throws UnitDoesNotExistException {
        boolean removed = false;

        for (Unit u : units) {
            if (u.getEntityId() == entityId) {
                units.remove(u);
                removed = true;
                break;
            }
        }

        if (!removed) throw new UnitDoesNotExistException("Could not find unit with entityId " + entityId);
    }

    // Get colonists
    public ArrayList<Colonist> getColonists() {
        return this.colonists;
    }

    // Get explorers
    public ArrayList<Explorer> getExplorers() {
        return this.explorers;
    }

    // Get melees
    public ArrayList<Melee> getMelees() {
        return this.melees;
    }

    // Get ranges
    public ArrayList<Ranged> getRanges() {
        return this.ranges;
    }

    // Get all units
    public ArrayList<Unit> getTotalUnits() {

        ArrayList<Unit> total = new ArrayList<>();

        total.addAll(this.colonists);
        total.addAll(this.explorers);
        total.addAll(this.melees);
        total.addAll(this.ranges);

        return total;

    }

    // Update unit attack strength
    public void onUnitAttackDamageChanged(EntitySubtypeEnum type, double atkStrength) {}

    // Update unit defense strength
    public void onUnitDefenseStrengthChanged(EntitySubtypeEnum type, double defStrength) {}

    // Update unit armor
    public void onUnitArmorChanged(EntitySubtypeEnum type, double armor) {}

    // Update unit movement
    public void onUnitMovementChanged(EntitySubtypeEnum type, double movement) {}

    // Update unit health
    public void onUnitHealthChanged(EntitySubtypeEnum type, double health) {}

    // Update unit upkeep efficiency
    public void onUnitUpkeepEfficiency(EntitySubtypeEnum type, double upkeep) {}

}
