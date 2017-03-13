package game.visitors;

import game.entities.managers.MovementManager;
import game.entities.structures.Structure;
import game.gameboard.Location;

public class AddStructureVisitor implements iMovementManageVisitor {
    private Structure structure;
    private Location location;

    public AddStructureVisitor(Structure structure, Location loc){
        this.structure=structure;
        location=loc;
    }

    public void visitMoveManage(MovementManager mm){
        mm.addStructure(this.structure, location);
    }
}