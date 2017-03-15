package game.entities.units;

import game.entities.EntityId;
import game.entities.EntitySubtypeEnum;
import game.entities.HealthPercentage;
import game.entities.iAttacker;
import game.entities.stats.UnitStats;

public class BattleGroupUnit implements iAttacker{
    private EntityId entityId;
    private UnitStats stats;
    private int health;
    private HealthPercentage healthPercent;

    public BattleGroupUnit(UnitStats stats, EntityId entityId){
        this.stats = stats;
        this.entityId = entityId;
        this.health = stats.getHealth();
        this.healthPercent = new HealthPercentage();
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

    public int getHealth(){return health;}

    public HealthPercentage getHealthPercent(){return healthPercent;}

    public boolean takeDamage(double damage){
        health-=damage;
        if(health<=0){
            return true;
        }
        else{
            return false;
        }
    }

}
