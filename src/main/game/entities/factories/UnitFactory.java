package game.entities.factories;

import java.util.HashMap;
import java.util.Map;

import game.entities.EntitySubtypeEnum;
import game.entities.EntityTypeEnum;
import game.entities.IdManager.IdManager;
import game.entities.IdManager.exceptions.IdLimitExceededException;
import game.entities.factories.exceptions.ColonistLimitExceededException;
import game.entities.factories.exceptions.*;
import game.entities.stats.UnitStats;
import game.entities.units.Colonist;
import game.entities.units.Explorer;
import game.entities.units.Melee;
import game.entities.units.Ranged;
import game.entities.units.Unit;
import game.entities.units.exceptions.UnitNotFoundException;
import game.gameboard.Location;
import game.entities.EntityId;

public class UnitFactory {
    private Map<EntitySubtypeEnum, UnitStats> unitStatistics;

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
    	colonistIdManager = new IdManager(0, 10);
        this.unitStatistics = new HashMap<>();
        try {
            this.unitStatistics.put(EntitySubtypeEnum.COLONIST, new UnitStats(EntitySubtypeEnum.COLONIST));
            this.unitStatistics.put(EntitySubtypeEnum.EXPLORER, new UnitStats(EntitySubtypeEnum.EXPLORER));
            this.unitStatistics.put(EntitySubtypeEnum.MELEE, new UnitStats(EntitySubtypeEnum.MELEE));
            this.unitStatistics.put(EntitySubtypeEnum.RANGE, new UnitStats(EntitySubtypeEnum.RANGE));
        }catch(UnitNotFoundException e){ System.out.println(e.getMessage()); }
    }
  
    public Unit createUnit(EntitySubtypeEnum unit, Location location, int playerId)
            throws ColonistLimitExceededException, ExplorerLimitExceededException, RangedLimitExceededException,
                    MeleeLimitExceededException, UnitNotFoundException
    {
        switch(unit) {
            case COLONIST:{
                try{
                    newColonistId = this.colonistIdManager.getNewId();
                    entityId = new EntityId(playerId, EntityTypeEnum.UNIT, EntitySubtypeEnum.COLONIST, newColonistId);
                    return new Colonist(unitStatistics.get(unit), location, entityId);
                } catch (IdLimitExceededException e) {
                    throw new ColonistLimitExceededException("Colonist limit is reached, can't add new colonist");
                }
            }
            case EXPLORER:{
                try{
                    newExplorerId = this.explorerIdManager.getNewId();
                    entityId = new EntityId(playerId, EntityTypeEnum.UNIT, EntitySubtypeEnum.EXPLORER, newExplorerId);
                    return new Explorer(unitStatistics.get(unit),location, entityId);
                } catch (IdLimitExceededException e){
                    throw new ExplorerLimitExceededException("Explorer limit is reached, can't add new explorers");
                }
            }
            case MELEE:
                try{
                    newMeleeId = this.meleeIdManager.getNewId();
                    entityId = new EntityId(playerId, EntityTypeEnum.UNIT, EntitySubtypeEnum.MELEE, newMeleeId);
                    return new Melee(unitStatistics.get(unit), location, entityId);
                } catch (IdLimitExceededException e){
                    throw new MeleeLimitExceededException("Melee limit is reached, can't add new melees");
                }
            case RANGE:{
                try{
                    newRangedId = this.rangedIdManager.getNewId();
                    entityId = new EntityId(playerId, EntityTypeEnum.UNIT, EntitySubtypeEnum.RANGE, newRangedId);
                    return new Ranged(unitStatistics.get(unit), location, entityId);
                } catch (IdLimitExceededException e){
                    throw new RangedLimitExceededException("Ranged limit is reached, can't add new ranges");
                }
            }
            default: throw new UnitNotFoundException();
        }
    }
}
