package game.entities.factories;

import java.util.HashMap;
import java.util.Map;

import game.entities.EntitySubtypeEnum;
import game.entities.EntityTypeEnum;
import game.entities.IdManager.IdManager;
import game.entities.IdManager.exceptions.IdLimitExceededException;
import game.entities.units.Colonist;
import game.entities.units.Explorer;
import game.entities.units.Melee;
import game.entities.units.Ranged;
import game.entities.units.Unit;
import game.entities.units.UnitStats;
import game.entities.units.UnitType;
import game.gameboard.Location;
import game.entities.EntityId;

public class UnitFactory {
    private Map<UnitType, UnitStats> unitStatistics;

    private EntityId entityId;
    private IdManager colonistIdManager;
    private IdManager meleeIdManager;
    private IdManager rangedIdManager;
    private IdManager explorerIdManager;

    private int newColonistId;
    private int newMeleeId;
    private  int newRangedId;
    private int newExplorerId;

    public UnitFactory(){
        this.unitStatistics = new HashMap<UnitType, UnitStats>();
        this.unitStatistics.put(UnitType.COLONIST, new UnitStats(UnitType.COLONIST));
        this.unitStatistics.put(UnitType.EXPLORER, new UnitStats(UnitType.EXPLORER));
        this.unitStatistics.put(UnitType.MELEE, new UnitStats(UnitType.MELEE));
        this.unitStatistics.put(UnitType.RANGED, new UnitStats(UnitType.RANGED));
        colonistIdManager = new IdManager(0, 9);
        meleeIdManager = new IdManager(0, 9);
        rangedIdManager = new IdManager(0, 9);
        explorerIdManager = new IdManager(0, 9);
    }

    public Unit createUnit(UnitType unit, Location location, int playerId){
        switch(unit) {
            case COLONIST:{
                try{
                    newColonistId = this.colonistIdManager.getNewId();
                    entityId = new EntityId(playerId, EntityTypeEnum.UNIT, EntitySubtypeEnum.COLONIST, newColonistId );
                    return new Colonist(unitStatistics.get(unit), location, entityId);
                } catch (IdLimitExceededException e) {}
            }
            case EXPLORER:{
                try{
                    newExplorerId = this.explorerIdManager.getNewId();
                    entityId = new EntityId(playerId, EntityTypeEnum.UNIT, EntitySubtypeEnum.EXPLORER, newExplorerId);
                    return new Explorer(unitStatistics.get(unit),location, entityId);
                } catch (IdLimitExceededException e){}
            }
            case MELEE:
                try{
                    newMeleeId = this.meleeIdManager.getNewId();
                    entityId = new EntityId(playerId, EntityTypeEnum.UNIT, EntitySubtypeEnum.MELEE, newMeleeId);
                    return new Melee(unitStatistics.get(unit), location, entityId);
                } catch (IdLimitExceededException e){}
            case RANGED:{
                try{
                    newRangedId = this.rangedIdManager.getNewId();
                    entityId = new EntityId(playerId, EntityTypeEnum.UNIT, EntitySubtypeEnum.RANGE, newRangedId);
                    return new Ranged(unitStatistics.get(unit), location, entityId);
                } catch (IdLimitExceededException e){}
            }

        }
        return null;
    }
}
