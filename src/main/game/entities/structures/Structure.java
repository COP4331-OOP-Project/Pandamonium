package game.entities.structures;

<<<<<<< HEAD
import game.entities.*;
=======
import java.util.ArrayList;

import game.commands.CommandEnum;
import game.entities.Entity;
import game.entities.EntityId;
import game.entities.EntitySubtypeEnum;
import game.entities.HealthPercentage;
>>>>>>> Command Stuff
import game.entities.managers.PlacementManager;
import game.entities.stats.StructureStats;
import game.gameboard.Location;
import game.iTurnObserver;
import game.semantics.Percentage;
import game.visitors.AddStructureVisitor;

public abstract class Structure extends Entity implements iTurnObserver {

    protected StructureStats stats;
    protected Location location;

<<<<<<< HEAD
    public Structure(StructureStats stats, Location location , EntityId entityId ,
                     PlacementManager placementManager, DeathNotifier notifier) {

        super(entityId, placementManager, notifier);

=======
    public Structure(StructureStats stats, Location location , EntityId entityId , PlacementManager placementManager){
    	super(entityId,placementManager);
>>>>>>> Command Stuff
        this.stats = stats;
        this.health = stats.getHealth();
        this.healthPercent = new HealthPercentage();
        this.location=location;

        AddStructureVisitor addStructureVisitor = new AddStructureVisitor(this,this.location);
        placementManager.accept(addStructureVisitor);

        standby();
<<<<<<< HEAD

=======
        addCommand(CommandEnum.DEFEND);
>>>>>>> Command Stuff
    }

    public Location getLocation(){return location;}
    public int getLocationX(){return location.getX();}
    public int getLocationY(){return location.getY();}

    public EntitySubtypeEnum getType() {
        return (EntitySubtypeEnum) getEntityId().getSubTypeId();
    }

    public StructureStats getStats() {
        return stats;
    }
    
    public void increaseVisibilityRadius(int increaseAmount) {
        this.stats.increaseVisibilityRadius(increaseAmount);
    }

    public void increaseAttackStrength(int increaseAmount) {
        this.stats.increaseAttackStrength(increaseAmount);
    }

    public void increaseDefensiveStrength(int increaseAmount) {
        this.stats.increaseDefenseStrength(increaseAmount);
    }

    public void increaseArmorStrength(int increaseAmount) {
        this.stats.increaseArmorStrength(increaseAmount);
    }

    public void increaseHealth(int increaseAmount) {
        this.stats.increaseHealth(increaseAmount);
    }

    public void increaseEfficiency(Percentage increasePercentage) {
        this.stats.increaseEfficiency(increasePercentage);
    }
}
