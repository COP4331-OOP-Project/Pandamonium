package game.entities.factories;

import game.entities.*;
import game.entities.units.UnitStats;
import game.entities.units.UnitType;

public class EntityFactory {
    private UnitStats colonistStats = new UnitStats(UnitType.COLONIST);
    private UnitStats explorerStats = new UnitStats(UnitType.EXPLORER);
    private UnitStats meleeStats = new UnitStats(UnitType.MELEE);
    private UnitStats rangedStats = new UnitStats(UnitType.RANGED);
}
