package game.commands.commandVisitors;

import game.entities.managers.PlacementManager;
import game.entities.units.Unit;
import game.gameboard.Location;

public class AddUnitVisitor implements iMovementManageVisitor {
    private Unit unit;
    private Location location;

    public AddUnitVisitor(Unit u, Location loc){
        unit=u;
        location=loc;
    }

    public void visitMoveManage(PlacementManager mm){
        mm.addUnit(unit, location);
    }
}
