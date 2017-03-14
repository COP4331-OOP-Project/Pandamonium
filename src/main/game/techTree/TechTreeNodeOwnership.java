package game.techTree;

import game.iTurnObserver;
import game.techTree.nodeTypes.TechTreeNode;

public class TechTreeNodeOwnership {

    private TechTreeNode techTreeNode;
    private int turnsLeft;

    public TechTreeNodeOwnership(TechTreeNode techTreeNode, int productionRate) {
        this.techTreeNode = techTreeNode;
        this.turnsLeft = this.techTreeNode.getProductionRequirement();
    }

    public boolean doTurn() {
        if (--this.turnsLeft <= 0) {
            this.techTreeNode.doResearch();
            return false;
        }

        return true;
    }
}
