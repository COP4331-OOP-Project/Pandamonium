package game.entities.units;


import game.entities.*;

import game.commands.CommandEnum;
import game.entities.Entity;
import game.entities.EntityId;
import game.entities.EntitySubtypeEnum;
import game.entities.HealthPercentage;
import game.entities.iAttacker;
import game.entities.iDefender;
import game.entities.iMoveable;

import game.entities.managers.PlacementManager;
import game.entities.stats.UnitStats;
import game.gameboard.Location;
import game.iTurnObserver;
import game.resources.Resource;
import game.visitors.AddUnitVisitor;
import game.visitors.RemoveEntityVisitor;

// TODO: Fix damage taking to account for defense

public abstract class Unit extends Entity implements iAttacker, iDefender, iMoveable, iTurnObserver {
    protected UnitStats stats;
    protected int orientation;
    protected Location location;

    public Unit(UnitStats stats, Location location, EntityId entityId, PlacementManager placementManager, DeathNotifier notifier) {
        super(entityId,placementManager, notifier);
        this.location=location;
        this.stats = stats;
        this.health = stats.getHealth();
        this.healthPercent = new HealthPercentage();
        this.orientation = 180;
        AddUnitVisitor addUnit = new AddUnitVisitor(this, this.location);
        placementManager.accept(addUnit);
        standby();
        addCommand(CommandEnum.MOVE);
        addCommand(CommandEnum.REINFORCE_ARMY);
    }

    public EntitySubtypeEnum getType() { 
    	return (EntitySubtypeEnum) getEntityId().getSubTypeId();
    }
    
    /* Accessors */
    public UnitStats getStats() { return stats; }
    public int getOrientation() { return orientation; }
    public Location getLocation(){return location;}
    public int getLocationX(){return location.getX();}
    public int getLocationY(){return location.getY();}

    /* Stats Accessors */
    public int getUpkeep() { return stats.getUpkeep(); }

    /* Mutators */
    public void setStats(UnitStats stats) { this.stats = stats; }
    public void setOrientation(int orientation) { this.orientation = orientation; }

    public void setLocation(Location location){
        setOrientation(direction(location));
        //Move To Tile
        AddUnitVisitor addUnit = new AddUnitVisitor(this, location);
        placementManager.accept(addUnit);
        //Delete old tile reference
        RemoveEntityVisitor removeEntityVisitor = new RemoveEntityVisitor(getEntityId(), this.location);
        placementManager.accept(removeEntityVisitor);
        //Update location
        this.location=location;
    }

    public int direction(Location location){
        if(location.equals(new Location(this.location.getX(), this.location.getY()-1))){
            return 0;
        }
        else if(location.equals(new Location(this.location.getX()+1, this.location.getY()-1))){
            return 45;
        }
        else if(location.equals(new Location(this.location.getX()+1, this.location.getY()))){
            return 135;
        }
        else if(location.equals(new Location(this.location.getX(), this.location.getY()+1))){
            return 180;
        }
        else if(location.equals(new Location(this.location.getX()-1, this.location.getY()+1))){
            return 225;
        }
        else if(location.equals(new Location(this.location.getX()-1, this.location.getY()))){
            return 315;
        }
        //Default set their orientation to 0
        return 180;
    }
    /* iAttacker */
    public double getDamage(){ return (double)stats.getOffPow(); }
    public int getRange(){ return stats.getRange(); }

    /* iMoveable */
    public int getMoveDistance(){ return stats.getSpeed(); }

    /* Stat-adjusted damage taking */
    @Override
    public void takeDamage(double damage){
        double armor = stats.getArmor();
        double damageX = 10/(10+armor);
        double adjDamage = damage * damageX;

        this.health -= adjDamage;
        this.healthPercent.updateHealthPercentage((double)this.health);

        if (this.health <= 0)
            this.notifer.publishEntityDeath(this.entityId.getTypeId(), (EntitySubtypeEnum) this.entityId.getSubTypeId(), this.entityId);
    }

    public void instantDeath() {
        // TODO: activate death visitor
        this.health = 0;
    }

    /* Upkeep handling */
    public void upkeepHandling(Resource nutrients){
        if (getUpkeep() <= nutrients.getAmount()){
            nutrients.decreaseAmountByValue(getUpkeep());
        } else takeDamage(10);
    }
}