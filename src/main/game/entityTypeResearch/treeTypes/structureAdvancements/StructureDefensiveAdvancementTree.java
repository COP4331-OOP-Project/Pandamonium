package game.entityTypeResearch.treeTypes.structureAdvancements;

import game.entities.EntitySubtypeEnum;
import game.entities.managers.StructureManager;
import game.entityTypeResearch.nodeTypes.EntityTypeAdvancementNode;
import game.entityTypeResearch.nodeTypes.structureAdvancements.StructureDefensiveAdvancementNode;

public class StructureDefensiveAdvancementTree {

    private EntityTypeAdvancementNode root;
    public StructureDefensiveAdvancementTree(StructureManager structureManager, EntitySubtypeEnum unitType) {
        StructureDefensiveAdvancementNode root = new StructureDefensiveAdvancementNode(structureManager, unitType, 1, "Defense Increase #1", "+1");
        StructureDefensiveAdvancementNode second = new StructureDefensiveAdvancementNode(structureManager, unitType, 1, "Defense Increase #2", "+1", root);
        StructureDefensiveAdvancementNode third = new StructureDefensiveAdvancementNode(structureManager, unitType, 1, "Defense Increase #3", "+1", second);

        root.setChild(second);
        second.setChild(third);

        this.root = root;
    }

    public EntityTypeAdvancementNode getRoot() {
        return this.root;
    }
}
