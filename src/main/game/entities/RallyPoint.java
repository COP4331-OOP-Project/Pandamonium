package game.entities;

import game.gameboard.Location;

public class RallyPoint {
    private Location location;
    private EntityId entityId;

    public RallyPoint(Location loc, EntityId id){
        location=loc;
        entityId=id;
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
