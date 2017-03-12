package game.entities.factories;

import java.util.HashMap;
import java.util.Map;

import game.entities.EntityId;
import game.entities.EntitySubtypeEnum;
import game.entities.EntityTypeEnum;
import game.entities.managers.IdManager.IdManager;
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
                entityId = new EntityId(playerId, EntityTypeEnum.STRUCTURE, EntitySubtypeEnum.MINE, id);
                return new Mine(structureStatistics.get(EntitySubtypeEnum.MINE), location, entityId);
            }
            case OBSERVE: {
                entityId = new EntityId(playerId, EntityTypeEnum.STRUCTURE, EntitySubtypeEnum.OBSERVE, id);
                return new ObservationTower(structureStatistics.get(EntitySubtypeEnum.OBSERVE), location, entityId);
            }
            case PLANT: {
                entityId = new EntityId(playerId, EntityTypeEnum.STRUCTURE, EntitySubtypeEnum.PLANT, id);
                return new PowerPlant(structureStatistics.get(EntitySubtypeEnum.PLANT), location, entityId);
            }
            case UNIVERSITY: {
                entityId = new EntityId(playerId, EntityTypeEnum.STRUCTURE, EntitySubtypeEnum.UNIVERSITY, id);
                return new Fort(structureStatistics.get(EntitySubtypeEnum.UNIVERSITY), location, entityId);
            }
            default:
                throw new StructureTypeDoesNotExist();
        }
    }

}
