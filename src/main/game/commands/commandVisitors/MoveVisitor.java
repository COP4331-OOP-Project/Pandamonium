package game.commands.commandVisitors;

import game.entities.iMoveable;
import game.gameboard.Tile;

// Visitor to handle
public class MoveVisitor {

    // Constructor
    MoveVisitor() {}

    // Visit tile and set entity on tile
    public void visitTile(Tile target) {}

    // Visit movable entity and update its location
    public void visitMoveable(iMoveable e) {}

}
