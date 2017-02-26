package game.entities.units;

import game.entities.EntityId;
import game.gameboard.Location;
import game.entities.Entity;

import game.entities.Percentage;

public abstract class Unit extends Entity{
    private UnitStats stats;

    public Unit(UnitStats stats, Location location, EntityId entityId){
        super(location, entityId);
    }

    public double getCurrentHealth(){
        return stats.getHealth();
    }

    public void heal(double healing){

    }

    public void takeDamage(double damage){

    }

    public Percentage getHealthPercentage(){
        return null;
    }

    public void decommissionEntity(){

    }
}
