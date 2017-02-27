package game.entities.factories;

import java.util.HashMap;
import java.util.Map;

import game.entities.EntityId;
import game.entities.units.*;
import game.gameboard.Location;

public class UnitFactory {
    private Map<UnitType, UnitStats> unitStatistics;

    public UnitFactory(){
        this.unitStatistics = new HashMap<>();
        try {
            this.unitStatistics.put(UnitType.COLONIST, new UnitStats(UnitType.COLONIST));
            this.unitStatistics.put(UnitType.EXPLORER, new UnitStats(UnitType.EXPLORER));
            this.unitStatistics.put(UnitType.MELEE, new UnitStats(UnitType.MELEE));
            this.unitStatistics.put(UnitType.RANGED, new UnitStats(UnitType.RANGED));
        }catch(UnitNotFoundException e){ System.out.println(e.getMessage()); }
    }

    public Unit createUnit(UnitType unit, Location location, EntityId entityId) throws UnitNotFoundException{
        switch(unit) {
            case COLONIST:
                return new Colonist(unitStatistics.get(unit), location, entityId);
            case EXPLORER:
                return new Explorer(unitStatistics.get(unit), location, entityId);
            case MELEE:
                return new Melee(unitStatistics.get(unit), location, entityId);
            case RANGED:
                return new Ranged(unitStatistics.get(unit), location, entityId);
            default:
                throw new UnitNotFoundException();
        }
    }
}
