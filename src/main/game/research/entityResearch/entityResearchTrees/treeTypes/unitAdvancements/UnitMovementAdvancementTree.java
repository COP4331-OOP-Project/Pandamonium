package game.research.entityResearch.entityResearchTrees.treeTypes.unitAdvancements;

import game.entities.EntitySubtypeEnum;
import game.entities.managers.UnitManager;
import game.research.entityResearch.entityResearchTrees.nodeTypes.EntityTypeAdvancementNode;
import game.research.entityResearch.entityResearchTrees.nodeTypes.unitAdvancements.UnitMovementAdvancementNode;

public class UnitMovementAdvancementTree {

    private EntityTypeAdvancementNode root;
    public UnitMovementAdvancementTree(UnitManager unitManager, EntitySubtypeEnum unitType) {
        UnitMovementAdvancementNode root = new UnitMovementAdvancementNode(unitManager, unitType, 1, "Movement Increase #1", "+1");
        UnitMovementAdvancementNode second = new UnitMovementAdvancementNode(unitManager, unitType, 1, "Movement Increase #2", "+1", root);
        UnitMovementAdvancementNode third = new UnitMovementAdvancementNode(unitManager, unitType, 1, "Movement Increase #3", "+1", second);

        root.setChild(second);
        second.setChild(third);

        this.root = root;
    }

    public EntityTypeAdvancementNode getRoot() {
        return this.root;
    }
}
