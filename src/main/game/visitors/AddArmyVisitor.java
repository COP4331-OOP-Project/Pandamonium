package game.visitors;

import game.entities.Army;
import game.entities.BattleGroup;
import game.entities.managers.PlacementManager;
import game.gameboard.Location;

public class AddArmyVisitor implements iMovementManageVisitor {
    private Army army;
    private Location location;

    public AddArmyVisitor(Army army, Location loc){
        this.army = army;
        location=loc;
    }

    public void visitMoveManage(PlacementManager mm){
        mm.addArmy(army, location);
    }
}
