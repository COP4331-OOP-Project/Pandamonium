package game.visitors;

import game.entities.managers.MovementManager;
import game.entities.units.Unit;
import game.gameboard.Location;

public class AddUnitVisitor implements iMovementManageVisitor {
    private Unit unit;
    private Location location;

    public AddUnitVisitor(Unit u, Location loc){
        unit=u;
        location=loc;
    }

    public void visitMoveManage(MovementManager mm){
        mm.addUnit(unit, location);
    }
}
