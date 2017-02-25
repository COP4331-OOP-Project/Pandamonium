package game.entities;

import game.gameboard.Location;

public class Army {
    private EntityId entityId;
    private BattleGroup battleGroup;
    private Reinforcement reinforcement;
    private RallyPoint rallyPoint;
    private Location location;

    public Army(EntityId entityId, RallyPoint rp){
        this.entityId=entityId;
        battleGroup = new BattleGroup(rp.getLocation());
        reinforcement = new Reinforcement();
        rallyPoint=rp;
        location=rp.getLocation();
    }

    public void moveRallyPoint(Location loc){
        rallyPoint.setLocation(loc);
        location=loc;
    }

    //TODO find way to update battlegroup and reinforcements
    public void updateArmy(){

    }


}
