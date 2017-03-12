package game.entities.managers;

import game.entities.EntityId;
import game.entities.EntitySubtypeEnum;
import game.entities.managers.IdManager.IdManager;
import game.entities.managers.IdManager.exceptions.IdDoesNotExistException;
import game.entities.managers.IdManager.exceptions.IdLimitExceededException;
import game.entities.managers.exceptions.StructureDoesNotExistException;
import game.entities.factories.StructureFactory;
import game.entities.factories.exceptions.*;
import game.entities.structures.*;
import game.gameboard.Location;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class StructureIdManager {
    private static final Integer MAX_STRUCTURE_COUNT = 30;
    private static final Integer MAX_OF_STRUCTURE_TYPE = 10;
    private static final Integer MAX_UNIVERSITIES = 5;
    private static final Integer MAX_CAPITOLS = 1;
    private static final Integer MIN_STRUCTURE_ID = 1;
    private final static Logger log = LogManager.getLogger(StructureFactory.class);

    private StructureFactory structureFactory;
    private IdManager allStructuresIdManager;
    private IdManager capitolIdManager;
    private IdManager farmIdManager;
    private IdManager fortIdManager;
    private IdManager mineIdManager;
    private IdManager observationTowerIdManager;
    private IdManager powerPlantIdManager;
    private IdManager universityIdManager;

    StructureIdManager(StructureFactory structureFactory) {
        this.structureFactory = structureFactory;

        this.allStructuresIdManager = new IdManager(MIN_STRUCTURE_ID, MAX_STRUCTURE_COUNT);
        this.capitolIdManager = new IdManager(MIN_STRUCTURE_ID, MAX_CAPITOLS);
        this.farmIdManager = new IdManager(MIN_STRUCTURE_ID, MAX_OF_STRUCTURE_TYPE);
        this.fortIdManager = new IdManager(MIN_STRUCTURE_ID, MAX_OF_STRUCTURE_TYPE);
        this.mineIdManager = new IdManager(MIN_STRUCTURE_ID, MAX_OF_STRUCTURE_TYPE);
        this.observationTowerIdManager = new IdManager(MIN_STRUCTURE_ID, MAX_OF_STRUCTURE_TYPE);
        this.powerPlantIdManager = new IdManager(MIN_STRUCTURE_ID, MAX_OF_STRUCTURE_TYPE);
        this.universityIdManager = new IdManager(MIN_STRUCTURE_ID, MAX_UNIVERSITIES);
    }

    Capitol createCapitol(Location location)
            throws StructureTypeLimitExceededException, TotalStructureLimitExceededException {

        int newCapitolId;
        try {
            newCapitolId = this.capitolIdManager.getNewId();
        } catch (IdLimitExceededException e) {
            throw new StructureTypeLimitExceededException("Capitol limit is reached, can't add new capitol.");
        }

        try {
            this.allStructuresIdManager.getNewId();
        } catch (IdLimitExceededException e) {
            try {
                this.capitolIdManager.removeId(newCapitolId);
            } catch (IdDoesNotExistException ex) {
                log.warn("Tried to recover capitol id but it was not yet taken.");
            }
            throw new TotalStructureLimitExceededException("Can't add new capitol, total structure limit has been reached.");
        }

        try {
            return (Capitol) this.structureFactory.createStructure(EntitySubtypeEnum.CAPITOL, newCapitolId, location);
        } catch (StructureTypeDoesNotExist e) {
            throw new RuntimeException(e.getLocalizedMessage());
        }
    }

    void removeCapitol(EntityId entityId) throws StructureDoesNotExistException {
        try {
            this.capitolIdManager.removeId(entityId.getInstanceId());
        } catch (IdDoesNotExistException e) {
            throw new StructureDoesNotExistException();
        }
    }

    Farm createFarm(Location location)
            throws StructureTypeLimitExceededException, TotalStructureLimitExceededException {

        int newFarmId;
        try {
            newFarmId = this.farmIdManager.getNewId();
        } catch (IdLimitExceededException e) {
            throw new StructureTypeLimitExceededException("Farm limit is reached, can't add new farm.");
        }

        try {
            this.allStructuresIdManager.getNewId();
        } catch (IdLimitExceededException e) {
            try {
                this.farmIdManager.removeId(newFarmId);
            } catch (IdDoesNotExistException ex) {
                log.warn("Tried to recover farm id but it was not yet taken.");
            }
            throw new TotalStructureLimitExceededException("Can't add new farm, total structure limit has been reached.");
        }

        try {
            return (Farm) this.structureFactory.createStructure(EntitySubtypeEnum.FARM, newFarmId, location);
        } catch (StructureTypeDoesNotExist e) {
            throw new RuntimeException(e.getLocalizedMessage());
        }
    }

    void removeFarm(EntityId entityId) throws StructureDoesNotExistException {
        try {
            this.farmIdManager.removeId(entityId.getInstanceId());
        } catch (IdDoesNotExistException e) {
            throw new StructureDoesNotExistException();
        }
    }

    Fort createFort(Location location)
        throws StructureTypeLimitExceededException, TotalStructureLimitExceededException {

        int newFortId;
        try {
            newFortId = this.fortIdManager.getNewId();
        } catch (IdLimitExceededException e) {
            throw new StructureTypeLimitExceededException("Fort limit is reached, can't add new fort.");
        }

        try {
            this.allStructuresIdManager.getNewId();
        } catch (IdLimitExceededException e) {
            try {
                this.fortIdManager.removeId(newFortId);
            } catch (IdDoesNotExistException ex) {
                log.warn("Tried to recover fort id but it was not yet taken.");
            }
            throw new TotalStructureLimitExceededException("Can't add new fort, total structure limit has been reached.");
        }

        try {
            return (Fort) this.structureFactory.createStructure(EntitySubtypeEnum.FORT, newFortId, location);
        } catch (StructureTypeDoesNotExist e) {
            throw new RuntimeException(e.getLocalizedMessage());
        }
    }

    void removeFort(EntityId entityId) throws StructureDoesNotExistException {
        try {
            this.fortIdManager.removeId(entityId.getInstanceId());
        } catch (IdDoesNotExistException e) {
            throw new StructureDoesNotExistException();
        }
    }

    Mine createMine(Location location)
            throws StructureTypeLimitExceededException, TotalStructureLimitExceededException {

        int newMineId;
        try {
            newMineId = this.mineIdManager.getNewId();
        } catch (IdLimitExceededException e) {
            throw new StructureTypeLimitExceededException("Mine limit is reached, can't add new mine.");
        }

        try {
            this.allStructuresIdManager.getNewId();
        } catch (IdLimitExceededException e) {
            try {
                this.mineIdManager.removeId(newMineId);
            } catch (IdDoesNotExistException ex) {
                log.warn("Tried to recover mine id but it was not yet taken.");
            }
            throw new TotalStructureLimitExceededException("Can't add new mine, total structure limit has been reached.");
        }

        try {
            return (Mine) this.structureFactory.createStructure(EntitySubtypeEnum.MINE, newMineId, location);
        } catch (StructureTypeDoesNotExist e) {
            throw new RuntimeException(e.getLocalizedMessage());
        }
    }

    void removeMine(EntityId entityId) throws StructureDoesNotExistException {
        try {
            this.mineIdManager.removeId(entityId.getInstanceId());
        } catch (IdDoesNotExistException e) {
            throw new StructureDoesNotExistException();
        }
    }

    ObservationTower createObservationTower(Location location)
            throws StructureTypeLimitExceededException, TotalStructureLimitExceededException {


        int newObservationTowerId;
        try {
            newObservationTowerId = this.observationTowerIdManager.getNewId();
        } catch (IdLimitExceededException e) {
            throw new StructureTypeLimitExceededException("Observation tower limit is reached, can't add new observation tower.");
        }

        try {
            this.allStructuresIdManager.getNewId();
        } catch (IdLimitExceededException e) {
            try {
                this.observationTowerIdManager.removeId(newObservationTowerId);
            } catch (IdDoesNotExistException ex) {
                log.warn("Tried to recover observation tower id but it was not yet taken.");
            }
            throw new TotalStructureLimitExceededException("Can't add new observation tower, total structure limit has been reached.");
        }

        try {
            return (ObservationTower) this.structureFactory.createStructure(EntitySubtypeEnum.OBSERVE, newObservationTowerId, location);
        } catch (StructureTypeDoesNotExist e) {
            throw new RuntimeException(e.getLocalizedMessage());
        }
    }

    void removeObservationTower(EntityId entityId) throws StructureDoesNotExistException {
        try {
            this.observationTowerIdManager.removeId(entityId.getInstanceId());
        } catch (IdDoesNotExistException e) {
            throw new StructureDoesNotExistException();
        }
    }

    PowerPlant createPowerPlant(Location location)
            throws StructureTypeLimitExceededException, TotalStructureLimitExceededException {

        int newPlantId;
        try {
            newPlantId = this.powerPlantIdManager.getNewId();
        } catch (IdLimitExceededException e) {
            throw new StructureTypeLimitExceededException("Power plant limit is reached, can't add new power plant.");
        }

        try {
            this.allStructuresIdManager.getNewId();
        } catch (IdLimitExceededException e) {
            try {
                this.powerPlantIdManager.removeId(newPlantId);
            } catch (IdDoesNotExistException ex) {
                log.warn("Tried to recover power plant id but it was not yet taken.");
            }
            throw new TotalStructureLimitExceededException("Can't add new power plant, total structure limit has been reached.");
        }

        try {
            return (PowerPlant) this.structureFactory.createStructure(EntitySubtypeEnum.PLANT, newPlantId, location);
        } catch (StructureTypeDoesNotExist e) {
            throw new RuntimeException(e.getLocalizedMessage());
        }
    }

    void removePowerPlant(EntityId entityId) throws StructureDoesNotExistException {
        try {
            this.powerPlantIdManager.removeId(entityId.getInstanceId());
        } catch (IdDoesNotExistException e) {
            throw new StructureDoesNotExistException();
        }
    }

    University createUniversity(Location location)
            throws StructureTypeLimitExceededException, TotalStructureLimitExceededException {

        int newUniversityId;
        try {
            newUniversityId = this.universityIdManager.getNewId();
        } catch (IdLimitExceededException e) {
            throw new StructureTypeLimitExceededException("University limit is reached, can't add new university.");
        }

        try {
            this.allStructuresIdManager.getNewId();
        } catch (IdLimitExceededException e) {
            try {
                this.powerPlantIdManager.removeId(newUniversityId);
            } catch (IdDoesNotExistException ex) {
                log.warn("Tried to recover university id but it was not yet taken.");
            }
            throw new TotalStructureLimitExceededException("Can't add new university, total structure limit has been reached.");
        }

        try {
            return (University) this.structureFactory.createStructure(EntitySubtypeEnum.UNIVERSITY, newUniversityId, location);
        } catch (StructureTypeDoesNotExist e) {
            throw new RuntimeException(e.getLocalizedMessage());
        }
    }

    void removeUniversity(EntityId entityId) throws StructureDoesNotExistException {
        try {
            this.universityIdManager.removeId(entityId.getInstanceId());
        } catch (IdDoesNotExistException e) {
            throw new StructureDoesNotExistException();
        }
    }
}
