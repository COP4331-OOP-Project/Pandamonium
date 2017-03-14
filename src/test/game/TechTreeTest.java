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
import game.techTree.nodeTypes.*;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class TechTreeTest {
    private static final Location HUMAN_STARTING_LOCATION = new Location(5, 28);
    private static final Location PANDA_STARTING_LOCATION = new Location(32, 11);

    private Player human;
    private Player panda;
//    private TechTree humanTechTree;
//    private TechTree pandaTechTree;

    private TechTreeNode humanFertilizer;
    private TechTreeNode humanWheelBarrow;
    private TechTreeNode humanIronMining;
    private TechTreeNode humanTent;
    private TechTreeNode humanBed;
    private TechTreeNode humanHousing;
    private TechTreeNode humanDraftHorse;

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
//        humanTechTree = new TechTree(0);
//        pandaTechTree = new TechTree(1);
        try {
            tenPercent = new Percentage(0.1);
        }catch (PercentageOutOfRangeException e){
            Assert.fail();
        }
        humanFertilizer = new ProductionRatePercentageResearchNode(human.getWorkerManager(), "Fertilizer", "+10%", TechNodeImageEnum.FOOD, WorkerTypeEnum.FOOD_GATHERER, this.tenPercent);
        humanWheelBarrow = new WorkRadiusResearchNode(human.getStructureManager(), "Wheelbarrow", "+1", TechNodeImageEnum.WORKER_RADIUS, 1);
        humanIronMining = new ProductionRatePercentageResearchNode(human.getWorkerManager(), "Iron Mining", "+10%", TechNodeImageEnum.METAL, WorkerTypeEnum.ORE_GATHERER, this.tenPercent, humanFertilizer, humanWheelBarrow);
        humanTent = new WorkerDensityResearchNode(human.getStructureManager(), "Tent", "+1", TechNodeImageEnum.WORKER_DENSITY, 1);
        humanBed = new ProductionRateIntegerResearchNode(human.getWorkerManager(), "Bed", "-1 turn", TechNodeImageEnum.BREEDING, WorkerTypeEnum.WORKER_GENERATOR, 1, humanTent);
        humanHousing = new WorkerDensityResearchNode(human.getStructureManager(), "Housing", "+2", TechNodeImageEnum.WORKER_DENSITY, 2, humanBed, humanIronMining);
        humanDraftHorse = new WorkRadiusResearchNode(human.getStructureManager(), "Draft Horse", "+1", TechNodeImageEnum.WORKER_RADIUS, 1, humanHousing);
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
            human.addEntity(EntityTypeEnum.WORKER, WorkerTypeEnum.WORKER_GENERATOR, worker1Location);
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
        Assert.assertTrue(humanFertilizer.isResearchCompleted());
        Assert.assertEquals(human.getWorkers().get(0).getProductionRate(),1.1,0);
        Assert.assertEquals(human.getWorkers().get(2).getProductionRate(),1.1,0);
    }

    @Test //Human upgrade does not affect panda upgrade
    public void testSeparateTechTree(){
        Assert.assertEquals(human.getWorkers().get(0).getProductionRate(),1,0);
        humanFertilizer.doResearch();
        Assert.assertTrue(humanFertilizer.isResearchCompleted());
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
        Assert.assertTrue(humanWheelBarrow.isResearchCompleted());
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

    @Test //Test Tent upgrade
    public void testTentTech(){
        Assert.assertEquals(10, human.getStructures().get(0).getStats().getWorkerDensity());
        humanTent.doResearch();
        Assert.assertEquals(11, human.getStructures().get(0).getStats().getWorkerDensity());
    }

    //TODO It seems like the Bed isn't doing anything to the production rate
    @Test //Test Bed Tech upgrade
    public void testBedTech(){
        Assert.assertEquals(1, human.getWorkers().get(0).getProductionRate(),0);
        humanBed.doResearch();
        Assert.assertEquals(1, human.getWorkers().get(0).getProductionRate(), 0);
    }

    @Test //Test House Tech upgrade
    public void testHousingTech(){
        Assert.assertEquals(10, human.getStructures().get(0).getStats().getWorkerDensity());
        humanTent.doResearch();
        humanHousing.doResearch();
        Assert.assertEquals(13, human.getStructures().get(0).getStats().getWorkerDensity());
    }

    @Test //Test if the later constructed structures also have the upgrade
    public void testNewlyBuiltStructureTech(){
        Assert.assertEquals(10, human.getStructures().get(0).getStats().getWorkerDensity());
        humanTent.doResearch();
        Assert.assertTrue(humanTent.isResearchCompleted());
        humanHousing.doResearch();
        Assert.assertTrue(humanHousing.isResearchCompleted());
        Assert.assertEquals(13, human.getStructures().get(0).getStats().getWorkerDensity());

        Location farmLocation = new Location(1,1);
        try {
            human.addEntity(EntityTypeEnum.STRUCTURE, EntitySubtypeEnum.FARM, farmLocation);
        }catch(EntityTypeDoesNotExistException| UnitTypeLimitExceededException|StructureTypeLimitExceededException|
                TotalUnitLimitExceededException| TotalStructureLimitExceededException|
                UnitTypeDoesNotExistException| StructureTypeDoesNotExist e){
            Assert.fail();
        }

        Assert.assertEquals(13, human.getStructures().get(1).getStats().getWorkerDensity());
    }

    @Test //Test Draft Horse Tech
    public void testDraftHorse(){
        Assert.assertEquals(human.getStructures().get(0).getStats().getWorkerRadius(), 1);
        humanWheelBarrow.doResearch();
        Assert.assertTrue(humanWheelBarrow.isResearchCompleted());
        Assert.assertEquals(human.getStructures().get(0).getStats().getWorkerRadius(), 2);

        //Test if Panda is affected
        Assert.assertEquals(panda.getStructures().get(0).getStats().getWorkerRadius(),1);

        humanDraftHorse.doResearch();
        Assert.assertTrue(humanDraftHorse.isResearchCompleted());

        Assert.assertEquals(3, human.getStructures().get(0).getStats().getWorkerRadius());
    }
}

