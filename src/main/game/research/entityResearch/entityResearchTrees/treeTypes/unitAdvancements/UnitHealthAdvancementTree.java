package game.research.entityResearch.entityResearchTrees.treeTypes.unitAdvancements;

import game.entities.EntitySubtypeEnum;
import game.entities.managers.UnitManager;
import game.research.entityResearch.entityResearchTrees.nodeTypes.EntityTypeAdvancementNode;
import game.research.entityResearch.entityResearchTrees.nodeTypes.unitAdvancements.UnitHealthAdvancementNode;

public class UnitHealthAdvancementTree {

    private EntityTypeAdvancementNode root;
    public UnitHealthAdvancementTree(UnitManager unitManager, EntitySubtypeEnum unitType) {
        UnitHealthAdvancementNode root = new UnitHealthAdvancementNode(unitManager, unitType, 1, "Health Increase #1", "+1");
        UnitHealthAdvancementNode second = new UnitHealthAdvancementNode(unitManager, unitType, 1, "Health Increase #2", "+1", root);
        UnitHealthAdvancementNode third = new UnitHealthAdvancementNode(unitManager, unitType, 1, "Health Increase #3", "+1", second);

        root.setChild(second);
        second.setChild(third);

        this.root = root;
    }

    public EntityTypeAdvancementNode getRoot() {
        return this.root;
    }
}
