package game.visitors;

import game.entities.iEntity;

// Interface for visitor performing Tile Actions
public interface iTileActionVisitor {
    void visitEntity(iEntity e);     // Visit entity of tile
}
