package game.research.entityResearch.entityResearchTrees.treeTypes.unitAdvancements;

import game.entities.EntitySubtypeEnum;
import game.entities.managers.UnitManager;
import game.research.entityResearch.entityResearchTrees.nodeTypes.EntityTypeAdvancementNode;
import game.research.entityResearch.entityResearchTrees.nodeTypes.unitAdvancements.UnitAttackAdvancementNode;

public class UnitAttackAdvancementTree {

    private EntityTypeAdvancementNode root;
    public UnitAttackAdvancementTree(UnitManager unitManager, EntitySubtypeEnum unitType) {
        UnitAttackAdvancementNode root = new UnitAttackAdvancementNode(unitManager, unitType, 1, "Attack Increase #1", "+1");
        UnitAttackAdvancementNode second = new UnitAttackAdvancementNode(unitManager, unitType, 1, "Attack Increase #2", "+1", root);
        UnitAttackAdvancementNode third = new UnitAttackAdvancementNode(unitManager, unitType, 1, "Attack Increase #3", "+1", second);

        root.setChild(second);
        second.setChild(third);

        this.root = root;
    }

    public EntityTypeAdvancementNode getRoot() {
        return this.root;
    }
}
