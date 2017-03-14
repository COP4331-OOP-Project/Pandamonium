package game.commands.commandVisitors;

import game.entities.EntityId;
import game.entities.managers.PlacementManager;
import game.gameboard.Location;

public class RemoveEntityVisitor implements iMovementManageVisitor {
    private EntityId entityId;
    private Location location;

    public RemoveEntityVisitor(EntityId id, Location loc){
        entityId=id;
        location=loc;
    }

    public void visitMoveManage(PlacementManager mm){
        mm.remove(entityId, location);
    }
}
