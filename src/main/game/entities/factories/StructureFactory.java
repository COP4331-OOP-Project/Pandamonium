package game.entities.factories;

import game.entities.EntityId;
import game.entities.EntitySubtypeEnum;
import game.entities.EntityTypeEnum;
import game.entities.IdManager.IdManager;
import game.entities.IdManager.exceptions.IdLimitExceededException;
import game.entities.factories.exceptions.*;
import game.entities.stats.StructureStats;
import game.entities.structures.*;
import game.entities.structures.exceptions.StructureNotFoundException;
import game.gameboard.Location;

import java.util.HashMap;
import java.util.Map;

public class StructureFactory{
    private Map<EntitySubtypeEnum, StructureStats> structureStatistics;

    private EntityId entityId;
    private IdManager capitolManager;
    private IdManager farmManager;
    private IdManager fortManager;
    private IdManager mineManager;
    private IdManager observeManager;
    private IdManager plantManager;
    private IdManager univManager;

    private int newCapitolId;
    private int newFarmId;
    private int newFortId;
    private int newMineId;
    private int newObserveId;
    private int newPlantId;
    private int newUnivId;


    public StructureFactory(){
        this.structureStatistics = new HashMap<>();
        try {
            this.structureStatistics.put(EntitySubtypeEnum.CAPITOL, new StructureStats(StructureType.CAPITOL));
            this.structureStatistics.put(EntitySubtypeEnum.FARM, new StructureStats(StructureType.FARM));
            this.structureStatistics.put(EntitySubtypeEnum.FORT, new StructureStats(StructureType.FORT));
            this.structureStatistics.put(EntitySubtypeEnum.MINE, new StructureStats(StructureType.MINE));
            this.structureStatistics.put(EntitySubtypeEnum.OBSERVE, new StructureStats(StructureType.OBSERVE));
            this.structureStatistics.put(EntitySubtypeEnum.PLANT, new StructureStats(StructureType.PLANT));
            this.structureStatistics.put(EntitySubtypeEnum.UNIVERSITY, new StructureStats(StructureType.UNIVERSITY));
        }catch(StructureNotFoundException e){ System.out.println(e.getMessage()); }
    }

    public Structure createStructure(EntitySubtypeEnum struct, Location location, int playerId)
            throws CapitolLimitExceededException, FarmLimitExceededException, FortLimitExceededException,
                    MineLimitExceededException, ObserveLimitExceededException, PlantLimitExceededException,
                    UniversityLimitExceededException, StructureNotFoundException
    {
        switch(struct) {
            case CAPITOL:{
                try{
                    newCapitolId = this.capitolManager.getNewId();
                    entityId = new EntityId(playerId, EntityTypeEnum.STRUCTURE, EntitySubtypeEnum.CAPITOL, newCapitolId);
                    return new Capitol(structureStatistics.get(struct), location, entityId);
                } catch (IdLimitExceededException e) {
                    throw new CapitolLimitExceededException("Capitol limit is reached, can't add new capitol.");
                }
            }
            case FARM:{
                try{
                    newCapitolId = this.farmManager.getNewId();
                    entityId = new EntityId(playerId, EntityTypeEnum.STRUCTURE, EntitySubtypeEnum.FARM, newFarmId);
                    return new Farm(structureStatistics.get(struct), location, entityId);
                } catch (IdLimitExceededException e) {
                    throw new FarmLimitExceededException("Farm limit is reached, can't add new farm.");
                }
            }
            case FORT:{
                try{
                    newCapitolId = this.fortManager.getNewId();
                    entityId = new EntityId(playerId, EntityTypeEnum.STRUCTURE, EntitySubtypeEnum.FORT, newFortId);
                    return new Fort(structureStatistics.get(struct), location, entityId);
                } catch (IdLimitExceededException e) {
                    throw new FortLimitExceededException("Fort limit is reached, can't add new fort.");
                }
            }
            case MINE:{
                try{
                    newCapitolId = this.mineManager.getNewId();
                    entityId = new EntityId(playerId, EntityTypeEnum.STRUCTURE, EntitySubtypeEnum.MINE, newMineId);
                    return new Mine(structureStatistics.get(struct), location, entityId);
                } catch (IdLimitExceededException e) {
                    throw new MineLimitExceededException("Mine limit is reached, can't add new mine.");
                }
            }
            case OBSERVE:{
                try{
                    newCapitolId = this.observeManager.getNewId();
                    entityId = new EntityId(playerId, EntityTypeEnum.STRUCTURE, EntitySubtypeEnum.OBSERVE, newObserveId);
                    return new Capitol(structureStatistics.get(struct), location, entityId);
                } catch (IdLimitExceededException e) {
                    throw new ObserveLimitExceededException("Observation tower limit is reached, can't add new observation tower.");
                }
            }
            case PLANT:{
                try{
                    newCapitolId = this.plantManager.getNewId();
                    entityId = new EntityId(playerId, EntityTypeEnum.STRUCTURE, EntitySubtypeEnum.PLANT, newPlantId);
                    return new PowerPlant(structureStatistics.get(struct), location, entityId);
                } catch (IdLimitExceededException e) {
                    throw new PlantLimitExceededException("Power plant limit is reached, can't add new power plant.");
                }
            }
            case UNIVERSITY:{
                try{
                    newCapitolId = this.univManager.getNewId();
                    entityId = new EntityId(playerId, EntityTypeEnum.STRUCTURE, EntitySubtypeEnum.UNIVERSITY, newUnivId);
                    return new University(structureStatistics.get(struct), location, entityId);
                } catch (IdLimitExceededException e) {
                    throw new UniversityLimitExceededException("University limit is reached, can't add new university.");
                }
            }
            default: throw new StructureNotFoundException();
        }
    }
}
