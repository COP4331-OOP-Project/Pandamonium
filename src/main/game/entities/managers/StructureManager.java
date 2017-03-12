package game.entities.managers;

import game.entities.EntityId;
import game.entities.EntitySubtypeEnum;
import game.entities.factories.exceptions.StructureTypeDoesNotExist;
import game.entities.factories.exceptions.StructureTypeLimitExceededException;
import game.entities.factories.exceptions.TotalStructureLimitExceededException;
import game.entities.managers.IdManager.IdManager;
import game.entities.managers.exceptions.StructureDoesNotExistException;
import game.entities.structures.*;
import game.entities.structures.exceptions.StructureNotFoundException;
import game.entities.workers.workerTypes.Worker;
import game.gameboard.Location;

import java.util.ArrayList;

public class StructureManager {

    private ArrayList<Capitol> capitols;
    private ArrayList<Farm> farms;
    private ArrayList<Fort> forts;
    private ArrayList<Mine> mines;
    private ArrayList<ObservationTower> observationTowers;
    private ArrayList<PowerPlant> powerPlants;
    private ArrayList<University> universities;

    private StructureIdManager structureIdManager;

    public StructureManager(int playerId) {
        this.structureIdManager = new StructureIdManager(playerId);
        this.capitols = new ArrayList<>();
        this.farms = new ArrayList<>();
        this.forts = new ArrayList<>();
        this.mines = new ArrayList<>();
        this.observationTowers = new ArrayList<>();
        this.powerPlants = new ArrayList<>();
        this.universities = new ArrayList<>();
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
}
