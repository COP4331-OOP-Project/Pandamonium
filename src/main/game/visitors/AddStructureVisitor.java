package game.visitors;

import game.entities.managers.PlacementManager;
import game.entities.structures.Structure;
import game.gameboard.Location;

public class AddStructureVisitor implements iMovementManageVisitor {
    private Structure structure;
    private Location location;

    public AddStructureVisitor(Structure structure, Location loc){

        this.structure=structure;
        this.location=loc;

    }

    public void visitMoveManage(PlacementManager mm){
        mm.addStructure(this.structure, location);
    }

}