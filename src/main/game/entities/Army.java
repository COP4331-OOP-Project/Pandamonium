package game.entities;

import game.entities.managers.PlacementManager;
import game.entities.units.Unit;
import game.gameboard.Location;
import game.commands.commandVisitors.AddArmyVisitor;
import game.commands.commandVisitors.AddRallyPointVisitor;
import game.commands.commandVisitors.RemoveEntityVisitor;

public class Army extends Entity{
    private BattleGroup battleGroup;
    private Reinforcement reinforcement;
    private RallyPoint rallyPoint;

    //TODO Know when to add battlegroup to tile. Shouldn't show up unless units in battlegroup
    public Army(EntityId entityId, RallyPoint rp, PlacementManager placementManager){
        super(entityId, placementManager);
        battleGroup = new BattleGroup(rp.getLocation(), entityId);
        reinforcement = new Reinforcement();
        rallyPoint=rp;
        AddRallyPointVisitor addRallyPointVisitor = new AddRallyPointVisitor(rallyPoint,rp.getLocation());
        placementManager.accept(addRallyPointVisitor);
        updateArmy();
    }

    public void moveRallyPoint(Location loc){
        //Add to new tile
        AddRallyPointVisitor addRallyPointVisitor = new AddRallyPointVisitor(rallyPoint,loc);
        placementManager.accept(addRallyPointVisitor);
        //Remove from old tile
        RemoveEntityVisitor removeEntityVisitor = new RemoveEntityVisitor(rallyPoint.getEntityId(),rallyPoint.getLocation());
        placementManager.accept(removeEntityVisitor);
        rallyPoint.setLocation(loc);
    }

    public void updateArmy(){
        while(reinforcement.onLocation(battleGroup.getLocation())){
            Unit unitToAdd = reinforcement.getOnLocationUnit(battleGroup.getLocation());
            //Remove tile reference to unit
            RemoveEntityVisitor removeEntityVisitor = new RemoveEntityVisitor(unitToAdd.getEntityId(), unitToAdd.getLocation());
            placementManager.accept(removeEntityVisitor);

            battleGroup.addUnit(unitToAdd);
        }
    }

    public void moveBattleGroup(Location loc){
        //Move to Tile
        AddArmyVisitor addArmyVisitor = new AddArmyVisitor(battleGroup, loc);
        placementManager.accept(addArmyVisitor);
        //Delete old Tile reference
        RemoveEntityVisitor removeEntityVisitor = new RemoveEntityVisitor(getEntityId(),battleGroup.getLocation());
        placementManager.accept(removeEntityVisitor);
        //Update battlegroup and army location
        battleGroup.setLocation(loc);
    }

    public Location getLocation(){return battleGroup.getLocation();}
    public int getLocationX(){return getLocation().getX();}
    public int getLocationY(){return getLocation().getY();}

    public Location getRallyPointLocation(){return rallyPoint.getLocation();}

    public double getCurrentHealth(){
        return 0;
    }
    public HealthPercentage getHealthPercentage(){
        return null;
    }
    public void takeDamage(double damage){

    }
    public void heal(double healing){

    }
    public void decommissionEntity(){

    }

}
