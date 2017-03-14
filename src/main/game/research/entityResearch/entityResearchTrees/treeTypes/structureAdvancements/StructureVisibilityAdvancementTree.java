package game.research.entityResearch.entityResearchTrees.treeTypes.structureAdvancements;

import game.entities.EntitySubtypeEnum;
import game.entities.managers.StructureManager;
import game.research.entityResearch.entityResearchTrees.nodeTypes.EntityTypeAdvancementNode;
import game.research.entityResearch.entityResearchTrees.nodeTypes.structureAdvancements.StructureVisibilityAdvancementNode;

public class StructureVisibilityAdvancementTree {

    private EntityTypeAdvancementNode root;
    public StructureVisibilityAdvancementTree(StructureManager structureManager, EntitySubtypeEnum unitType) {
        StructureVisibilityAdvancementNode root = new StructureVisibilityAdvancementNode(structureManager, unitType, 1, "Visibility Increase #1", "+1");
        StructureVisibilityAdvancementNode second = new StructureVisibilityAdvancementNode(structureManager, unitType, 1, "Visibility Increase #2", "+1", root);
        StructureVisibilityAdvancementNode third = new StructureVisibilityAdvancementNode(structureManager, unitType, 1, "Visibility Increase #3", "+1", second);

        root.setChild(second);
        second.setChild(third);

        this.root = root;
    }

    public EntityTypeAdvancementNode getRoot() {
        return this.root;
    }
}
