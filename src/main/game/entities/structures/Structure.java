package game.entities.structures;

import game.entities.Entity;
import game.entities.EntityId;
import game.entities.EntitySubtypeEnum;
import game.entities.HealthPercentage;
import game.entities.stats.StructureStats;
import game.gameboard.Location;

public abstract class Structure extends Entity {
    protected StructureStats stats;
    public Structure(StructureStats stats, Location location , EntityId entityId ){
        super(location, entityId);
        this.stats = stats;
        this.health = stats.getHealth();
        this.healthPercent = new HealthPercentage();
        standby();
    }

    public EntitySubtypeEnum getType() {
    	return (EntitySubtypeEnum) getEntityId().getSubTypeId();
    }
    
    public StructureStats getStats() {
    	return stats;
    }

    public void increaseVisibilityRadius(int increaseAmount) {
        this.stats.increaseVisibilityRadius(increaseAmount);
    }
    
}
