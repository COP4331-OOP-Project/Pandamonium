package game.entityTypeResearch.treeTypes.unitAdvancements;

import game.entities.EntitySubtypeEnum;
import game.entities.managers.UnitManager;
import game.entityTypeResearch.nodeTypes.EntityTypeAdvancementNode;
import game.entityTypeResearch.nodeTypes.unitAdvancements.UnitDefensiveAdvancementNode;

public class UnitDefensiveAdvancementTree {

    private EntityTypeAdvancementNode root;
    public UnitDefensiveAdvancementTree(UnitManager unitManager, EntitySubtypeEnum unitType) {
        UnitDefensiveAdvancementNode root = new UnitDefensiveAdvancementNode(unitManager, unitType, 1, "Defense Increase #1", "+1");
        UnitDefensiveAdvancementNode second = new UnitDefensiveAdvancementNode(unitManager, unitType, 1, "Defense Increase #2", "+1", root);
        UnitDefensiveAdvancementNode third = new UnitDefensiveAdvancementNode(unitManager, unitType, 1, "Defense Increase #3", "+1", second);

        root.setChild(second);
        second.setChild(third);

        this.root = root;
    }

    public EntityTypeAdvancementNode getRoot() {
        return this.root;
    }
}
