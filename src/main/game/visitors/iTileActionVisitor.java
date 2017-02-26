package game.visitors;

import game.entities.Entity;

// Interface for visitor performing Tile Actions
public interface iTileActionVisitor {
    void visitEntity(Entity e);     // Visit entity of tile
}
