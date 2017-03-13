package game.visitors;

import game.entities.managers.MovementManager;

public interface iMovementManageVisitor {
    void visitMoveManage(MovementManager mm);
}
