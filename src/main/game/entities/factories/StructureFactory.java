package game.entities.factories;

import java.util.HashMap;
import java.util.Map;

import entityResearch.iStructureResearchObserver;
import game.entities.EntityId;
import game.entities.EntitySubtypeEnum;
import game.entities.EntityTypeEnum;
import game.entities.factories.exceptions.*;
import game.entities.stats.StructureStats;
import game.entities.structures.*;
import game.entities.structures.exceptions.StructureNotFoundException;
import game.gameboard.Location;
import game.semantics.Percentage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class StructureFactory implements iStructureResearchObserver {

    private final static Logger log = LogManager.getLogger(StructureFactory.class);

    private int playerId;
    private Map<EntitySubtypeEnum, StructureStats> structureStatistics;

    public StructureFactory(int playerId) {
        this.playerId = playerId;
        this.structureStatistics = new HashMap<>();

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

    public Structure createStructure(EntitySubtypeEnum structureType, int id, int globalId, Location location)
            throws  StructureTypeDoesNotExist {
        switch (structureType) {
            case CAPITOL: {
                EntityId entityId = new EntityId(playerId, EntityTypeEnum.STRUCTURE, EntitySubtypeEnum.CAPITOL, id, globalId);
                return new Capitol(structureStatistics.get(EntitySubtypeEnum.CAPITOL), location, entityId);
            }
            case FARM: {
                EntityId entityId = new EntityId(playerId, EntityTypeEnum.STRUCTURE, EntitySubtypeEnum.FARM, id, globalId);
                return new Farm(structureStatistics.get(EntitySubtypeEnum.FARM), location, entityId);
            }
            case FORT: {
                EntityId entityId = new EntityId(playerId, EntityTypeEnum.STRUCTURE, EntitySubtypeEnum.FORT, id, globalId);
                return new Fort(structureStatistics.get(EntitySubtypeEnum.FORT), location, entityId);
            }
            case MINE: {
                EntityId entityId = new EntityId(playerId, EntityTypeEnum.STRUCTURE, EntitySubtypeEnum.MINE, id, globalId);
                return new Mine(structureStatistics.get(EntitySubtypeEnum.MINE), location, entityId);
            }
            case OBSERVE: {
                EntityId entityId = new EntityId(playerId, EntityTypeEnum.STRUCTURE, EntitySubtypeEnum.OBSERVE, id, globalId);
                return new ObservationTower(structureStatistics.get(EntitySubtypeEnum.OBSERVE), location, entityId);
            }
            case PLANT: {
                EntityId entityId = new EntityId(playerId, EntityTypeEnum.STRUCTURE, EntitySubtypeEnum.PLANT, id, globalId);
                return new PowerPlant(structureStatistics.get(EntitySubtypeEnum.PLANT), location, entityId);
            }
            case UNIVERSITY: {
                EntityId entityId = new EntityId(playerId, EntityTypeEnum.STRUCTURE, EntitySubtypeEnum.UNIVERSITY, id, globalId);
                return new Fort(structureStatistics.get(EntitySubtypeEnum.UNIVERSITY), location, entityId);
            }
            default:
                throw new StructureTypeDoesNotExist();
        }
    }

    public void onVisibilityRadiusIncreased(EntitySubtypeEnum subtype, int increaseAmount) throws StructureTypeDoesNotExist {
        StructureStats stats = this.structureStatistics.get(subtype);
        if (stats == null) throw new StructureTypeDoesNotExist();

        stats.increaseVisibilityRadius(increaseAmount);
    }

    public void onAttackStrengthIncreased(EntitySubtypeEnum subtype, int increaseAmount) throws StructureTypeDoesNotExist {
        StructureStats stats = this.structureStatistics.get(subtype);
        if (stats == null) throw new StructureTypeDoesNotExist();

        stats.increaseAttackStrength(increaseAmount);
    }

    public void onDefenseStrengthIncreased(EntitySubtypeEnum subtype, int increaseAmount) throws StructureTypeDoesNotExist {
        StructureStats stats = this.structureStatistics.get(subtype);
        if (stats == null) throw new StructureTypeDoesNotExist();

        stats.increaseDefenseStrength(increaseAmount);
    }

    public void onArmorStrengthIncreased(EntitySubtypeEnum subtype, int increaseAmount) throws StructureTypeDoesNotExist {
        StructureStats stats = this.structureStatistics.get(subtype);
        if (stats == null) throw new StructureTypeDoesNotExist();

        stats.increaseArmorStrength(increaseAmount);
    }

    public void onHealthIncreased(EntitySubtypeEnum subtype, int increaseAmount) throws StructureTypeDoesNotExist {
        StructureStats stats = this.structureStatistics.get(subtype);
        if (stats == null) throw new StructureTypeDoesNotExist();

        stats.increaseHealth(increaseAmount);
    }

    public void onEfficiencyIncreased(EntitySubtypeEnum subtype, Percentage increasePercentage) throws StructureTypeDoesNotExist {
        StructureStats stats = this.structureStatistics.get(subtype);
        if (stats == null) throw new StructureTypeDoesNotExist();

        stats.increaseEfficiency(increasePercentage);
    }

    // Update worker density of all structures
    public void onWorkerDensityIncreased(int increaseAmount) {
        for (Object o : this.structureStatistics.entrySet()) {
            Map.Entry pair = (Map.Entry) o;
            StructureStats stats = (StructureStats) pair.getValue();
            stats.increaseWorkerDensity(increaseAmount);
        }
    }

    // Update worker radius of all structures
    public void onWorkerRadiusIncreased(int increaseAmount) {
        for (Object o : this.structureStatistics.entrySet()) {
            Map.Entry pair = (Map.Entry) o;
            StructureStats stats = (StructureStats) pair.getValue();
            stats.increaseWorkerRadius(increaseAmount);
        }
    }

}
