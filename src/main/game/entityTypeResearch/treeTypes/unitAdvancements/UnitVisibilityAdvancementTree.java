package game.entityTypeResearch.treeTypes.unitAdvancements;

import game.entities.EntitySubtypeEnum;
import game.entities.managers.UnitManager;
import game.entityTypeResearch.nodeTypes.EntityTypeAdvancementNode;
import game.entityTypeResearch.nodeTypes.unitAdvancements.UnitVisibilityAdvancementNode;

public class UnitVisibilityAdvancementTree {

    private EntityTypeAdvancementNode root;
    public UnitVisibilityAdvancementTree(UnitManager unitManager, EntitySubtypeEnum unitType) {
        UnitVisibilityAdvancementNode root = new UnitVisibilityAdvancementNode(unitManager, unitType, 1, "Visibility Increase #1", "+1");
        UnitVisibilityAdvancementNode second = new UnitVisibilityAdvancementNode(unitManager, unitType, 1, "Visibility Increase #2", "+1", root);
        UnitVisibilityAdvancementNode third = new UnitVisibilityAdvancementNode(unitManager, unitType, 1, "Visibility Increase #3", "+1", second);

        root.setChild(second);
        second.setChild(third);

        this.root = root;
    }

    public EntityTypeAdvancementNode getRoot() {
        return this.root;
    }
}
