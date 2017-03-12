package game.visitors;

import game.entities.RallyPoint;
import game.entities.managers.MovementManager;
import game.gameboard.Location;

public class AddRallyPointVisitor implements iMovementManageVisitor {
    private RallyPoint rallyPoint;
    private Location location;

    public AddRallyPointVisitor(RallyPoint rp, Location loc){
       rallyPoint=rp;
        location=loc;
    }

    public void visitMoveManage(MovementManager mm){
        mm.addRallyPoint(rallyPoint, location);
    }
}
