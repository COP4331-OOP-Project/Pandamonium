package game.research.entityResearch.entityResearchTrees.treeTypes.structureAdvancements;

import game.entities.EntitySubtypeEnum;
import game.entities.managers.StructureManager;
import game.research.entityResearch.entityResearchTrees.nodeTypes.EntityTypeAdvancementNode;
import game.research.entityResearch.entityResearchTrees.nodeTypes.structureAdvancements.StructureEfficiencyAdvancementNode;
import game.semantics.Percentage;
import game.semantics.PercentageOutOfRangeException;

public class StructureEfficiencyAdvancementTree {

    private EntityTypeAdvancementNode root;
    public StructureEfficiencyAdvancementTree(StructureManager structureManager, EntitySubtypeEnum unitType) {
        Percentage tenPercent;
        try {
            tenPercent = new Percentage(.1);
        } catch (PercentageOutOfRangeException e) {
            throw new RuntimeException("Percentage out of range");
        }
        StructureEfficiencyAdvancementNode root = new StructureEfficiencyAdvancementNode(structureManager, unitType, tenPercent, "Efficiency Increase #1", "+1");
        StructureEfficiencyAdvancementNode second = new StructureEfficiencyAdvancementNode(structureManager, unitType, tenPercent, "Efficiency Increase #2", "+1", root);
        StructureEfficiencyAdvancementNode third = new StructureEfficiencyAdvancementNode(structureManager, unitType, tenPercent, "Efficiency Increase #3", "+1", second);

        root.setChild(second);
        second.setChild(third);

        this.root = root;
    }

    public EntityTypeAdvancementNode getRoot() {
        return this.root;
    }
}
