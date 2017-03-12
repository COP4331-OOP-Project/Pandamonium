package game.entities.managers;

import entityResearch.iStructureResearchObservable;
import entityResearch.iStructureResearchObserver;
import game.entities.EntityId;
import game.entities.EntitySubtypeEnum;
import game.entities.factories.StructureFactory;
import game.entities.factories.exceptions.StructureTypeDoesNotExist;
import game.entities.factories.exceptions.StructureTypeLimitExceededException;
import game.entities.factories.exceptions.TotalStructureLimitExceededException;
import game.entities.managers.exceptions.StructureDoesNotExistException;
import game.entities.structures.*;
import game.gameboard.Location;
import game.semantics.Percentage;

import java.util.ArrayList;

public class StructureManager implements iStructureResearchObservable {

    private ArrayList<Capitol> capitols;
    private ArrayList<Farm> farms;
    private ArrayList<Fort> forts;
    private ArrayList<Mine> mines;
    private ArrayList<ObservationTower> observationTowers;
    private ArrayList<PowerPlant> powerPlants;
    private ArrayList<University> universities;

    private ArrayList<iStructureResearchObserver> observers;

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

    public Structure addStructure(EntitySubtypeEnum structureType, Location location) throws StructureTypeLimitExceededException, TotalStructureLimitExceededException, StructureTypeDoesNotExist {
        switch (structureType) {
            case CAPITOL: {
                Capitol c = this.structureIdManager.createCapitol(location);
                this.capitols.add(c);
                return c;
            }
            case FARM: {
                Farm f = this.structureIdManager.createFarm(location);
                this.farms.add(f);
                return f;
            }
            case FORT: {
                Fort f = this.structureIdManager.createFort(location);
                this.forts.add(f);
                return f;
            }
            case MINE: {
                Mine m = this.structureIdManager.createMine(location);
                this.mines.add(m);
                return m;
            }
            case OBSERVE: {
                ObservationTower o = this.structureIdManager.createObservationTower(location);
                this.observationTowers.add(o);
                return o;
            }
            case PLANT: {
                PowerPlant p = this.structureIdManager.createPowerPlant(location);
                this.powerPlants.add(p);
                return p;
            }
            case UNIVERSITY: {
                University u = this.structureIdManager.createUniversity(location);
                this.universities.add(u);
                return u;
            }
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

    public void attach(iStructureResearchObserver observer) {
        this.observers.add(observer);
    }

    public void increaseVisibilityRadius(EntitySubtypeEnum subtype, int increaseAmount) throws StructureTypeDoesNotExist {
        for (iStructureResearchObserver observer : this.observers) {
            observer.onVisibilityRadiusIncreased(subtype, increaseAmount);
        }
    }
    public void increaseAttackStrength(EntitySubtypeEnum subtype, int increaseAmount) throws StructureTypeDoesNotExist {
        for (iStructureResearchObserver observer : this.observers) {
            observer.onAttackStrengthIncreased(subtype, increaseAmount);
        }
    }
    public void increaseDefensiveStrength(EntitySubtypeEnum subtype, int increaseAmount) throws StructureTypeDoesNotExist {
        for (iStructureResearchObserver observer : this.observers) {
            observer.onDefenseStrengthIncreased(subtype, increaseAmount);
        }
    }
    public void increaseArmorStrength(EntitySubtypeEnum subtype, int increaseAmount) throws StructureTypeDoesNotExist {
        for (iStructureResearchObserver observer : this.observers) {
            observer.onArmorStrengthIncreased(subtype, increaseAmount);
        }
    }
    public void increaseHealth(EntitySubtypeEnum subtype, int increaseAmount) throws StructureTypeDoesNotExist {
        for (iStructureResearchObserver observer : this.observers) {
            observer.onHealthIncreased(subtype, increaseAmount);
        }
    }
    public void increaseEfficiency(EntitySubtypeEnum subtype, Percentage increasePercentage) throws StructureTypeDoesNotExist {
        for (iStructureResearchObserver observer : this.observers) {
            observer.onEfficiencyIncreased(subtype, increasePercentage);
        }
    }


}
