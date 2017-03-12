package game.techTree;

import game.techTree.nodeTypes.TechTreeNode;

import java.util.ArrayList;
import java.util.List;

public class TechTree {

    private List<TechTreeNode> rootNodes;

    public TechTree(int playerId) {
        this.rootNodes = new ArrayList<>();
    }
}
