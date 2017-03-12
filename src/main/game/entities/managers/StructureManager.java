package game.entities.managers;

import entityResearch.iEntityResearchObservable;
import entityResearch.iEntityResearchObserver;
import game.entities.EntityId;
import game.entities.EntitySubtypeEnum;
import game.entities.factories.StructureFactory;
import game.entities.factories.exceptions.StructureTypeDoesNotExist;
import game.entities.factories.exceptions.StructureTypeLimitExceededException;
import game.entities.factories.exceptions.TotalStructureLimitExceededException;
import game.entities.managers.IdManager.IdManager;
import game.entities.managers.exceptions.StructureDoesNotExistException;
import game.entities.structures.*;
import game.entities.structures.exceptions.StructureNotFoundException;
import game.entities.workers.workerTypes.Worker;
import game.gameboard.Location;
import game.semantics.Percentage;

import java.util.ArrayList;

public class StructureManager implements iEntityResearchObservable {

    private ArrayList<Capitol> capitols;
    private ArrayList<Farm> farms;
    private ArrayList<Fort> forts;
    private ArrayList<Mine> mines;
    private ArrayList<ObservationTower> observationTowers;
    private ArrayList<PowerPlant> powerPlants;
    private ArrayList<University> universities;

    private ArrayList<iEntityResearchObserver> observers;

    private StructureIdManager structureIdManager;

    public StructureManager(int playerId) {
        this.capitols = new ArrayList<>();
        this.farms = new ArrayList<>();
        this.forts = new ArrayList<>();
        this.mines = new ArrayList<>();
        this.observationTowers = new ArrayList<>();
        this.powerPlants = new ArrayList<>();
        this.universities = new ArrayList<>();
        this.observers = new ArrayList<>();

        StructureFactory structureFactory = new StructureFactory(playerId);
        this.attach(structureFactory);
        this.structureIdManager = new StructureIdManager(structureFactory);
    }

    public ArrayList<Capitol> getCapitols() {
        return this.capitols;
    }

    public ArrayList<Farm> getFarms() {
        return this.farms;
    }

    public ArrayList<Fort> getForts() {
        return this.forts;
    }

    public ArrayList<Mine> getMines() {
        return this.mines;
    }

    public ArrayList<ObservationTower> getObservationTowers() {
        return this.observationTowers;
    }

    public ArrayList<PowerPlant> getPowerPlants() {
        return this.powerPlants;
    }

    public ArrayList<University> getUniversities() {
        return this.universities;
    }

    public ArrayList<Structure> getTotalStructures() {
        ArrayList<Structure> allStructures = new ArrayList<>();
        allStructures.addAll(getCapitols());
        allStructures.addAll(getFarms());
        allStructures.addAll(getForts());
        allStructures.addAll(getMines());
        allStructures.addAll(getObservationTowers());
        allStructures.addAll(getPowerPlants());
        allStructures.addAll(getUniversities());
        return allStructures;
    }
    
    public void addStructure(EntitySubtypeEnum structureType, Location location) throws StructureTypeLimitExceededException, TotalStructureLimitExceededException, StructureTypeDoesNotExist {
        switch (structureType) {
            case CAPITOL:
                this.capitols.add(this.structureIdManager.createCapitol(location));
                break;
            case FARM:
                this.farms.add(this.structureIdManager.createFarm(location));
                break;
            case FORT:
                this.forts.add(this.structureIdManager.createFort(location));
                break;
            case MINE:
                this.mines.add(this.structureIdManager.createMine(location));
                break;
            case OBSERVE:
                this.observationTowers.add(this.structureIdManager.createObservationTower(location));
                break;
            case PLANT:
                this.powerPlants.add(this.structureIdManager.createPowerPlant(location));
                break;
            case UNIVERSITY:
                this.universities.add(this.structureIdManager.createUniversity(location));
                break;
            default:
                throw new StructureTypeDoesNotExist();
        }
    }

    public void removeStructure(EntitySubtypeEnum structureType, EntityId entityId) throws StructureDoesNotExistException, StructureTypeDoesNotExist {
        switch (structureType) {
            case CAPITOL:
                removeEntityFromList(entityId, this.capitols);
                this.structureIdManager.removeCapitol(entityId);
                break;
            case FARM:
                removeEntityFromList(entityId, this.farms);
                this.structureIdManager.removeFarm(entityId);
                break;
            case FORT:
                removeEntityFromList(entityId, this.forts);
                this.structureIdManager.removeFort(entityId);
                break;
            case MINE:
                removeEntityFromList(entityId, this.mines);
                this.structureIdManager.removeMine(entityId);
                break;
            case OBSERVE:
                removeEntityFromList(entityId, this.observationTowers);
                this.structureIdManager.removeObservationTower(entityId);
                break;
            case PLANT:
                removeEntityFromList(entityId, this.powerPlants);
                this.structureIdManager.removePowerPlant(entityId);
                break;
            case UNIVERSITY:
                removeEntityFromList(entityId, this.universities);
                this.structureIdManager.removeUniversity(entityId);
                break;
            default:
                throw new StructureTypeDoesNotExist();
        }
    }

    public Structure getStructureById(EntityId entityId) throws StructureDoesNotExistException {
        ArrayList<Structure> allStructures = new ArrayList<>();
        allStructures.addAll(this.capitols);
        allStructures.addAll(this.farms);
        allStructures.addAll(this.forts);
        allStructures.addAll(this.mines);
        allStructures.addAll(this.observationTowers);
        allStructures.addAll(this.powerPlants);
        allStructures.addAll(this.universities);

        for (Structure s : allStructures) {
            if (s.getEntityId() == entityId) {
                return s;
            }
        }

        throw new StructureDoesNotExistException();
    }

    private void removeEntityFromList(EntityId entityId, ArrayList<? extends Structure> structures) throws StructureDoesNotExistException {
        boolean removed = false;

        for (Structure s : structures) {
            if (s.getEntityId() == entityId) {
                structures.remove(s);
                removed = true;
                break;
            }
        }

        if (!removed) throw new StructureDoesNotExistException("Could not find structure with entityId " + entityId);
    }

    public void attach(iEntityResearchObserver observer) {
        this.observers.add(observer);
    }

    public void increaseVisibilityRadius(EntitySubtypeEnum subtype, int increaseAmount) throws StructureTypeDoesNotExist {
        switch (subtype) {
            case CAPITOL:
                this.upgradeVisbilityRadius(this.capitols, increaseAmount);
                break;
            case FARM:
                this.upgradeVisbilityRadius(this.farms, increaseAmount);
                break;
            case FORT:
                this.upgradeVisbilityRadius(this.forts, increaseAmount);
                break;
            case MINE:
                this.upgradeVisbilityRadius(this.mines, increaseAmount);
                break;
            case OBSERVE:
                this.upgradeVisbilityRadius(this.observationTowers, increaseAmount);
                break;
            case PLANT:
                this.upgradeVisbilityRadius(this.powerPlants, increaseAmount);
                break;
            case UNIVERSITY:
                this.upgradeVisbilityRadius(this.universities, increaseAmount);
                break;
            case default:
                throw new StructureType
        }
    }

    private void upgradeVisbilityRadius(ArrayList<? extends Structure> structures, int increaseAmount) {
        for (Structure s : structures) {
            s.increaseVisibilityRadius(increaseAmount);
        }
    }

    public void increaseAttackStrength(int increaseAmount);
    public void increaseDefenseStrength(int increaseAmount);
    public void increaseArmorStrength(int increaseAmount);
    public void increaseMovementRate(int increaseAmount);
    public void increaseHealth(int increaseAmount);
    public void increaseEfficiency(Percentage increasePercentage);


}
