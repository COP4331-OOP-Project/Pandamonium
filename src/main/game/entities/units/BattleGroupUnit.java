package game.entities.units;

import game.entities.EntityId;
import game.entities.iAttacker;
import game.entities.stats.UnitStats;

public class BattleGroupUnit implements iAttacker{
    private EntityId entityId;
    private UnitStats stats;

    public BattleGroupUnit(UnitStats stats, EntityId entityId){
        this.stats = stats;
        this.entityId = entityId;
    }

    public double getDamage(){
        return stats.getOffPow();
    }

    public int getRange(){
        //TODO Add Range
        return 0;
    }

    public int getMoveSpeed(){
        return stats.getSpeed();
    }

    public UnitStats getStats(){return stats;}

    public EntityId getEntityId(){return entityId;}

}
