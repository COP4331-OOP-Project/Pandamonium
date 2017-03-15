package game.entities.managers;

import entityResearch.iUnitResearchObservable;
import entityResearch.iUnitResearchObserver;
import game.Player;
import game.entities.EntityId;
import game.entities.EntitySubtypeEnum;
import game.entities.factories.exceptions.TotalUnitLimitExceededException;
import game.entities.factories.exceptions.UnitTypeDoesNotExistException;
import game.entities.factories.exceptions.UnitTypeLimitExceededException;
import game.entities.managers.exceptions.UnitDoesNotExistException;
import game.entities.units.*;
import game.gameboard.Gameboard;
import game.gameboard.Location;
import game.iTurnObservable;
import game.iTurnObserver;
import game.resources.Resource;
import game.semantics.Percentage;

import java.util.ArrayList;

public class UnitManager implements iUnitResearchObservable, iTurnObserver, iTurnObservable {

    private ArrayList<Melee> melees;
    private ArrayList<Ranged> ranges;
    private ArrayList<Explorer> explorers;
    private ArrayList<Colonist> colonists;

    private ArrayList<iUnitResearchObserver> observers;
    private ArrayList<iTurnObserver> turnObservers;

    private boolean endOfTurnIteration = false;
    private ArrayList<iTurnObserver> observersToBeDeletedAtEndOfTurn;
    private ArrayList<iTurnObserver> observersToBeAddedAtEndOfTurn;
    private ArrayList<iUnitResearchObserver> observersToBeAddedAtEndOfTurnResearch;


    private UnitIdManager unitIdManager;

    // Constructor
    public UnitManager(Player player, PlacementManager placementManager) {
        this.unitIdManager = new UnitIdManager(player, placementManager);   // Initialize unit id manager
        // Setup unit type collections
        this.melees = new ArrayList<>();
        this.ranges = new ArrayList<>();
        this.explorers = new ArrayList<>();
        this.colonists = new ArrayList<>();
        this.observers = new ArrayList<>();
        this.turnObservers = new ArrayList<>();
        this.observersToBeDeletedAtEndOfTurn = new ArrayList<>();
        this.observersToBeAddedAtEndOfTurn = new ArrayList<>();
    }

    // Add unit based on type at designated location
    public Unit addUnit(EntitySubtypeEnum unitType, Location location)
            throws UnitTypeLimitExceededException, TotalUnitLimitExceededException, UnitTypeDoesNotExistException {

        switch (unitType) {
            case COLONIST: {
                Colonist c = this.unitIdManager.createColonist(location);
                this.colonists.add(c);
                if (endOfTurnIteration){
                    this.observersToBeAddedAtEndOfTurn.add(c);
                    this.observersToBeAddedAtEndOfTurnResearch.add(c);
                }
                else {
                    this.attach((iUnitResearchObserver) c);
                    this.attach((iTurnObserver) c);
                }
                return c;
            }
            case EXPLORER: {
                Explorer e = this.unitIdManager.createExplorer(location);
                this.explorers.add(e);
                if (endOfTurnIteration){
                    this.observersToBeAddedAtEndOfTurn.add(e);
                    this.observersToBeAddedAtEndOfTurnResearch.add(e);
                }
                else {
                    this.attach((iUnitResearchObserver) e);
                    this.attach((iTurnObserver) e);
                }
                return e;
            }
            case MELEE: {
                Melee m = this.unitIdManager.createMelee(location);
                this.melees.add(m);
                if (endOfTurnIteration){
                    this.observersToBeAddedAtEndOfTurn.add(m);
                    this.observersToBeAddedAtEndOfTurnResearch.add(m);
                }
                else {
                    this.attach((iUnitResearchObserver) m);
                    this.attach((iTurnObserver) m);
                }
                return m;
            }
            case RANGE: {
                Ranged r = this.unitIdManager.createRanged(location);
                this.ranges.add(r);
                if (endOfTurnIteration){
                    this.observersToBeAddedAtEndOfTurn.add(r);
                    this.observersToBeAddedAtEndOfTurnResearch.add(r);
                }
                else {
                    this.attach((iUnitResearchObserver) r);
                    this.attach((iTurnObserver) r);
                }
                return r;
            }
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
                this.observersToBeDeletedAtEndOfTurn.add(u);
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

    // Attach new unit observer
    public void attach(iUnitResearchObserver observer) {
        this.observers.add(observer);
    }

    // Update to increase unit subtype visibility range
    public void increaseVisibilityRadius(EntitySubtypeEnum subtype, int increaseAmount) throws UnitTypeDoesNotExistException {
        for (iUnitResearchObserver observer : this.observers) {
            observer.onVisibilityRadiusIncreased(subtype, increaseAmount);
        }
    }

    // // Update to increase unit subtype attack strength
    public void increaseAttackStrength(EntitySubtypeEnum subtype, int increaseAmount) throws UnitTypeDoesNotExistException {
        for (iUnitResearchObserver observer : this.observers) {
            observer.onAttackStrengthIncreased(subtype, increaseAmount);
            if (observer.getType() == subtype) return;
        }
    }

    // Update to increase unit subtype defensive strength
    public void increaseDefensiveStrength(EntitySubtypeEnum subtype, int increaseAmount) throws UnitTypeDoesNotExistException {
        for (iUnitResearchObserver observer : this.observers) {
            observer.onDefenseStrengthIncreased(subtype, increaseAmount);
        }
    }

    // Update to increase unit subtype armor
    public void increaseArmorStrength(EntitySubtypeEnum subtype, int increaseAmount) throws UnitTypeDoesNotExistException {
        for (iUnitResearchObserver observer : this.observers) {
            observer.onArmorStrengthIncreased(subtype, increaseAmount);
        }
    }

    // Update to increase unit subtype health value
    public void increaseHealth(EntitySubtypeEnum subtype, int increaseAmount) throws UnitTypeDoesNotExistException {
        for (iUnitResearchObserver observer : this.observers) {
            observer.onHealthIncreased(subtype, increaseAmount);
        }
    }

    // Update to increase unit subtype efficiency
    public void increaseEfficiency(EntitySubtypeEnum subtype, Percentage increasePercentage) throws UnitTypeDoesNotExistException {
        for (iUnitResearchObserver observer : this.observers) {
            observer.onEfficiencyIncreased(subtype, increasePercentage);
        }
    }

    // Update to increase unit subtype movement range
    public void increaseMovementRange(EntitySubtypeEnum subtype, int increaseAmount) throws UnitTypeDoesNotExistException {
        for (iUnitResearchObserver observer : this.observers) {
            observer.onMovementRangeIncreased(subtype, increaseAmount);
        }
    }

    public void attach(iTurnObserver observer) {
        this.turnObservers.add(observer);
    }

    public void onTurnEnded() {
        this.endTurn();
    }

    public void endTurn() {
        this.endOfTurnIteration = true;
        for (iTurnObserver observer : this.turnObservers) {
            observer.onTurnEnded();
        }
        for (iTurnObserver observer : this.observersToBeDeletedAtEndOfTurn) {
            this.turnObservers.remove(observer);
        }
        for (iTurnObserver observer : this.observersToBeAddedAtEndOfTurn) {
            this.turnObservers.add(observer);
        }
        /*
        for (iUnitResearchObserver observer : this.observersToBeAddedAtEndOfTurnResearch) {
            this.observers.add(observer);
        }
        */


        this.observersToBeDeletedAtEndOfTurn.clear();

        this.endOfTurnIteration = false;
    }

    public void upkeepHandling(Resource nutrients){
        for (Melee m : this.melees){
            m.upkeepHandling(nutrients);
        }
        for (Ranged r : this.ranges){
            r.upkeepHandling(nutrients);
        }
        for (Explorer e : this.explorers){
            e.upkeepHandling(nutrients);
        }
        for (Colonist c : this.colonists){
            c.upkeepHandling(nutrients);
        }
    }
}
