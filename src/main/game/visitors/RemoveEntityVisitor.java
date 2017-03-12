package game.visitors;

import game.entities.EntityId;
import game.entities.managers.MovementManager;
import game.gameboard.Location;

public class RemoveEntityVisitor {
    private EntityId entityId;
    private Location location;

    public RemoveEntityVisitor(EntityId id, Location loc){
        entityId=id;
        location=loc;
    }

    public void visitMoveManage(MovementManager mm){
        mm.remove(entityId, location);
    }
}
