package game.entities.factories;

import game.entities.EntityId;
import game.entities.structures.*;
import game.entities.units.UnitNotFoundException;
import game.gameboard.Location;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by khariollivierre on 3/1/17.
 */
public class StructureFactory {
    private Map<StructureType, StructureStats> structureStatistics;

    public UnitFactory(){
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

    /*
        TODO: Fix structure constructors
        TODO: Finish createStructure for structures
        TODO: Finish StructureFactory, make sure there is no carryover from UnitStructure
        TODO: More
     */

    public Structure createStructure(StructureType struct, Location location, EntityId entityId) throws StructureNotFoundException {
        switch(struct) {
            case CAPITOL: return new Capitol(structureStatistics.get(struct), location, entityId);
            case EXPLORER: return new Explorer(structureStatistics.get(unit), location, entityId);
            case MELEE: return new Melee(structureStatistics.get(unit), location, entityId);
            case RANGED: return new Ranged(structureStatistics.get(unit), location, entityId);
            default: throw new StructureNotFoundException();
        }
    }
}
