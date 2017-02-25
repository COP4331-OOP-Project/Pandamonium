package game.entities.units;

import game.entities.*;
import game.gameboard.Location;
import game.visitors.iTileActionVisitor;

public class Explorer extends Unit implements iEntity, iAttacker, iDefender, iMoveable {

    public Explorer(UnitStats stats, Location location){ super(stats, location); }

    /* iEntity */
    public double getCurrentHealth(){ return this.health; }
    public Percentage getHealthPercentage(){ return this.healthPercent; }
    public void takeDamage(double damage){
        this.health -= damage;
        this.healthPercent.updateHealthPercentage((double)this.health);
    }
    public void heal(double healing){
        this.health += healing;
        this.healthPercent.updateHealthPercentage((double)this.health);
    }
    public void accept(iTileActionVisitor v){}

    /* iAttacker */
    public double getDamage(){ return (double)stats.getOffPow(); }
    public int getRange(){ return 1; }

    /* iMoveable */
    public int getMoveDistance(){ return stats.getSpeed(); }
}
