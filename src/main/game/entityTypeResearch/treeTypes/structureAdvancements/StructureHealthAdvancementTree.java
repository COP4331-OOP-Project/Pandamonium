package game.entityTypeResearch.treeTypes.structureAdvancements;

import game.entities.EntitySubtypeEnum;
import game.entities.managers.StructureManager;
import game.entityTypeResearch.nodeTypes.EntityTypeAdvancementNode;
import game.entityTypeResearch.nodeTypes.structureAdvancements.StructureHealthAdvancementNode;

public class StructureHealthAdvancementTree {

    private EntityTypeAdvancementNode root;
    public StructureHealthAdvancementTree(StructureManager structureManager, EntitySubtypeEnum unitType) {
        StructureHealthAdvancementNode root = new StructureHealthAdvancementNode(structureManager, unitType, 1, "Health Increase #1", "+1");
        StructureHealthAdvancementNode second = new StructureHealthAdvancementNode(structureManager, unitType, 1, "Health Increase #2", "+1", root);
        StructureHealthAdvancementNode third = new StructureHealthAdvancementNode(structureManager, unitType, 1, "Health Increase #3", "+1", second);

        root.setChild(second);
        second.setChild(third);

        this.root = root;
    }

    public EntityTypeAdvancementNode getRoot() {
        return this.root;
    }
}
