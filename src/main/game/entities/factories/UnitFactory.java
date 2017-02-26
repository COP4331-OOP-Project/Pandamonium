package game.entities.factories;

import java.util.HashMap;
import java.util.Map;

import game.entities.units.Colonist;
import game.entities.units.Explorer;
import game.entities.units.Melee;
import game.entities.units.Ranged;
import game.entities.units.Unit;
import game.entities.units.UnitStats;
import game.entities.units.UnitType;
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

    public Unit createUnit(UnitType unit, Location location){
        switch(unit) {
            case COLONIST:
                return new Colonist(unitStatistics.get(unit), location);
            case EXPLORER:
                return new Explorer(unitStatistics.get(unit), location);
            case MELEE:
                return new Melee(unitStatistics.get(unit), location);
            case RANGED:
                return new Ranged(unitStatistics.get(unit), location);
        }
        return null;
    }
}
