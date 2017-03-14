package game.research.entityResearch.entityResearchTrees.treeTypes.structureAdvancements;

import game.entities.EntitySubtypeEnum;
import game.entities.managers.StructureManager;
import game.research.entityResearch.entityResearchTrees.nodeTypes.EntityTypeAdvancementNode;
import game.research.entityResearch.entityResearchTrees.nodeTypes.structureAdvancements.StructureAttackAdvancementNode;

public class StructureAttackAdvancementTree {

    private EntityTypeAdvancementNode root;
    public StructureAttackAdvancementTree(StructureManager structureManager, EntitySubtypeEnum unitType) {
        StructureAttackAdvancementNode root = new StructureAttackAdvancementNode(structureManager, unitType, 1, "Attack Increase #1", "+1");
        StructureAttackAdvancementNode second = new StructureAttackAdvancementNode(structureManager, unitType, 1, "Attack Increase #2", "+1", root);
        StructureAttackAdvancementNode third = new StructureAttackAdvancementNode(structureManager, unitType, 1, "Attack Increase #3", "+1", second);

        root.setChild(second);
        second.setChild(third);

        this.root = root;
    }

    public EntityTypeAdvancementNode getRoot() {
        return this.root;
    }
}
