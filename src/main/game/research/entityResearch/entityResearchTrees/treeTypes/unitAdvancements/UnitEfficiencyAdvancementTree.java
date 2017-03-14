package game.research.entityResearch.entityResearchTrees.treeTypes.unitAdvancements;

import game.entities.EntitySubtypeEnum;
import game.entities.managers.UnitManager;
import game.research.entityResearch.entityResearchTrees.nodeTypes.EntityTypeAdvancementNode;
import game.research.entityResearch.entityResearchTrees.nodeTypes.unitAdvancements.UnitEfficiencyAdvancementNode;
import game.semantics.Percentage;
import game.semantics.PercentageOutOfRangeException;

public class UnitEfficiencyAdvancementTree {

    private EntityTypeAdvancementNode root;
    public UnitEfficiencyAdvancementTree(UnitManager unitManager, EntitySubtypeEnum unitType) {
        Percentage tenPercent;

        try {
            tenPercent = new Percentage(.1);
        } catch (PercentageOutOfRangeException e) {
            throw new RuntimeException("Percentage out of range");
        }
        UnitEfficiencyAdvancementNode root = new UnitEfficiencyAdvancementNode(unitManager, unitType, tenPercent, "Efficiency Increase #1", "+1");
        UnitEfficiencyAdvancementNode second = new UnitEfficiencyAdvancementNode(unitManager, unitType, tenPercent, "Efficiency Increase #2", "+1", root);
        UnitEfficiencyAdvancementNode third = new UnitEfficiencyAdvancementNode(unitManager, unitType, tenPercent, "Efficiency Increase #3", "+1", second);

        root.setChild(second);
        second.setChild(third);

        this.root = root;
    }

    public EntityTypeAdvancementNode getRoot() {
        return this.root;
    }
}
