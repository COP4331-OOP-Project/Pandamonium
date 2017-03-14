package game.commands.commandVisitors;

import game.entities.RallyPoint;
import game.entities.managers.PlacementManager;
import game.gameboard.Location;

public class AddRallyPointVisitor implements iMovementManageVisitor {
    private RallyPoint rallyPoint;
    private Location location;

    public AddRallyPointVisitor(RallyPoint rp, Location loc){
       rallyPoint=rp;
        location=loc;
    }

    public void visitMoveManage(PlacementManager mm){
        mm.addRallyPoint(rallyPoint, location);
    }
}
