package game.research.entityResearch.entityResearchTrees.treeTypes.structureAdvancements;

import game.entities.EntitySubtypeEnum;
import game.entities.managers.StructureManager;
import game.research.entityResearch.entityResearchTrees.nodeTypes.EntityTypeAdvancementNode;
import game.research.entityResearch.entityResearchTrees.nodeTypes.structureAdvancements.StructureArmorAdvancementNode;

public class StructureArmorAdvancementTree {

    private EntityTypeAdvancementNode root;
    public StructureArmorAdvancementTree(StructureManager structureManager, EntitySubtypeEnum unitType) {
        StructureArmorAdvancementNode root = new StructureArmorAdvancementNode(structureManager, unitType, 1, "Armor Increase #1", "+1");
        StructureArmorAdvancementNode second = new StructureArmorAdvancementNode(structureManager, unitType, 1, "Armor Increase #2", "+1", root);
        StructureArmorAdvancementNode third = new StructureArmorAdvancementNode(structureManager, unitType, 1, "Armor Increase #3", "+1", second);

        root.setChild(second);
        second.setChild(third);

        this.root = root;
    }

    public EntityTypeAdvancementNode getRoot() {
        return this.root;
    }
}
