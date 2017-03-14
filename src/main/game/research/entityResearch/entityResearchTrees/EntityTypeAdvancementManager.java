package game.research.entityResearch.entityResearchTrees;

import game.entities.EntitySubtypeEnum;
import game.entities.managers.StructureManager;
import game.entities.managers.UnitManager;
import game.research.entityResearch.entityResearchTrees.treeTypes.structureAdvancements.*;
import game.research.entityResearch.entityResearchTrees.treeTypes.unitAdvancements.*;

public class EntityTypeAdvancementManager {

    private int playerId;
    private UnitArmorAdvancementTree meleeArmorTree;
    private UnitArmorAdvancementTree rangedArmorTree;
    private UnitArmorAdvancementTree colonistArmorTree;
    private UnitArmorAdvancementTree explorerArmorTree;

    private UnitAttackAdvancementTree meleeAttackTree;
    private UnitAttackAdvancementTree rangedAttackTree;
    private UnitAttackAdvancementTree colonistAttackTree;
    private UnitAttackAdvancementTree explorerAttackTree;

    private UnitDefensiveAdvancementTree meleeDefensiveTree;
    private UnitDefensiveAdvancementTree rangedDefensiveTree;
    private UnitDefensiveAdvancementTree colonistDefensiveTree;
    private UnitDefensiveAdvancementTree explorerDefensiveTree;

    private UnitEfficiencyAdvancementTree meleeEfficiencyTree;
    private UnitEfficiencyAdvancementTree rangedEfficiencyTree;
    private UnitEfficiencyAdvancementTree colonistEfficiencyTree;
    private UnitEfficiencyAdvancementTree explorerEfficiencyTree;

    private UnitHealthAdvancementTree meleeHealthTree;
    private UnitHealthAdvancementTree rangedHealthTree;
    private UnitHealthAdvancementTree colonistHealthTree;
    private UnitHealthAdvancementTree explorerHealthTree;

    private UnitMovementAdvancementTree meleeMovementTree;
    private UnitMovementAdvancementTree rangedMovementTree;
    private UnitMovementAdvancementTree colonistMovementTree;
    private UnitMovementAdvancementTree explorerMovementTree;

    private UnitVisibilityAdvancementTree meleeVisibilityTree;
    private UnitVisibilityAdvancementTree rangedVisibilityTree;
    private UnitVisibilityAdvancementTree colonistVisibilityTree;
    private UnitVisibilityAdvancementTree explorerVisibilityTree;

    private StructureAttackAdvancementTree fortAttackTree;

    private StructureDefensiveAdvancementTree capitolDefensiveTree;
    private StructureDefensiveAdvancementTree farmDefensiveTree;
    private StructureDefensiveAdvancementTree fortDefensiveTree;
    private StructureDefensiveAdvancementTree mineDefensiveTree;
    private StructureDefensiveAdvancementTree observationTowerDefensiveTree;
    private StructureDefensiveAdvancementTree powerPlantDefensiveTree;
    private StructureDefensiveAdvancementTree universityDefensiveTree;

    private StructureArmorAdvancementTree capitolArmorTree;
    private StructureArmorAdvancementTree farmArmorTree;
    private StructureArmorAdvancementTree fortArmorTree;
    private StructureArmorAdvancementTree mineArmorTree;
    private StructureArmorAdvancementTree observationTowerArmorTree;
    private StructureArmorAdvancementTree powerPlantArmorTree;
    private StructureArmorAdvancementTree universityArmorTree;

    private StructureEfficiencyAdvancementTree capitolEfficiencyTree;
    private StructureEfficiencyAdvancementTree farmEfficiencyTree;
    private StructureEfficiencyAdvancementTree fortEfficiencyTree;
    private StructureEfficiencyAdvancementTree mineEfficiencyTree;
    private StructureEfficiencyAdvancementTree observationTowerEfficiencyTree;
    private StructureEfficiencyAdvancementTree powerPlantEfficiencyTree;
    private StructureEfficiencyAdvancementTree universityEfficiencyTree;

    private StructureHealthAdvancementTree capitolHealthTree;
    private StructureHealthAdvancementTree farmHealthTree;
    private StructureHealthAdvancementTree fortHealthTree;
    private StructureHealthAdvancementTree mineHealthTree;
    private StructureHealthAdvancementTree observationTowerHealthTree;
    private StructureHealthAdvancementTree powerPlantHealthTree;
    private StructureHealthAdvancementTree universityHealthTree;

    private StructureVisibilityAdvancementTree capitolVisibilityTree;
    private StructureVisibilityAdvancementTree farmVisibilityTree;
    private StructureVisibilityAdvancementTree fortVisibilityTree;
    private StructureVisibilityAdvancementTree mineVisibilityTree;
    private StructureVisibilityAdvancementTree observationTowerVisibilityTree;
    private StructureVisibilityAdvancementTree powerPlantVisibilityTree;
    private StructureVisibilityAdvancementTree universityVisibilityTree;

    public void EntityTypeAdvancementManager(int playerId, UnitManager unitManager, StructureManager structureManager) {
        this.playerId = playerId;
        initTrees(unitManager, structureManager);
    }

    private void initTrees(UnitManager unitManager, StructureManager structureManager) {
        this.meleeArmorTree = new UnitArmorAdvancementTree(unitManager, EntitySubtypeEnum.MELEE);
        this.rangedArmorTree = new UnitArmorAdvancementTree(unitManager, EntitySubtypeEnum.RANGE);
        this.colonistArmorTree = new UnitArmorAdvancementTree(unitManager, EntitySubtypeEnum.COLONIST);
        this.explorerArmorTree = new UnitArmorAdvancementTree(unitManager, EntitySubtypeEnum.EXPLORER);

        this.meleeAttackTree = new UnitAttackAdvancementTree(unitManager, EntitySubtypeEnum.MELEE);
        this.rangedAttackTree = new UnitAttackAdvancementTree(unitManager, EntitySubtypeEnum.RANGE);
        this.colonistAttackTree = new UnitAttackAdvancementTree(unitManager, EntitySubtypeEnum.COLONIST);
        this.explorerAttackTree = new UnitAttackAdvancementTree(unitManager, EntitySubtypeEnum.EXPLORER);

        this.meleeDefensiveTree = new UnitDefensiveAdvancementTree(unitManager, EntitySubtypeEnum.MELEE);
        this.rangedDefensiveTree = new UnitDefensiveAdvancementTree(unitManager, EntitySubtypeEnum.RANGE);
        this.colonistDefensiveTree = new UnitDefensiveAdvancementTree(unitManager, EntitySubtypeEnum.COLONIST);
        this.explorerDefensiveTree = new UnitDefensiveAdvancementTree(unitManager, EntitySubtypeEnum.EXPLORER);

        this.meleeEfficiencyTree = new UnitEfficiencyAdvancementTree(unitManager, EntitySubtypeEnum.MELEE);
        this.rangedEfficiencyTree = new UnitEfficiencyAdvancementTree(unitManager, EntitySubtypeEnum.RANGE);
        this.colonistEfficiencyTree = new UnitEfficiencyAdvancementTree(unitManager, EntitySubtypeEnum.COLONIST);
        this.explorerEfficiencyTree = new UnitEfficiencyAdvancementTree(unitManager, EntitySubtypeEnum.EXPLORER);

        this.meleeHealthTree = new UnitHealthAdvancementTree(unitManager, EntitySubtypeEnum.MELEE);
        this.rangedHealthTree = new UnitHealthAdvancementTree(unitManager, EntitySubtypeEnum.RANGE);
        this.colonistHealthTree = new UnitHealthAdvancementTree(unitManager, EntitySubtypeEnum.COLONIST);
        this.explorerHealthTree = new UnitHealthAdvancementTree(unitManager, EntitySubtypeEnum.EXPLORER);

        this.meleeMovementTree = new UnitMovementAdvancementTree(unitManager, EntitySubtypeEnum.MELEE);
        this.rangedMovementTree = new UnitMovementAdvancementTree(unitManager, EntitySubtypeEnum.RANGE);
        this.colonistMovementTree = new UnitMovementAdvancementTree(unitManager, EntitySubtypeEnum.COLONIST);
        this.explorerMovementTree = new UnitMovementAdvancementTree(unitManager, EntitySubtypeEnum.EXPLORER);

        this.meleeVisibilityTree = new UnitVisibilityAdvancementTree(unitManager, EntitySubtypeEnum.MELEE);
        this.rangedVisibilityTree = new UnitVisibilityAdvancementTree(unitManager, EntitySubtypeEnum.RANGE);
        this.colonistVisibilityTree = new UnitVisibilityAdvancementTree(unitManager, EntitySubtypeEnum.COLONIST);
        this.explorerVisibilityTree = new UnitVisibilityAdvancementTree(unitManager, EntitySubtypeEnum.EXPLORER);

        this.capitolArmorTree = new StructureArmorAdvancementTree(structureManager, EntitySubtypeEnum.CAPITOL);
        this.farmArmorTree = new StructureArmorAdvancementTree(structureManager, EntitySubtypeEnum.FARM);
        this.fortArmorTree = new StructureArmorAdvancementTree(structureManager, EntitySubtypeEnum.FORT);
        this.mineArmorTree = new StructureArmorAdvancementTree(structureManager, EntitySubtypeEnum.MINE);
        this.observationTowerArmorTree = new StructureArmorAdvancementTree(structureManager, EntitySubtypeEnum.OBSERVE);
        this.powerPlantArmorTree = new StructureArmorAdvancementTree(structureManager, EntitySubtypeEnum.PLANT);
        this.universityArmorTree = new StructureArmorAdvancementTree(structureManager, EntitySubtypeEnum.UNIVERSITY);

        this.fortAttackTree = new StructureAttackAdvancementTree(structureManager, EntitySubtypeEnum.FORT);

        this.capitolDefensiveTree = new StructureDefensiveAdvancementTree(structureManager, EntitySubtypeEnum.CAPITOL);
        this.farmDefensiveTree = new StructureDefensiveAdvancementTree(structureManager, EntitySubtypeEnum.FARM);
        this.fortDefensiveTree= new StructureDefensiveAdvancementTree(structureManager, EntitySubtypeEnum.FORT);
        this.mineDefensiveTree = new StructureDefensiveAdvancementTree(structureManager, EntitySubtypeEnum.MINE);
        this.observationTowerDefensiveTree = new StructureDefensiveAdvancementTree(structureManager, EntitySubtypeEnum.OBSERVE);
        this.powerPlantDefensiveTree = new StructureDefensiveAdvancementTree(structureManager, EntitySubtypeEnum.PLANT);
        this.universityDefensiveTree = new StructureDefensiveAdvancementTree(structureManager, EntitySubtypeEnum.UNIVERSITY);

        this.capitolEfficiencyTree = new StructureEfficiencyAdvancementTree(structureManager, EntitySubtypeEnum.CAPITOL);
        this.farmEfficiencyTree = new StructureEfficiencyAdvancementTree(structureManager, EntitySubtypeEnum.FARM);
        this.fortEfficiencyTree = new StructureEfficiencyAdvancementTree(structureManager, EntitySubtypeEnum.FORT);
        this.mineEfficiencyTree = new StructureEfficiencyAdvancementTree(structureManager, EntitySubtypeEnum.MINE);
        this.observationTowerEfficiencyTree = new StructureEfficiencyAdvancementTree(structureManager, EntitySubtypeEnum.OBSERVE);
        this.powerPlantEfficiencyTree = new StructureEfficiencyAdvancementTree(structureManager, EntitySubtypeEnum.PLANT);
        this.universityEfficiencyTree = new StructureEfficiencyAdvancementTree(structureManager, EntitySubtypeEnum.UNIVERSITY);

        this.capitolHealthTree = new StructureHealthAdvancementTree(structureManager, EntitySubtypeEnum.CAPITOL);
        this.farmHealthTree = new StructureHealthAdvancementTree(structureManager, EntitySubtypeEnum.FARM);
        this.fortHealthTree = new StructureHealthAdvancementTree(structureManager, EntitySubtypeEnum.FORT);
        this.mineHealthTree = new StructureHealthAdvancementTree(structureManager, EntitySubtypeEnum.MINE);
        this.observationTowerHealthTree = new StructureHealthAdvancementTree(structureManager, EntitySubtypeEnum.OBSERVE);
        this.powerPlantHealthTree = new StructureHealthAdvancementTree(structureManager, EntitySubtypeEnum.PLANT);
        this.universityHealthTree = new StructureHealthAdvancementTree(structureManager, EntitySubtypeEnum.UNIVERSITY);

        this.capitolVisibilityTree = new StructureVisibilityAdvancementTree(structureManager, EntitySubtypeEnum.CAPITOL);
        this.farmVisibilityTree = new StructureVisibilityAdvancementTree(structureManager, EntitySubtypeEnum.FARM);
        this.fortVisibilityTree = new StructureVisibilityAdvancementTree(structureManager, EntitySubtypeEnum.FORT);
        this.mineVisibilityTree = new StructureVisibilityAdvancementTree(structureManager, EntitySubtypeEnum.MINE);
        this.observationTowerVisibilityTree = new StructureVisibilityAdvancementTree(structureManager, EntitySubtypeEnum.OBSERVE);
        this.powerPlantVisibilityTree = new StructureVisibilityAdvancementTree(structureManager, EntitySubtypeEnum.PLANT);
        this.universityVisibilityTree = new StructureVisibilityAdvancementTree(structureManager, EntitySubtypeEnum.UNIVERSITY);
    }

    public UnitArmorAdvancementTree getMeleeArmorTree() {
        return meleeArmorTree;
    }

    public UnitArmorAdvancementTree getRangedArmorTree() {
        return rangedArmorTree;
    }

    public UnitArmorAdvancementTree getColonistArmorTree() {
        return colonistArmorTree;
    }

    public UnitArmorAdvancementTree getExplorerArmorTree() {
        return explorerArmorTree;
    }

    public UnitAttackAdvancementTree getMeleeAttackTree() {
        return meleeAttackTree;
    }

    public UnitAttackAdvancementTree getRangedAttackTree() {
        return rangedAttackTree;
    }

    public UnitAttackAdvancementTree getColonistAttackTree() {
        return colonistAttackTree;
    }

    public UnitAttackAdvancementTree getExplorerAttackTree() {
        return explorerAttackTree;
    }

    public UnitDefensiveAdvancementTree getMeleeDefensiveTree() {
        return meleeDefensiveTree;
    }

    public UnitDefensiveAdvancementTree getRangedDefensiveTree() {
        return rangedDefensiveTree;
    }

    public UnitDefensiveAdvancementTree getColonistDefensiveTree() {
        return colonistDefensiveTree;
    }

    public UnitDefensiveAdvancementTree getExplorerDefensiveTree() {
        return explorerDefensiveTree;
    }

    public UnitEfficiencyAdvancementTree getMeleeEfficiencyTree() {
        return meleeEfficiencyTree;
    }

    public UnitEfficiencyAdvancementTree getRangedEfficiencyTree() {
        return rangedEfficiencyTree;
    }

    public UnitEfficiencyAdvancementTree getColonistEfficiencyTree() {
        return colonistEfficiencyTree;
    }

    public UnitEfficiencyAdvancementTree getExplorerEfficiencyTree() {
        return explorerEfficiencyTree;
    }

    public UnitHealthAdvancementTree getMeleeHealthTree() {
        return meleeHealthTree;
    }

    public UnitHealthAdvancementTree getRangedHealthTree() {
        return rangedHealthTree;
    }

    public UnitHealthAdvancementTree getColonistHealthTree() {
        return colonistHealthTree;
    }

    public UnitHealthAdvancementTree getExplorerHealthTree() {
        return explorerHealthTree;
    }

    public UnitMovementAdvancementTree getMeleeMovementTree() {
        return meleeMovementTree;
    }

    public UnitMovementAdvancementTree getRangedMovementTree() {
        return rangedMovementTree;
    }

    public UnitMovementAdvancementTree getColonistMovementTree() {
        return colonistMovementTree;
    }

    public UnitMovementAdvancementTree getExplorerMovementTree() {
        return explorerMovementTree;
    }

    public UnitVisibilityAdvancementTree getMeleeVisibilityTree() {
        return meleeVisibilityTree;
    }

    public UnitVisibilityAdvancementTree getRangedVisibilityTree() {
        return rangedVisibilityTree;
    }

    public UnitVisibilityAdvancementTree getColonistVisibilityTree() {
        return colonistVisibilityTree;
    }

    public UnitVisibilityAdvancementTree getExplorerVisibilityTree() {
        return explorerVisibilityTree;
    }

    public StructureAttackAdvancementTree getFortAttackTree() {
        return fortAttackTree;
    }

    public StructureDefensiveAdvancementTree getCapitolDefensiveTree() {
        return capitolDefensiveTree;
    }

    public StructureDefensiveAdvancementTree getFarmDefensiveTree() {
        return farmDefensiveTree;
    }

    public StructureDefensiveAdvancementTree getFortDefensiveTree() {
        return fortDefensiveTree;
    }

    public StructureDefensiveAdvancementTree getMineDefensiveTree() {
        return mineDefensiveTree;
    }

    public StructureDefensiveAdvancementTree getObservationTowerDefensiveTree() {
        return observationTowerDefensiveTree;
    }

    public StructureDefensiveAdvancementTree getPowerPlantDefensiveTree() {
        return powerPlantDefensiveTree;
    }

    public StructureDefensiveAdvancementTree getUniversityDefensiveTree() {
        return universityDefensiveTree;
    }

    public StructureArmorAdvancementTree getCapitolArmorTree() {
        return capitolArmorTree;
    }

    public StructureArmorAdvancementTree getFarmArmorTree() {
        return farmArmorTree;
    }

    public StructureArmorAdvancementTree getFortArmorTree() {
        return fortArmorTree;
    }

    public StructureArmorAdvancementTree getMineArmorTree() {
        return mineArmorTree;
    }

    public StructureArmorAdvancementTree getObservationTowerArmorTree() {
        return observationTowerArmorTree;
    }

    public StructureArmorAdvancementTree getPowerPlantArmorTree() {
        return powerPlantArmorTree;
    }

    public StructureArmorAdvancementTree getUniversityArmorTree() {
        return universityArmorTree;
    }

    public StructureEfficiencyAdvancementTree getFarmEfficiencyTree() {
        return farmEfficiencyTree;
    }

    public StructureEfficiencyAdvancementTree getFortEfficiencyTree() {
        return fortEfficiencyTree;
    }

    public StructureEfficiencyAdvancementTree getMineEfficiencyTree() {
        return mineEfficiencyTree;
    }

    public StructureEfficiencyAdvancementTree getObservationTowerEfficiencyTree() {
        return observationTowerEfficiencyTree;
    }

    public StructureEfficiencyAdvancementTree getPowerPlantEfficiencyTree() {
        return powerPlantEfficiencyTree;
    }

    public StructureEfficiencyAdvancementTree getUniversityEfficiencyTree() {
        return universityEfficiencyTree;
    }

    public StructureEfficiencyAdvancementTree getCapitolEfficiencyTree() {
        return capitolEfficiencyTree;
    }

    public StructureHealthAdvancementTree getCapitolHealthTree() {
        return capitolHealthTree;
    }

    public StructureHealthAdvancementTree getFarmHealthTree() {
        return farmHealthTree;
    }

    public StructureHealthAdvancementTree getFortHealthTree() {
        return fortHealthTree;
    }

    public StructureHealthAdvancementTree getMineHealthTree() {
        return mineHealthTree;
    }

    public StructureHealthAdvancementTree getObservationTowerHealthTree() {
        return observationTowerHealthTree;
    }

    public StructureHealthAdvancementTree getPowerPlantHealthTree() {
        return powerPlantHealthTree;
    }

    public StructureHealthAdvancementTree getUniversityHealthTree() {
        return universityHealthTree;
    }

    public StructureVisibilityAdvancementTree getCapitolVisibilityTree() {
        return capitolVisibilityTree;
    }

    public StructureVisibilityAdvancementTree getFarmVisibilityTree() {
        return farmVisibilityTree;
    }

    public StructureVisibilityAdvancementTree getFortVisibilityTree() {
        return fortVisibilityTree;
    }

    public StructureVisibilityAdvancementTree getMineVisibilityTree() {
        return mineVisibilityTree;
    }

    public StructureVisibilityAdvancementTree getObservationTowerVisibilityTree() {
        return observationTowerVisibilityTree;
    }

    public StructureVisibilityAdvancementTree getPowerPlantVisibilityTree() {
        return powerPlantVisibilityTree;
    }

    public StructureVisibilityAdvancementTree getUniversityVisibilityTree() {
        return universityVisibilityTree;
    }
}
