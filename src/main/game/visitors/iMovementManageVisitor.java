package game.visitors;


import game.entities.managers.PlacementManager;

public interface iMovementManageVisitor {
    void visitMoveManage(PlacementManager pm);
}
