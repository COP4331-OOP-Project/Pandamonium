package game.entities;

import game.gameboard.Location;

public class RallyPoint {
    private Location location;
    private EntityId entityId;
    private Army army;

    public RallyPoint(Location loc, EntityId id, Army army){
        location=loc;
        entityId=id;
        this.army=army;
    }

    public Location getLocation(){
        return location;
    }

    public void setLocation(Location loc){
        location=loc;
    }

    public EntityId getEntityId(){
        return entityId;
    }
}
