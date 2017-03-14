package game;

import game.entities.EntitySubtypeEnum;
import game.entities.EntityTypeEnum;
import game.entities.factories.EntityTypeDoesNotExistException;
import game.entities.factories.exceptions.*;
import game.entities.managers.exceptions.WorkerLimitExceededException;
import game.entities.managers.exceptions.WorkerTypeDoesNotExist;
import game.entities.workers.workerTypes.WorkerTypeEnum;
import game.gameboard.Location;
import game.semantics.Percentage;
import game.semantics.PercentageOutOfRangeException;
import game.techTree.TechTree;
import game.techTree.nodeTypes.ProductionRatePercentageResearchNode;
import game.techTree.nodeTypes.TechNodeImageEnum;
import game.techTree.nodeTypes.TechTreeNode;
import game.techTree.nodeTypes.WorkRadiusResearchNode;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class TechTreeTest {
    private static final Location HUMAN_STARTING_LOCATION = new Location(5, 28);
    private static final Location PANDA_STARTING_LOCATION = new Location(32, 11);

    private Player human;
    private Player panda;
    private TechTree humanTechTree;
    private TechTree pandaTechTree;

    private TechTreeNode humanFertilizer;
    private TechTreeNode humanWheelBarrow;
    private TechTreeNode humanIronMining;

    private Location worker1Location;
    private Location worker2Location;
    private Location worker3Location;
    private Location worker4Location;
    private Location worker5Location;
    private Location worker6Location;
    private Location capitolLocation;
    private Location capitol2Location;
    private Percentage tenPercent;

    //TODO STANDBY worker type not found
    @Before
    public void setUp(){
        human = new Player(0, HUMAN_STARTING_LOCATION);
        panda = new Player(1, PANDA_STARTING_LOCATION);
        humanTechTree = new TechTree(0);
        pandaTechTree = new TechTree(1);
        try {
            tenPercent = new Percentage(0.1);
        }catch (PercentageOutOfRangeException e){
            Assert.fail();
        }
        humanFertilizer = new ProductionRatePercentageResearchNode(human.getWorkerManager(), "Fertilizer", "+10%", TechNodeImageEnum.FOOD, WorkerTypeEnum.FOOD_GATHERER, this.tenPercent);
        humanWheelBarrow = new WorkRadiusResearchNode(human.getStructureManager(), "Wheelbarrow", "+1", TechNodeImageEnum.WORKER_RADIUS, 1);
        humanIronMining = new ProductionRatePercentageResearchNode(human.getWorkerManager(), "Iron Mining", "+10%", TechNodeImageEnum.METAL, WorkerTypeEnum.ORE_GATHERER, this.tenPercent, humanFertilizer, humanWheelBarrow);
        worker1Location = new Location(13,13);
        worker2Location = new Location(10,13);
        worker3Location = new Location(11,11);
        worker4Location = new Location(20,20);
        worker5Location = new Location(30,30);
        worker6Location = new Location(32,32);
        capitolLocation = new Location(21,23);
        capitol2Location = new Location (35,35);
        try {
            human.addEntity(EntityTypeEnum.WORKER, WorkerTypeEnum.FOOD_GATHERER, worker1Location);
            human.addEntity(EntityTypeEnum.WORKER, WorkerTypeEnum.ORE_GATHERER, worker2Location);
            human.addEntity(EntityTypeEnum.WORKER, WorkerTypeEnum.FOOD_GATHERER, worker3Location);
            human.addEntity(EntityTypeEnum.STRUCTURE, EntitySubtypeEnum.CAPITOL, capitolLocation);
            panda.addEntity(EntityTypeEnum.WORKER, WorkerTypeEnum.FOOD_GATHERER, worker4Location);
            panda.addEntity(EntityTypeEnum.WORKER, WorkerTypeEnum.FOOD_GATHERER, worker5Location);
            panda.addEntity(EntityTypeEnum.WORKER, WorkerTypeEnum.ORE_GATHERER, worker6Location);
            panda.addEntity(EntityTypeEnum.STRUCTURE, EntitySubtypeEnum.CAPITOL, capitol2Location);

        }catch(WorkerTypeDoesNotExist | EntityTypeDoesNotExistException| WorkerLimitExceededException|
                UnitTypeLimitExceededException|StructureTypeLimitExceededException|
                TotalUnitLimitExceededException| TotalStructureLimitExceededException|
                UnitTypeDoesNotExistException| StructureTypeDoesNotExist e){
            Assert.fail();
        }
    }

    @Test //Test for Fertilizer upgrade
    public void testFertilizerTech(){
        Assert.assertEquals(human.getWorkers().get(0).getProductionRate(),1,0);
        humanFertilizer.doResearch();
        Assert.assertEquals(human.getWorkers().get(0).getProductionRate(),1.1,0);
        Assert.assertEquals(human.getWorkers().get(2).getProductionRate(),1.1,0);
    }

    @Test //Human upgrade does not affect panda upgrade
    public void testSeparateTechTree(){
        Assert.assertEquals(human.getWorkers().get(0).getProductionRate(),1,0);
        humanFertilizer.doResearch();
        Assert.assertEquals(human.getWorkers().get(0).getProductionRate(),1.1,0);
        Assert.assertEquals(human.getWorkers().get(2).getProductionRate(),1.1,0);

        //Test panda's worker status
        Assert.assertEquals(panda.getWorkers().get(0).getProductionRate(),1,0);
        Assert.assertEquals(panda.getWorkers().get(1).getProductionRate(),1,0);
    }

    //TODO
    @Test //Test for WorkRadius upgrade
    public void testWheelBarrow(){
        Assert.assertEquals(human.getStructures().get(0).getStats().getWorkerRadius(), 1);
        humanWheelBarrow.doResearch();
        Assert.assertEquals(human.getStructures().get(0).getStats().getWorkerRadius(), 2);

        //Test if Panda is affected
        Assert.assertEquals(panda.getStructures().get(0).getStats().getWorkerRadius(),1);

    }

    @Test //Test IronMining
    public void testIronMiningTech(){
        Assert.assertEquals(1, human.getWorkers().get(1).getProductionRate(),0);
        humanIronMining.doResearch();
        Assert.assertEquals(1.1, human.getWorkers().get(1).getProductionRate(),0);
    }

    @Test //Mining doesn't affect food gathering
    public void testIfTechIsIndependent(){
        Assert.assertEquals(1, human.getWorkers().get(1).getProductionRate(),0);
        humanIronMining.doResearch();
        Assert.assertEquals(1.1, human.getWorkers().get(1).getProductionRate(),0);

        Assert.assertNotEquals(human.getWorkers().get(0).getProductionRate(), human.getWorkers().get(1).getProductionRate());
    }






}

