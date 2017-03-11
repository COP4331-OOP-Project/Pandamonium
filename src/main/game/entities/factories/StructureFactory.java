package game.entities.factories;

import java.util.HashMap;
import java.util.Map;

import game.entities.EntityId;
import game.entities.EntitySubtypeEnum;
import game.entities.EntityTypeEnum;
import game.entities.Managers.IdManager.IdManager;
import game.entities.Managers.IdManager.exceptions.IdDoesNotExistException;
import game.entities.Managers.IdManager.exceptions.IdLimitExceededException;
import game.entities.factories.exceptions.*;
import game.entities.stats.StructureStats;
import game.entities.structures.*;
import game.entities.structures.exceptions.StructureNotFoundException;
import game.gameboard.Location;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class StructureFactory {

    private static final Integer MAX_STRUCTURE_COUNT = 30;
    private static final Integer MAX_OF_STRUCTURE_TYPE = 10;
    private static final Integer MAX_UNIVERSITIES = 5;
    private static final Integer MAX_CAPITOLS = 1;
    private static final Integer MIN_STRUCTURE_ID = 1;
    private final static Logger log = LogManager.getLogger(StructureFactory.class);


    private int playerId;
    private Map<EntitySubtypeEnum, StructureStats> structureStatistics;
    private EntityId entityId;
    private IdManager totalStructureCountManager;
    private IdManager capitolManager;
    private IdManager farmManager;
    private IdManager fortManager;
    private IdManager mineManager;
    private IdManager observeManager;
    private IdManager plantManager;
    private IdManager univManager;


    public StructureFactory(int playerId) {
        this.playerId = playerId;
        this.structureStatistics = new HashMap<>();
        this.totalStructureCountManager = new IdManager(1, MAX_STRUCTURE_COUNT);
        this.capitolManager = new IdManager(MIN_STRUCTURE_ID, MAX_CAPITOLS);
        this.farmManager = new IdManager(MIN_STRUCTURE_ID, MAX_OF_STRUCTURE_TYPE);
        this.fortManager = new IdManager(MIN_STRUCTURE_ID, MAX_OF_STRUCTURE_TYPE);
        this.mineManager = new IdManager(MIN_STRUCTURE_ID, MAX_OF_STRUCTURE_TYPE);
        this.observeManager = new IdManager(MIN_STRUCTURE_ID, MAX_OF_STRUCTURE_TYPE);
        this.plantManager = new IdManager(MIN_STRUCTURE_ID, MAX_OF_STRUCTURE_TYPE);
        this.univManager = new IdManager(MIN_STRUCTURE_ID, MAX_UNIVERSITIES);

        try {
            this.structureStatistics.put(EntitySubtypeEnum.CAPITOL, new StructureStats(EntitySubtypeEnum.CAPITOL));
            this.structureStatistics.put(EntitySubtypeEnum.FARM, new StructureStats(EntitySubtypeEnum.FARM));
            this.structureStatistics.put(EntitySubtypeEnum.FORT, new StructureStats(EntitySubtypeEnum.FORT));
            this.structureStatistics.put(EntitySubtypeEnum.MINE, new StructureStats(EntitySubtypeEnum.MINE));
            this.structureStatistics.put(EntitySubtypeEnum.OBSERVE, new StructureStats(EntitySubtypeEnum.OBSERVE));
            this.structureStatistics.put(EntitySubtypeEnum.PLANT, new StructureStats(EntitySubtypeEnum.PLANT));
            this.structureStatistics.put(EntitySubtypeEnum.UNIVERSITY, new StructureStats(EntitySubtypeEnum.UNIVERSITY));

        } catch (StructureNotFoundException e) {
            throw new RuntimeException("Structure Factory could not be instantiated: " + e.getLocalizedMessage());
        }
    }

    public Structure createStructure(EntitySubtypeEnum structureType, int id, Location location)
            throws  StructureTypeDoesNotExist {
        switch (structureType) {
            case CAPITOL: {
                entityId = new EntityId(playerId, EntityTypeEnum.STRUCTURE, EntitySubtypeEnum.CAPITOL, id);
                return new Capitol(structureStatistics.get(EntitySubtypeEnum.CAPITOL), location, entityId);
            }
            case FARM: {
                entityId = new EntityId(playerId, EntityTypeEnum.STRUCTURE, EntitySubtypeEnum.FARM, id);
                return new Farm(structureStatistics.get(EntitySubtypeEnum.FARM), location, entityId);
            }
            case FORT: {
                entityId = new EntityId(playerId, EntityTypeEnum.STRUCTURE, EntitySubtypeEnum.FORT, id);
                return new Fort(structureStatistics.get(EntitySubtypeEnum.FORT), location, entityId);
            }
            case MINE: {
                return this.getNewMine(location, playerId);
            }
            case OBSERVE: {
                return this.getNewObservationTower(location, playerId);
            }
            case PLANT: {
                return this.getNewPowerPlant(location, playerId);
            }
            case UNIVERSITY: {
                return this.getNewUniversity(location, playerId);
            }
            default:
                throw new StructureNotFoundException();
        }
    }


    private Capitol getNewCapitol(Location location, int playerId)
            throws CapitolLimitExceededException, TotalStructureLimitExceededException {

        int newCapitolId;
        try {
            newCapitolId = this.capitolManager.getNewId();
        } catch (IdLimitExceededException e) {
            throw new CapitolLimitExceededException("Capitol limit is reached, can't add new capitol.");
        }

        try {
            this.totalStructureCountManager.getNewId();
        } catch (IdLimitExceededException e) {
            try {
                this.capitolManager.removeId(newCapitolId);
            } catch (IdDoesNotExistException ex) {
                log.warn("Tried to recover capitol id but it was not yet taken.");
            }
            throw new TotalStructureLimitExceededException("Can't add new capitol, total structure limit has been reached.");
        }

        entityId = new EntityId(playerId, EntityTypeEnum.STRUCTURE, EntitySubtypeEnum.CAPITOL, newCapitolId);
        return new Capitol(structureStatistics.get(EntitySubtypeEnum.CAPITOL), location, entityId);
    }

    private Farm getNewFarm(Location location, int playerId)
            throws FarmLimitExceededException, TotalStructureLimitExceededException {

        int newFarmId;
        try {
            newFarmId = this.farmManager.getNewId();
        } catch (IdLimitExceededException e) {
            throw new FarmLimitExceededException("Farm limit is reached, can't add new farm.");
        }

        try {
            this.totalStructureCountManager.getNewId();
        } catch (IdLimitExceededException e) {
            try {
                this.farmManager.removeId(newFarmId);
            } catch (IdDoesNotExistException ex) {
                log.warn("Tried to recover farm id but it was not yet taken.");
            }
            throw new TotalStructureLimitExceededException("Can't add new farm, total structure limit has been reached.");
        }

        entityId = new EntityId(playerId, EntityTypeEnum.STRUCTURE, EntitySubtypeEnum.FARM, newFarmId);
        return new Farm(structureStatistics.get(EntitySubtypeEnum.FARM), location, entityId);
    }

    private Fort getNewFort(Location location, int playerId)
            throws FortLimitExceededException, TotalStructureLimitExceededException {

        int newFortId;
        try {
            newFortId = this.fortManager.getNewId();
        } catch (IdLimitExceededException e) {
            throw new FortLimitExceededException("Fort limit is reached, can't add new fort.");
        }

        try {
            this.totalStructureCountManager.getNewId();
        } catch (IdLimitExceededException e) {
            try {
                this.fortManager.removeId(newFortId);
            } catch (IdDoesNotExistException ex) {
                log.warn("Tried to recover fort id but it was not yet taken.");
            }
            throw new TotalStructureLimitExceededException("Can't add new fort, total structure limit has been reached.");
        }

        entityId = new EntityId(playerId, EntityTypeEnum.STRUCTURE, EntitySubtypeEnum.FORT, newFortId);
        return new Fort(structureStatistics.get(EntitySubtypeEnum.FORT), location, entityId);
    }


    private Mine getNewMine(Location location, int playerId)
            throws MineLimitExceededException, TotalStructureLimitExceededException {

        int newMineId;
        try {
            newMineId = this.mineManager.getNewId();
        } catch (IdLimitExceededException e) {
            throw new MineLimitExceededException("Mine limit is reached, can't add new mine.");
        }

        try {
            this.totalStructureCountManager.getNewId();
        } catch (IdLimitExceededException e) {
            try {
                this.mineManager.removeId(newMineId);
            } catch (IdDoesNotExistException ex) {
                log.warn("Tried to recover mine id but it was not yet taken.");
            }
            throw new TotalStructureLimitExceededException("Can't add new mine, total structure limit has been reached.");
        }

        entityId = new EntityId(playerId, EntityTypeEnum.STRUCTURE, EntitySubtypeEnum.MINE, newMineId);
        return new Mine(structureStatistics.get(EntitySubtypeEnum.MINE), location, entityId);
    }

    private ObservationTower getNewObservationTower(Location location, int playerId)
            throws ObserveLimitExceededException, TotalStructureLimitExceededException {

        int newObserveLimit;
        try {
            newObserveLimit = this.observeManager.getNewId();
        } catch (IdLimitExceededException e) {
            throw new ObserveLimitExceededException("Observation tower limit is reached, can't add new observation tower.");
        }

        try {
            this.totalStructureCountManager.getNewId();
        } catch (IdLimitExceededException e) {
            try {
                this.observeManager.removeId(newObserveLimit);
            } catch (IdDoesNotExistException ex) {
                log.warn("Tried to recover observation tower id but it was not yet taken.");
            }
            throw new TotalStructureLimitExceededException("Can't add new observation tower, total structure limit has been reached.");
        }

        entityId = new EntityId(playerId, EntityTypeEnum.STRUCTURE, EntitySubtypeEnum.OBSERVE, newObserveLimit);
        return new ObservationTower(structureStatistics.get(EntitySubtypeEnum.OBSERVE), location, entityId);
    }

    private PowerPlant getNewPowerPlant(Location location, int playerId)
            throws PlantLimitExceededException, TotalStructureLimitExceededException {

        int newPowerPlantId;
        try {
            newPowerPlantId = this.plantManager.getNewId();
        } catch (IdLimitExceededException e) {
            throw new PlantLimitExceededException("Power plant limit is reached, can't add new power plant.");
        }

        try {
            this.totalStructureCountManager.getNewId();
        } catch (IdLimitExceededException e) {
            try {
                this.plantManager.removeId(newPowerPlantId);
            } catch (IdDoesNotExistException ex) {
                log.warn("Tried to recover power plant id but it was not yet taken.");
            }
            throw new TotalStructureLimitExceededException("Can't add new power plant, total structure limit has been reached.");
        }

        entityId = new EntityId(playerId, EntityTypeEnum.STRUCTURE, EntitySubtypeEnum.PLANT, newPowerPlantId);
        return new PowerPlant(structureStatistics.get(EntitySubtypeEnum.PLANT), location, entityId);
    }

    private University getNewUniversity(Location location, int playerId)
            throws UniversityLimitExceededException, TotalStructureLimitExceededException {

        int newUniversityid;
        try {
            newUniversityid = this.univManager.getNewId();
        } catch (IdLimitExceededException e) {
            throw new UniversityLimitExceededException("University limit is reached, can't add new power plant.");
        }

        try {
            this.totalStructureCountManager.getNewId();
        } catch (IdLimitExceededException e) {
            try {
                this.univManager.removeId(newUniversityid);
            } catch (IdDoesNotExistException ex) {
                log.warn("Tried to recover university id but it was not yet taken.");
            }
            throw new TotalStructureLimitExceededException("Can't add new university, total structure limit has been reached.");
        }

        entityId = new EntityId(playerId, EntityTypeEnum.STRUCTURE, EntitySubtypeEnum.UNIVERSITY, newUniversityid);
        return new University(structureStatistics.get(EntitySubtypeEnum.UNIVERSITY), location, entityId);
    }


}
