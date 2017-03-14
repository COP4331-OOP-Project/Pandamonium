package game.techTree;


import game.Player;
import game.entities.managers.PlacementManager;
import game.entities.managers.StructureManager;
import game.entities.managers.UnitManager;
import game.entities.managers.WorkerManager;
import game.entities.workers.workerTypes.WorkerTypeEnum;
import game.semantics.Percentage;
import game.semantics.PercentageOutOfRangeException;
import game.techTree.nodeTypes.*;

import java.util.ArrayList;

public class TechTree {

    private ArrayList<TechTreeNode> rootNodes;
    private WorkerManager workerManager;
    private StructureManager structureManager;
    private Percentage tenPercent;

    public TechTree(Player player, PlacementManager placementManager, WorkerManager workerManager, UnitManager unitManager) {
        this.workerManager = workerManager;
        this.structureManager = new StructureManager(player, placementManager, workerManager, unitManager);
        this.rootNodes = new ArrayList<>();
        try {
            this.tenPercent = new Percentage(.1);
        } catch (PercentageOutOfRangeException e) {
            throw new RuntimeException("Percentage out of range in TechTree initialization.");
        }
        initTree();
    }

    private void initTree() {
        TechTreeNode fertilizer = new ProductionRatePercentageResearchNode(this.workerManager, "Fertilizer", "+10%", TechNodeImageEnum.FOOD, WorkerTypeEnum.FOOD_GATHERER, this.tenPercent);
        TechTreeNode wheelBarrow = new WorkRadiusResearchNode(this.structureManager, "Wheelbarrow", "+1", TechNodeImageEnum.WORKER_RADIUS, 1);
        TechTreeNode ironMining = new ProductionRatePercentageResearchNode(this.workerManager, "Iron Mining", "+10%", TechNodeImageEnum.METAL, WorkerTypeEnum.ORE_GATHERER, this.tenPercent, fertilizer, wheelBarrow);
        fertilizer.addChild(ironMining);
        wheelBarrow.addChild(ironMining);

        TechTreeNode tent = new WorkerDensityResearchNode(this.structureManager, "Tent", "+1", TechNodeImageEnum.WORKER_DENSITY, 1);
        TechTreeNode bed = new ProductionRateIntegerResearchNode(this.workerManager, "Bed", "-1 turn", TechNodeImageEnum.BREEDING, WorkerTypeEnum.WORKER_GENERATOR, 1, tent);
        TechTreeNode housing = new WorkerDensityResearchNode(this.structureManager, "Housing", "+2", TechNodeImageEnum.WORKER_DENSITY, 2, bed, ironMining);
        tent.addChild(bed);
        bed.addChild(housing);
        ironMining.addChild(housing);

        this.rootNodes.add(fertilizer);
        this.rootNodes.add(wheelBarrow);
        this.rootNodes.add(tent);

        TechTreeNode draftHorse = new WorkRadiusResearchNode(this.structureManager, "Draft Horse", "+1", TechNodeImageEnum.WORKER_RADIUS, 1, housing);
        TechTreeNode militia = new ProductionRateIntegerResearchNode(this.workerManager, "Militia", "-1 turn", TechNodeImageEnum.SOLDIER_POINTS, WorkerTypeEnum.SOLDIER_GENERATOR, 1, draftHorse);
        TechTreeNode barracks = new ProductionRateIntegerResearchNode(this.workerManager, "Barracks", "-1 turn", TechNodeImageEnum.SOLDIER_POINTS, WorkerTypeEnum.SOLDIER_GENERATOR, 1, militia);
        TechTreeNode roads = new WorkRadiusResearchNode(this.structureManager, "Roads", "+1", TechNodeImageEnum.WORKER_RADIUS, 1, barracks);
        TechTreeNode urbanPlanning = new WorkerDensityResearchNode(this.structureManager, "Urban Planning", "+2", TechNodeImageEnum.WORKER_DENSITY, 2, roads);
        TechTreeNode windPower = new ProductionRatePercentageResearchNode(this.workerManager, "Wind Power", "+10%", TechNodeImageEnum.POWER, WorkerTypeEnum.PEAT_GATHERER, this.tenPercent, roads);
        TechTreeNode militaryAcademy = new ProductionRateIntegerResearchNode(this.workerManager, "Military Academy", "-1 turn", TechNodeImageEnum.SOLDIER_POINTS, WorkerTypeEnum.SOLDIER_GENERATOR, 1, urbanPlanning);
        TechTreeNode nuclearPower = new ProductionRatePercentageResearchNode(this.workerManager, "Nuclear Power", "+10%", TechNodeImageEnum.POWER, WorkerTypeEnum.PEAT_GATHERER, this.tenPercent, windPower);

        housing.addChild(draftHorse);
        draftHorse.addChild(militia);
        militia.addChild(barracks);
        barracks.addChild(roads);
        roads.addChild(urbanPlanning);
        roads.addChild(windPower);
        urbanPlanning.addChild(militaryAcademy);
        windPower.addChild(nuclearPower);

        TechTreeNode irrigation = new ProductionRatePercentageResearchNode(this.workerManager, "Irrigation", "+10%", TechNodeImageEnum.FOOD, WorkerTypeEnum.FOOD_GATHERER, this.tenPercent, housing);
        TechTreeNode pesticides = new ProductionRatePercentageResearchNode(this.workerManager, "Pesticides", "+10%", TechNodeImageEnum.FOOD, WorkerTypeEnum.FOOD_GATHERER, this.tenPercent, irrigation);
        TechTreeNode beer = new ProductionRateIntegerResearchNode(this.workerManager, "Beer", "-1 turn", TechNodeImageEnum.BREEDING, WorkerTypeEnum.WORKER_GENERATOR, 1, pesticides);
        TechTreeNode steamPower = new ProductionRatePercentageResearchNode(this.workerManager, "Steam Power", "+10%", TechNodeImageEnum.POWER, WorkerTypeEnum.PEAT_GATHERER, this.tenPercent, housing);
        TechTreeNode steelMining = new ProductionRatePercentageResearchNode(this.workerManager, "Steel Mining", "+10%", TechNodeImageEnum.METAL, WorkerTypeEnum.ORE_GATHERER, this.tenPercent, steamPower);
        TechTreeNode blastFurnace = new ProductionRatePercentageResearchNode(this.workerManager, "Blast Furnace", "+10%", TechNodeImageEnum.METAL, WorkerTypeEnum.ORE_GATHERER, this.tenPercent, steelMining);
        TechTreeNode vodka = new ProductionRateIntegerResearchNode(this.workerManager, "Vodka", "-1 turn", TechNodeImageEnum.BREEDING, WorkerTypeEnum.WORKER_GENERATOR, 1, beer, blastFurnace);

        housing.addChild(irrigation);
        housing.addChild(steamPower);
        irrigation.addChild(pesticides);
        pesticides.addChild(beer);
        beer.addChild(vodka);
        steamPower.addChild(steelMining);
        steelMining.addChild(blastFurnace);
        blastFurnace.addChild(vodka);
    }

    public ArrayList<TechTreeNode> getRootNodes() {
        return this.rootNodes;
    }
}
