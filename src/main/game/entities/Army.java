package game.entities;

import game.entities.units.Unit;
import game.gameboard.Location;

public class Army extends Entity{
    private BattleGroup battleGroup;
    private Reinforcement reinforcement;
    private RallyPoint rallyPoint;

    public Army(EntityId entityId, RallyPoint rp){
        super(rp.getLocation(), entityId);
        battleGroup = new BattleGroup(rp.getLocation(), entityId);
        reinforcement = new Reinforcement();
        rallyPoint=rp;
    }

    public void moveRallyPoint(Location loc){
        rallyPoint.setLocation(loc);
        setLocation(loc);
    }

    //TODO find a way to delete tiles reference to unit that is added to battlegroup. VISITOR?
    public void updateArmy(){
        while(reinforcement.onLocation(battleGroup.getLocation())){
            Unit unitToAdd = reinforcement.getOnLocationUnit(battleGroup.getLocation());
            battleGroup.addUnit(unitToAdd);
        }
    }

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
