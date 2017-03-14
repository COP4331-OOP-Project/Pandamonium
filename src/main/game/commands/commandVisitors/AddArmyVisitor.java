package game.commands.commandVisitors;

import game.entities.BattleGroup;
import game.entities.managers.PlacementManager;
import game.gameboard.Location;

public class AddArmyVisitor implements iMovementManageVisitor {
    private BattleGroup battleGroup;
    private Location location;

    public AddArmyVisitor(BattleGroup bg, Location loc){
        battleGroup=bg;
        location=loc;
    }

    public void visitMoveManage(PlacementManager mm){
        mm.addArmy(battleGroup, location);
    }
}
