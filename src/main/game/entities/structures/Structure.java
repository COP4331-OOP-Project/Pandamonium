package game.entities.structures;

import game.entities.Entity;
import game.entities.EntityId;
import game.entities.EntitySubtypeEnum;
import game.entities.Percentage;
import game.entities.stats.StructureStats;
import game.gameboard.Location;

public abstract class Structure extends Entity {
    protected StructureStats stats;
    public Structure(StructureStats stats, Location location , EntityId entityId ){
        super(location, entityId);
        this.stats = stats;
        this.health = stats.getHealth();
        this.healthPercent = new Percentage();
        standby();
    }

    public EntitySubtypeEnum getType() {
    	return (EntitySubtypeEnum) getEntityId().getSubTypeId();
    }
    
    public StructureStats getStats() {
    	return stats;
    }

    /* Stat-adjusted damage taking*/
    @Override
    public void takeDamage(double damage){
        int armor = stats.getArmor();
        double damageX = 10/(10+armor);
        int adjDamage = (int)(damage * damageX);
        this.health -= adjDamage;
        this.healthPercent.updateHealthPercentage((double)this.health);
    }
}
