package game.entityTypeResearch.treeTypes.unitAdvancements;

import game.entities.EntitySubtypeEnum;
import game.entities.managers.UnitManager;
import game.entityTypeResearch.nodeTypes.EntityTypeAdvancementNode;
import game.entityTypeResearch.nodeTypes.unitAdvancements.UnitArmorAdvancementNode;

public class UnitArmorAdvancementTree {

    private EntityTypeAdvancementNode root;
    public UnitArmorAdvancementTree(UnitManager unitManager, EntitySubtypeEnum unitType) {
        UnitArmorAdvancementNode root = new UnitArmorAdvancementNode(unitManager, unitType, 1, "Armor Increase #1", "+1");
        UnitArmorAdvancementNode second = new UnitArmorAdvancementNode(unitManager, unitType, 1, "Armor Increase #2", "+1", root);
        UnitArmorAdvancementNode third = new UnitArmorAdvancementNode(unitManager, unitType, 1, "Armor Increase #3", "+1", second);

        root.setChild(second);
        second.setChild(third);

        this.root = root;
    }

    public EntityTypeAdvancementNode getRoot() {
        return this.root;
    }
}
