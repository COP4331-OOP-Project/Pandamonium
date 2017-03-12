package game.entities;

import game.gameboard.Location;

public class Army extends Entity{
    private BattleGroup battleGroup;
    private Reinforcement reinforcement;
    private RallyPoint rallyPoint;

    public Army(EntityId entityId, RallyPoint rp){
        super(rp.getLocation(), entityId);
        battleGroup = new BattleGroup(rp.getLocation());
        reinforcement = new Reinforcement();
        rallyPoint=rp;
    }

    public void moveRallyPoint(Location loc){
        rallyPoint.setLocation(loc);
        setLocation(loc);
    }

    //TODO find way to update battlegroup and reinforcements
    public void updateArmy(){

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
