package game.entities.units;

import game.entities.*;
import game.entities.managers.PlacementManager;
import game.entities.stats.UnitStats;
import game.gameboard.Location;
import game.iTurnObserver;
import game.visitors.AddUnitVisitor;
import game.visitors.RemoveEntityVisitor;

// TODO: Fix damage taking to account for defense

public abstract class Unit extends Entity implements iAttacker, iDefender, iMoveable, iTurnObserver {
    protected UnitStats stats;
    protected int orientation;
    protected Location location;

    public Unit(UnitStats stats, Location location, EntityId entityId, PlacementManager placementManager){
        super(entityId,placementManager);
        this.location=location;
        this.stats = stats;
        this.health = stats.getHealth();
        this.healthPercent = new HealthPercentage();
        this.orientation = 180;
        AddUnitVisitor addUnit = new AddUnitVisitor(this, this.location);
        placementManager.accept(addUnit);
        standby();
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

    public void instantDeath() {
        // TODO: activate death visitor
        this.health = 0;
    }
}