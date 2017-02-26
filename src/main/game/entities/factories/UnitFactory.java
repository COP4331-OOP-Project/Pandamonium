package game.entities.factories;

import java.util.*;
import game.entities.units.*;
import game.gameboard.Location;

public class UnitFactory {
    private Map<UnitType, UnitStats> unitStatistics;

    public UnitFactory(){
        this.unitStatistics = new HashMap<UnitType, UnitStats>();
        this.unitStatistics.put(UnitType.COLONIST, new UnitStats(UnitType.COLONIST));
        this.unitStatistics.put(UnitType.EXPLORER, new UnitStats(UnitType.EXPLORER));
        this.unitStatistics.put(UnitType.MELEE, new UnitStats(UnitType.MELEE));
        this.unitStatistics.put(UnitType.RANGED, new UnitStats(UnitType.RANGED));
    }

    public Unit createUnit(UnitType unit, Location location, int ownerID){
        switch(unit) {
            case COLONIST:
                return new Colonist(unitStatistics.get(unit), location, ownerID);
            case EXPLORER:
                return new Explorer(unitStatistics.get(unit), location, ownerID);
            case MELEE:
                return new Melee(unitStatistics.get(unit), location, ownerID);
            case RANGED:
                return new Ranged(unitStatistics.get(unit), location, ownerID);
        }
        return null;
    }
}
