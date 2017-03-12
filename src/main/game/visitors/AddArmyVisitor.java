package game.visitors;

import game.entities.BattleGroup;
import game.entities.managers.MovementManager;
import game.gameboard.Location;

public class AddArmyVisitor implements iMovementManageVisitor {
    private BattleGroup battleGroup;
    private Location location;

    public AddArmyVisitor(BattleGroup bg, Location loc){
        battleGroup=bg;
        location=loc;
    }

    public void visitMoveManage(MovementManager mm){
        mm.addArmy(battleGroup, location);
    }
}
