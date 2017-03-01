package game.entities.factories;

import game.entities.EntityId;
import game.entities.stats.StructureStats;
import game.entities.structures.*;
import game.entities.structures.exceptions.StructureNotFoundException;
import game.gameboard.Location;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by khariollivierre on 3/1/17.
 */
public class StructureFactory{
    private Map<StructureType, StructureStats> structureStatistics;

    public StructureFactory(){
        this.structureStatistics = new HashMap<>();
        try {
            this.structureStatistics.put(StructureType.CAPITOL, new StructureStats(StructureType.CAPITOL));
            this.structureStatistics.put(StructureType.FARM, new StructureStats(StructureType.FARM));
            this.structureStatistics.put(StructureType.FORT, new StructureStats(StructureType.FORT));
            this.structureStatistics.put(StructureType.MINE, new StructureStats(StructureType.MINE));
            this.structureStatistics.put(StructureType.OBSERVE, new StructureStats(StructureType.OBSERVE));
            this.structureStatistics.put(StructureType.PLANT, new StructureStats(StructureType.PLANT));
            this.structureStatistics.put(StructureType.UNIVERSITY, new StructureStats(StructureType.UNIVERSITY));
        }catch(StructureNotFoundException e){ System.out.println(e.getMessage()); }
    }

    public Structure createStructure(StructureType struct, Location location, EntityId entityId) throws StructureNotFoundException {
        switch(struct) {
            case CAPITOL: return new Capitol(structureStatistics.get(struct), location, entityId);
            case FARM: return new Farm(structureStatistics.get(struct), location, entityId);
            case FORT: return new Fort(structureStatistics.get(struct), location, entityId);
            case MINE: return new Mine(structureStatistics.get(struct), location, entityId);
            case OBSERVE: return new ObservationTower(structureStatistics.get(struct), location, entityId);
            case PLANT: return new PowerPlant(structureStatistics.get(struct), location, entityId);
            case UNIVERSITY: return new University(structureStatistics.get(struct), location, entityId);
            default: throw new StructureNotFoundException();
        }
    }
}
